@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumberLoop(n: Int): Int { // цикл
    var countDigits = 1
    var myNum = n
    while (myNum / 10 != 0) {
        countDigits += 1
        myNum /= 10
    }
    return countDigits
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fibLoop(n: Int): Int { // цикл
    var num = 1
    var prevNum = 1
    var tmp = num
    if (n >= 3) {
        for (i in 3..n) {
            tmp = num
            num += prevNum
            prevNum = tmp
        }
    }
    return num
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcmLoop(m: Int, n: Int): Int { // цикл
    // НОК = m * n / НОД(m, n)
    // НОД ---------------
    // определим наибольшее и наименьшее из чисел
    var max = m
    var min = n
    var tmp = 0
    if (m < n) {
        min = m
        max = n
    }
    while (max % min != 0) { // НОД = min при завершении цикла
        tmp = min
        min = max % min
        max = tmp
    }
    return m * n / min
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisorRec(n: Int): Int { // рекурсия
    var result = 1
    for (i in 2..n) {
        if (n % i == 0) {
            result = i
            break
        }
    }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisorRec(n: Int): Int { // рекурсия
    var result = 1
    for (i in n - 1 downTo 2) {
        if (n % i == 0) {
            result = i
            break
        }
    }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisorLoop(n: Int): Int { // цикл
    var result = 1
    for (i in 2..n) {
        if (n % i == 0) {
            result = i
            break
        }
    }
    return result
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrimeNod(m: Int, n: Int): Boolean { // через НОД
    var result = true
    var min = m
    var max = n
    if (m > n) {
        max = m
        min = n
    }
    for (i in 2..min) {
        if (m % i == 0 && n % i == 0) {
            result = false
            break
        }
    }
    return result
}