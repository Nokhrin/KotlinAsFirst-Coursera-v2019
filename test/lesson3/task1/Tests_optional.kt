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
    fun fibLoop() {
        assertEquals(1, fibLoop(1))
        assertEquals(1, fibLoop(2))
        assertEquals(2, fibLoop(3))
        assertEquals(5, fibLoop(5))
        assertEquals(21, fibLoop(8))
        assertEquals(102334155, fibLoop(40))
        assertEquals(1134903170, fibLoop(45))
        assertEquals(1836311903, fibLoop(46))
        // Just to calculate it
        fibLoop(50)
    }


}