package com.example.baseapp.core.testing

import kotlin.test.assertEquals

/**
 * will decide later between assertJ and kotlin test
 */
infix fun <T> T.shouldEqual(expected: T?): T = this.apply { assertEquals(expected, this) }
