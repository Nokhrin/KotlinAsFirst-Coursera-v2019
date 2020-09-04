package lesson3.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.math.PI

class Tests_optional {
    @Test
    @Tag("Easy")
    fun digitNumberLoop() {
        assertEquals(1, digitNumberLoop(0))
        assertEquals(1, digitNumberLoop(7))
        assertEquals(2, digitNumberLoop(10))
        assertEquals(2, digitNumberLoop(99))
        assertEquals(3, digitNumberLoop(123))
        assertEquals(10, digitNumberLoop(Int.MAX_VALUE))
    }

    @Test
    @Tag("Easy")
    fun fibRec() {
        assertEquals(1, fibRec(1))
        assertEquals(1, fibRec(2))
        assertEquals(2, fibRec(3))
        assertEquals(5, fibRec(5))
        assertEquals(21, fibRec(8))
        assertEquals(102334155, fibRec(40))
        assertEquals(1134903170, fibRec(45))
        assertEquals(1836311903, fibRec(46))
        // уже на 47м числе происходит переполнение Int
        fibRec(50)
    }

    @Test
    @Tag("Easy")
    fun isCoPrimeSpeedTest() {
        assertTrue(isCoPrime(25, 49))
        assertFalse(isCoPrime(6, 8))
        assertTrue(isCoPrime(17, 97))
        assertFalse(isCoPrime(37, 111))
        assertTrue(isCoPrime(1234567890, 908765431))
        assertTrue(isCoPrime(2109876543, 1234567891))
        assertTrue(isCoPrime(23, 17))
        assertFalse(isCoPrime(28, 18))
        assertTrue(isCoPrime(1234567890, 908765431))
        assertTrue(isCoPrime(2109876543, 1234567891))
        assertTrue(isCoPrime(1234567890, 908765431))
        assertTrue(isCoPrime(2109876543, 1234567891))
        assertTrue(isCoPrime(1234567890, 908765431))
        assertFalse(isCoPrime(2147483647, Int.MAX_VALUE))
    }

}