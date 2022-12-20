package com.codely.api

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import java.time.LocalDateTime

open class BaseTest {

    protected fun givenFixedDate(fixedDateTime: LocalDateTime) {
        mockkStatic(LocalDateTime::class)
        every {
            LocalDateTime.now()
        } returns fixedDateTime
    }

    @AfterEach
    protected fun cleanMock() {
        unmockkAll()
    }
}