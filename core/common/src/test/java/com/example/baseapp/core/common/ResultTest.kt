package com.example.baseapp.core.common

import app.cash.turbine.test
import com.example.baseapp.core.common.result.Result
import com.example.baseapp.core.common.result.asResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultTest {
    @Test
    fun `GIVEN value WHEN SUCESS THEN verify loading and success`() = runTest {
        flow {
            emit(10)
        }.asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(10), awaitItem())
                awaitComplete()
            }
    }

    @Test
    fun `WHEN Error THEN verify loading and error`() = runTest {
        flow<Int> {
            throw Exception(ERROR_MSG)
        }.asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                when (val result = awaitItem()) {
                    is Result.Error -> assertEquals(ERROR_MSG, result.exception.message)
                    Result.Loading,
                    is Result.Success,
                    -> throw IllegalStateException(
                        "The flow should have emitted an Error Result",
                    )
                }
                awaitComplete()
            }
    }

    companion object {
        private const val ERROR_MSG = "Test Exception"
    }
}
