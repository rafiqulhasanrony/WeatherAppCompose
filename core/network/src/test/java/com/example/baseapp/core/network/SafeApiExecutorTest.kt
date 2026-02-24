package com.example.baseapp.core.network

import com.example.baseapp.core.common.result.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SafeApiExecutorTest {

    private lateinit var executor: SafeApiExecutor

    @Before
    fun setup() {
        executor = SafeApiExecutor()
    }

    @Test
    fun `Given Success body WHEN response is successful THEN return Success result`() = runTest {
        val response = Response.success("Hello")

        val result = executor.execute { response }

        assertTrue(result is Result.Success)
        assertEquals("Hello", result.data)
    }

    @Test
    fun `Given empty success body THEN returns error`() = runTest {
        val response = Response.success<String>(null)

        val result = executor.execute { response }

        assertTrue(result is Result.Error)
        assertEquals(
            ErrorMessages.EMPTY_BODY,
            result.exception.message,
        )
    }

    @Test
    fun `GIVEN error JSON object WHEN response is not successful THEN returns parsed error message from JSON object`() = runTest {
        val result = executor.execute { errorResponse(422, """{"status":422,"message":"Validation failed"}""") }

        assertError(result, "Response.error()")
    }

    @Test
    fun `WHEN unknown host error THEN returns unknown host error`() = runTest {
        val result = executor.execute<String> {
            throw UnknownHostException()
        }

        assertError(result, ErrorMessages.UNKNOWN_HOST)
    }

    @Test
    fun `WHEN socket timeout error THEN returns timeout error`() = runTest {
        val result = executor.execute<String> {
            throw SocketTimeoutException()
        }
        assertError(result, ErrorMessages.TIMEOUT)
    }

    @Test
    fun `WHEN IO error for IOException THEN returns timeout error`() = runTest {
        val result = executor.execute<String> {
            throw IOException()
        }

        assertError(result, ErrorMessages.TIMEOUT)
    }

    @Test
    fun `WHEN unknown network error for unexpected exception THEN returns unknown network error`() = runTest {
        val result = executor.execute<String> {
            throw RuntimeException()
        }

        assertError(result, ErrorMessages.UNKNOWN_NETWORK)
    }

    private fun <T> errorResponse(code: Int, json: String): Response<T> =
        Response.error(code, json.toResponseBody("application/json".toMediaTypeOrNull()))

    private fun assertError(result: Result<*>, expectedMessage: String) {
        assertTrue(result is Result.Error)
        val exception = result.exception
        assertTrue(exception is RequestException)
        assertEquals(expectedMessage, exception.message)
    }
}
