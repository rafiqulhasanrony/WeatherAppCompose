package com.example.baseapp.core.network

import com.example.baseapp.core.common.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeApiExecutor @Inject constructor() {

    suspend fun <T : Any> execute(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        call: suspend () -> Response<T>,
    ): Result<T> = withContext(dispatcher) {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                    ?: return@withContext Result.Error(
                        RequestException(ErrorMessages.EMPTY_BODY),
                    )

                return@withContext Result.Success(body)
            }

            val message =
                parseError(response.errorBody()) ?: response.message()

            Result.Error(RequestException(message))
        } catch (_: UnknownHostException) {
            Result.Error(RequestException(ErrorMessages.UNKNOWN_HOST))
        } catch (_: SocketTimeoutException) {
            Result.Error(RequestException(ErrorMessages.TIMEOUT))
        } catch (_: IOException) {
            Result.Error(RequestException(ErrorMessages.TIMEOUT))
        } catch (_: HttpException) {
            Result.Error(RequestException(ErrorMessages.UNKNOWN_NETWORK))
        } catch (_: Exception) {
            Result.Error(RequestException(ErrorMessages.UNKNOWN_NETWORK))
        }
    }

    private fun parseError(responseBody: ResponseBody?): String? {
        return try {
            val raw = responseBody?.string() ?: return null
            when (val json = JSONTokener(raw).nextValue()) {
                is JSONObject -> {
                    json.optString("message").takeIf { it.isNotEmpty() }
                }
                is JSONArray -> {
                    for (i in 0 until json.length()) {
                        val message = json.optJSONObject(i)?.optString("message")
                        if (!message.isNullOrEmpty()) {
                            return message
                        }
                    }
                    null
                }
                else -> null
            }
        } catch (_: Exception) {
            null
        }
    }
}

object ErrorMessages {
    const val TIMEOUT = "Please check your internet connection"
    const val UNKNOWN_NETWORK = "Please check your network connection and try again"
    const val EMPTY_BODY = "Response body can not be empty"
    const val UNKNOWN_HOST =
        "Couldn't connect to the server at the moment. Please try again in a few minutes"
}
