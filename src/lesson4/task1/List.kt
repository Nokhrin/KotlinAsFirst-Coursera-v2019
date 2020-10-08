@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    v.forEach { sum += it * it }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double { // forEach
    var result = 0.0
    if (list.isEmpty()) return result
    list.forEach { result += it }
    return result / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val listMean = mean(list)
    if (list.isNotEmpty()) {
        for (i in 0 until list.size) {
            list[i] -= listMean
        }
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    if (a.isEmpty() && b.isEmpty()) return result
    for (i in a.indices) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    var xPower = x
    if (p.isEmpty()) return result
    result = p[0]
    for (i in 1 until p.size) {
        result += p[i] * xPower
        xPower *= x
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    val original = list.toList()
    var sum = list[0]
    for (i in 1 until list.size) {
        list[i] += sum
        sum += original[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    var divisor = 2
    var number = n
    while (number > 1) {
        while (number % divisor != 0) {
            divisor += 1
        }
        resultList.add(divisor)
        number /= divisor
        divisor = 2
    }
    return resultList
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var number = n
    var divider = 2
    val listDividers = mutableListOf<Int>()
    while (number > 1) {
        while (number % divider != 0) { // ищем делитель от минимального возможного,
            // поэтому результат в массиве сразу упорядочен по возрастанию
            divider += 1
        }
        listDividers.add(divider)
        number /= divider
        divider = 2
    }
    return listDividers.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    var nextN: Int
    val resultList = mutableListOf<Int>()
    while (true) { // находим цифры в base-ичной системе
        nextN = number % base
        resultList.add(0, nextN) // добавляем в начало списка, получаем порядок от старшего к младшему
        if (number < base) {
            break
        }
        number /= base
    }
    return resultList
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var number = n
    val resultList = mutableListOf<String>()
    var input: String
    if (number == 0) return "0"
    while (number != 0) {
        input = if (number % base > 9) {
            (number % base + 87).toChar().toString()
        } else {
            (number % base).toString()
        }
        resultList.add(0, input)
        number /= base
    }
    return resultList.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    digits.forEachIndexed { i, d ->
        result += d * base.toDouble().pow(digits.size - i - 1).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<String>()
    list += str.chunked(1)
    for (i in list.indices) {
        when (list[i]) {
            "a" -> list[i] = "10"
            "b" -> list[i] = "11"
            "c" -> list[i] = "12"
            "d" -> list[i] = "13"
            "e" -> list[i] = "14"
            "f" -> list[i] = "15"
            "g" -> list[i] = "16"
            "h" -> list[i] = "17"
            "i" -> list[i] = "18"
            "j" -> list[i] = "19"
            "k" -> list[i] = "20"
            "l" -> list[i] = "21"
            "m" -> list[i] = "22"
            "n" -> list[i] = "23"
            "o" -> list[i] = "24"
            "p" -> list[i] = "25"
            "q" -> list[i] = "26"
            "r" -> list[i] = "27"
            "s" -> list[i] = "28"
            "t" -> list[i] = "29"
            "u" -> list[i] = "30"
            "v" -> list[i] = "31"
            "w" -> list[i] = "32"
            "x" -> list[i] = "33"
            "y" -> list[i] = "34"
            "z" -> list[i] = "35"
        }
    }
    val numList = list.map { it.toInt() }
    var result = 0
    numList.forEachIndexed { i, d ->
        result += d * base.toDouble().pow(numList.size - i - 1).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    val romanList = mutableListOf<String>()
    val result: String
    while (number != 0) {
        while (number / 1000 >= 1) {
            romanList.add("M")
            number -= 1000
        }
        while (number / 900 >= 1) {
            romanList.add("CM")
            number -= 900
        }
        while (number / 500 >= 1) {
            romanList.add("D")
            number -= 500
        }
        while (number / 400 >= 1) {
            romanList.add("CD")
            number -= 400
        }
        while (number / 100 >= 1) {
            romanList.add("C")
            number -= 100
        }
        while (number / 90 >= 1) {
            romanList.add("XC")
            number -= 90
        }
        while (number / 50 >= 1) {
            romanList.add("L")
            number -= 50
        }
        while (number / 40 >= 1) {
            romanList.add("XL")
            number -= 40
        }
        while (number / 10 >= 1) {
            romanList.add("X")
            number -= 10
        }
        while (number / 9 >= 1) {
            romanList.add("IX")
            number -= 9
        }
        while (number / 5 >= 1) {
            romanList.add("V")
            number -= 5
        }
        while (number / 4 >= 1) {
            romanList.add("IV")
            number -= 4
        }
        while (number / 1 >= 1) {
            romanList.add("I")
            number -= 1
        }
    }
    result = romanList.joinToString(separator = "")
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var number = n
    val numberList = mutableListOf<String>()
    val result: String
    // по нисходящей определяем:
    // сколько сотен, десятков, единиц

    // разложим на слагаемые
    // нужны для определения склонения числительного
    // сотни тысяч
    if (number / 100000 in 1..9) {
        when (number / 100000) {
            1 -> numberList.add("сто")
            2 -> numberList.add("двести")
            3 -> numberList.add("триста")
            4 -> numberList.add("четыреста")
            5 -> numberList.add("пятьсот")
            6 -> numberList.add("шестьсот")
            7 -> numberList.add("семьсот")
            8 -> numberList.add("восемьсот")
            9 -> numberList.add("девятьсот")
        }
        number %= 100000
    }
    // слово "тысяч" для случая, когда тысячи и десятки тысяч равны нулю
    if (n / 100000 != 0 && number / 10000 == 0 && number / 1000 == 0) {
        numberList.add("тысяч")
        number %= 1000
    } else {
        // десятки тысяч
        if (number / 10000 in 2..9) {
            when (number / 10000) {
                2 -> numberList.add("двадцать")
                3 -> numberList.add("тридцать")
                4 -> numberList.add("сорок")
                5 -> numberList.add("пятьдесят")
                6 -> numberList.add("шестьдесят")
                7 -> numberList.add("семьдесят")
                8 -> numberList.add("восемьдесят")
                9 -> numberList.add("девяносто")
            }
            number %= 10000
            if (number / 1000 == 0) numberList.add("тысяч")
        }

        if (number / 10000 == 1) {
            number %= 10000
            when (number / 1000) {
                1 -> numberList.add("одинадцать")
                2 -> numberList.add("двенадцать")
                3 -> numberList.add("тринадцать")
                4 -> numberList.add("четырнадцать")
                5 -> numberList.add("пятнадцать")
                6 -> numberList.add("шестнадцать")
                7 -> numberList.add("семнадцать")
                8 -> numberList.add("восемнадцать")
                9 -> numberList.add("девятнадцать")
            }
            number %= 1000
            // с этими числами верна форма "тысяч"
            numberList.add("тысяч")
        }

        if (number / 10000 == 0) {
            number %= 10000
            // тысячи
            if (number / 1000 in 1..9) {
                when (number / 1000) {
                    1 -> numberList.add("одна")
                    2 -> numberList.add("две")
                    3 -> numberList.add("три")
                    4 -> numberList.add("четыре")
                    5 -> numberList.add("пять")
                    6 -> numberList.add("шесть")
                    7 -> numberList.add("семь")
                    8 -> numberList.add("восемь")
                    9 -> numberList.add("девять")
                }
                // выбираем форму слова "тысяча"
                when (number / 1000) {
                    1 -> numberList.add("тысяча") // одна
                    2, 3, 4 -> numberList.add("тысячи") // две, три, четыре
                    5, 6, 7, 8, 9 -> numberList.add("тысяч") // пять, шесть, семь, восемь, девять
                }
                number %= 1000
            }
        }
    }
    // сотни
    if (number / 100 in 1..9) {
        when (number / 100) {
            1 -> numberList.add("сто")
            2 -> numberList.add("двести")
            3 -> numberList.add("триста")
            4 -> numberList.add("четыреста")
            5 -> numberList.add("пятьсот")
            6 -> numberList.add("шестьсот")
            7 -> numberList.add("семьсот")
            8 -> numberList.add("восемьсот")
            9 -> numberList.add("девятьсот")
        }
        number %= 100
    }
    // десятки
    if (number / 10 in 2..9) {
        when (number / 10) {
            2 -> numberList.add("двадцать")
            3 -> numberList.add("тридцать")
            4 -> numberList.add("сорок")
            5 -> numberList.add("пятьдесят")
            6 -> numberList.add("шестьдесят")
            7 -> numberList.add("семьдесят")
            8 -> numberList.add("восемьдесят")
            9 -> numberList.add("девяносто")
        }
    }
    if (number / 10 == 1) {
        when (number % 10) {
            1 -> numberList.add("одинадцать")
            2 -> numberList.add("двенадцать")
            3 -> numberList.add("тринадцать")
            4 -> numberList.add("четырнадцать")
            5 -> numberList.add("пятнадцать")
            6 -> numberList.add("шестнадцать")
            7 -> numberList.add("семнадцать")
            8 -> numberList.add("восемнадцать")
            9 -> numberList.add("девятнадцать")
        }
    } else {
        number %= 10
        // единицы
        when (number) {
            1 -> numberList.add("один")
            2 -> numberList.add("два")
            3 -> numberList.add("три")
            4 -> numberList.add("четыре")
            5 -> numberList.add("пять")
            6 -> numberList.add("шесть")
            7 -> numberList.add("семь")
            8 -> numberList.add("восемь")
            9 -> numberList.add("девять")
        }
    }
    result = numberList.joinToString(separator = " ")
    return result
}
