@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int = // рекурсия
    when {
        n / 10 < 1 -> 1
        else -> digitNumber(n / 10) + digitNumber(n % 10)
    }

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int { // цикл
    var num = 1
    var prevNum = 1
    if (n >= 3) {
        for (i in 3..n) {
            val tmp = num
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
fun lcm(m: Int, n: Int): Int { // цикл
    // НОК = m * n / НОД(m, n)
    // НОД ---------------
    // определим наибольшее и наименьшее из чисел
    var max = m
    var min = n
    if (m < n) {
        min = m
        max = n
    }
    while (max % min != 0) { // НОД = min при завершении цикла
        val tmp = min
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
fun minDivisor(n: Int): Int { // цикл
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
fun isCoPrime(m: Int, n: Int): Boolean {
    // определим минимальное из заданных чисел
    var min = m
    if (m > n) {
        min = n
    }
    // если числа делятся на 2, то они не взаимнопростые. Исключим 2 из перебора.
    // для сравнения написал в Loop_optional функцию без иключения 2. С исключением получилось медленнее :)
    if (m % 2 == 0 && n % 2 == 0) return false
    for (i in 3..min step 2) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int { // цикл
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
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var result = false
    for (i in m..n) {
        val root = sqrt(i.toDouble())
        if (i == (round(root).toInt()) * (round(root).toInt())) result = true
    }
    return result
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var counter = 0
    var newX = x
    while (newX > 1) {
        if (newX % 2 == 0) {
            newX /= 2
        } else {
            newX = newX * 3 + 1
        }
        counter += 1
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var element = x
    var coeff = 2
    var result = element
    while (abs(element) >= eps) {
        element = (element * (-1) * x * x) / (coeff * (coeff + 1))
        coeff += 2
        result += element
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var term = 1.0
    var n = 1.0
    var result = term
    while (abs(term) >= eps) {
        term = term * (-1) * x * x / (n * (n + 1))
        n += 2
        result += term
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var digitsCount = 0
    var result = 0
    while (number / 10 >= 1) { // считаем количество цифр в заданном числе - 1
        digitsCount += 1
        number /= 10
    }
    number = n
    while (number / 10 > 0) { // находим слагаемые для нового числа
        var tmpNumber = number % 10
        for (i in 1..digitsCount) {
            tmpNumber *= 10
        }
        number /= 10
        digitsCount -= 1
        result += tmpNumber
    }
    result += number
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number = n
    var digitsCount = 0
    var result = true
    // считаем количество цифр
    while (true) {
        digitsCount += 1
        if (number / 10 == 0) break
        number /= 10
    }
    number = n // возвращаем исходное значение
    // будем сравнивать крайнюю правую и крайнюю левую цифры
    // число итераций цикла равна половине от количества цифр (делим целочисленно)
    var highDivider = 1
    for (i in 1 until digitsCount) {
        // определяем количество разрядов у делителя для определения старшего разряда
        highDivider *= 10
    }
    for (i in 1..digitsCount / 2) {
        // за счёт деления нацело на 2 можем не думать о четности количества цифр в числе
        if ((number / highDivider % 10) != (number % 10)) {
            result = false
            return result
        }
        number /= 10 // двигаемся справа налево
        highDivider /= 100 // двигаемся слева направо
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    var result = false // предположим, что цифры не различаются
// хотел найти количество цифр с помощью логарифма, но с некоторыми числами неверный результат
// var digitsCount = ((round(log10(number.toDouble())) + 1).toInt())
    var digitsCount = 0
    while (true) {
        digitsCount += 1
        if (number / 10 == 0) break
        number /= 10
    }
    number = n // возвращаем исходное значение
    for (i in 1 until digitsCount) {
        if ((number % 10) != (number / 10 % 10)) { // сравниваем крайнюю правую цифру со следующей
            result = true
            break
        }
        number /= 10
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var counter = 0
    var number = 1
    var result = 0
    for (i in 1..n) {
        var squareNum = number * number
        // определим количество цифр в квадрате числа, чтобы посчитать их индексы
        var countDigits = 0
        while (squareNum >= 1) {
            countDigits += 1
            squareNum /= 10
        }
        squareNum = number * number // восстанавливаем значение

        var squareCount = counter + countDigits
        for (k in 1..countDigits) {
            if (squareCount == n) {
                result = squareNum % 10
                return result
            }
            squareNum /= 10
            squareCount -= 1
        }
        counter += countDigits
        squareNum = number * number // восстанавливаем значение
        if (squareNum / 10 < 1) {
            if (counter == n) {
                result = squareNum % 10
                return result
            }
            squareNum /= 10
        }
        number += 1
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var number = 1
    var prevNumber = 0
    var result = 0
    var counter = 0
    for (i in 1..n) {
        var digitCounter = 0
        var tmpNumber = number
        // определяем количество цифр в числе
        while (tmpNumber >= 1) {
            digitCounter += 1
            tmpNumber /= 10
        }
        counter += digitCounter
        tmpNumber = number // восстанавливаем значение для дальнейшей обработки
        // определяем порядковый номер каждой цифры и
        // сравниваем с заданным в аргументе функции
        var tmpCounter = counter
        while (tmpNumber >= 1) {
            if (tmpCounter == n) {
                result = tmpNumber % 10
                break
            }
            tmpNumber /= 10
            tmpCounter -= 1
        }
        // генерация следующего числа последовательности
        val tmp = number
        number += prevNumber
        prevNumber = tmp
    }
    return result
}
