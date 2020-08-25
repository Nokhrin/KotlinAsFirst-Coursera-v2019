@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt


/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun absLoop(v: List<Double>): Double { // цикл
    var result = 0.0
    for (element in v) {
        result += element * element
    }
    result = sqrt(result)
    return result
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun meanLoop(list: List<Double>): Double { // цикл
    var result = 0.0
    if (list.isEmpty()) return result
    for (element in list) {
        result += element
    }
    return result / list.size
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString1(n: Int): String {
    var number = n
    var divider = 2
    val listDividers = mutableListOf<Int>()
    var result = ""
    while (number > 1) {
        while (number % divider != 0) { // ищем делитель от минимального возможного,
            // поэтому результат в массиве сразу упорядочен по возрастанию
            divider += 1
        }
        listDividers.add(divider)
        number /= divider
        divider = 2
    }
    result = listDividers.joinToString(separator = "*")
    return result
}