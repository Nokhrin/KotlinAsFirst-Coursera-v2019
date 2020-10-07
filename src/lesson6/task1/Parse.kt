@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.NumberFormatException
import kotlin.reflect.typeOf

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun mainConsole() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val input = str.split(" ").toMutableList()
    // проверим, что введенная строка состоит из трёх элементов
    if (input.size != 3) return ""
    // определим номер месяца с помощью словаря
    val monthsNumbers = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    // проверим написание имени месяца,
    // если имя некорректно, возвращаем пустую строку
    if (input[1] !in monthsNumbers.keys) {
        return ""
    }
    // проверяем номер введенного дня
    // должен быть < 0 и меньше принятого для заданного месяца
    val days = mapOf(
        "января" to 0..31,
        "февраля" to 0..29,
        "марта" to 0..31,
        "апреля" to 0..30,
        "мая" to 0..31,
        "июня" to 0..30,
        "июля" to 0..31,
        "августа" to 0..31,
        "сентября" to 0..30,
        "октября" to 0..31,
        "ноября" to 0..30,
        "декабря" to 0..31
    )
    if (input[0].toInt() in days[input[1]] ?: error("")) {
        if (input[0].toInt() < 10) {
            // добавляем ведущий ноль числам от 1 до 9
            input[0] = "0" + input[0]
        }
        if (input[0] == "29" && input[1] == "февраля") {
            // проверяем год на високосность
            val year = input[2].toInt()
            // формулировка "по тексту": високосный, если кратен 4 и не кратен 100 или если кратен 400
//            if (!((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
//                return ""
//            }
            // условие без отрицания - для тренировки
            // если кратен 100 и не кратен 400 или не кратен 4
            if ((year % 100 == 0 && year % 400 != 0) || year % 4 != 0) {
                return ""
            }
        }
        return input[0] + "." + monthsNumbers[input[1]] + "." + input[2]
    }
    return ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val input = digital.split(".").toMutableList()
    // если в полученном списке количество элементов меньше или больше 3, то формат неверен
    if (input.size != 3) return ""
    // проверяем, являются ли полученные подстроки числами
    input.forEach {
        // перехват ошибки конвертации строки в число
        // если элемент не является числом, то формат неверен
        try {
            it.toInt()
        } catch (e: NumberFormatException) {
            return ""
        }
    }
    // проверим значение месяца
    if (input[1].toInt() !in 1..12) return ""
    // проверим значение числа
    if (input[1].toInt() !in 1..31) return ""
    // на этом этапе считаем, что формат верный
    // переходим к интерпретации ввода
    // введем словарь для имен месяцев
    val monthNames = mapOf(
        "01" to "января",
        "02" to "февраля",
        "03" to "марта",
        "04" to "апреля",
        "05" to "мая",
        "06" to "июня",
        "07" to "июля",
        "08" to "августа",
        "09" to "сентября",
        "10" to "октября",
        "11" to "ноября",
        "12" to "декабря"
    )
    // введем словарь для допустимых диапазонов чисел в месяцах
    val days = mapOf(
        "01" to 0..31,
        "02" to 0..29,
        "03" to 0..31,
        "04" to 0..30,
        "05" to 0..31,
        "06" to 0..30,
        "07" to 0..31,
        "08" to 0..31,
        "09" to 0..30,
        "10" to 0..31,
        "11" to 0..30,
        "12" to 0..31
    )
    // проверим вхождение числа в указанный месяц
    if (!(days[input[1]]?.contains(input[0].toInt()))!!) return ""
    // проверим високосность
    if (input[0] == "29" && input[1] == "02") {
        val year = input[2].toInt()
        // если 29 февраля в невисокосном году, ввод неверен
        if ((year % 100 == 0 && year % 400 != 0) || year % 4 != 0) return ""
    }
    // на этом этапе ввод считаем корректным
    input[0] = input[0].toInt().toString() // избавляемся от ведущего 0 в числе
    input[1] = monthNames[input[1]].toString()
    return input.joinToString(separator = " ")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    // создадим список разрешенных символов
    // будем использовать его для определения допустимости символов
    val legalSymbols = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "-", "+", "(", ")")
    // представим введеную строку в виде списка ее символов
    val input = phone.chunked(1)
    // если находим недопустимый символ, прекращаем работу
    input.forEach {
        if (it !in legalSymbols) return ""
    }
    // удаляем все символы, кроме цифр и скобок
    val filteredInput = input.filter { it in listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9") }
    // проверяем скобки - они должны идти парой строго ( ) и с числом между друг другом
    // ( и ) должны встречаться только единожды
    if (input.filter { it == "(" }.count() > 1 ||
        input.filter { it == ")" }.count() > 1
    ) {
        return ""
    }
    // если скобка ( идет после ) или между скобками ( и ) нет символом, то ввод неверен
    if (input.indexOf("(") > input.indexOf(")") || input.indexOf(")") - input.indexOf("(") == 1) {
        return ""
    }
    var result = ""
    // напишем плюс, если он задан на входе
    if (input[0] == "+") result += "+"
    result += filteredInput.joinToString(separator = "")
    return result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int? {
    val inputList = jumps.split(" ").toList()
    val numbers = mutableListOf<Int>()
    inputList.forEach {
        // если нашли число
        if (it.matches("-?\\d+(\\.\\d+)?".toRegex())) {
            numbers.add(it.toInt()) // конвертируем строку в число и запоминаем
        } else {
            // если нашли запрещенную строку
            if (it != "-" && it != "%") return -1 // завершаем выполнение
        }
    }
    // если ввод не содержит чисел
    if (numbers.isEmpty()) return -1 // завершаем выполнение
    // находим максимальное
    return numbers.max()
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int? {
    val regex = "\\d+ \\+".toRegex() // регулярка для поиска "от одной и более цифры, пробел, +"
    val matchedResults = mutableListOf<Int>()
    regex.findAll(jumps).forEach {
        // соберем найденные подходящие значения, конвертируем в Int
        matchedResults.add(it.value.split(" ")[0].toInt())
    }
    if (matchedResults.isEmpty()) return -1
    return matchedResults.max()
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = TODO()

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
