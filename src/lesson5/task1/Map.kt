@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import ru.spbstu.kotlin.typeclass.kind
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.reflect.typeOf

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> =
    grades.toList().groupBy(
        { it.second },
        { it.first }
    )

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    a.forEach { (k, v) ->
        if (b.containsKey(k) && b[k] == v) {
            return true
        }
    }
    return false
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit =
    b.forEach { (t, u) -> a.remove(t, u) }

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> =
    a.intersect(b).toList()

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val resultMap = mutableMapOf<String, String>()
    resultMap += mapA
    for ((k, _) in mapB) {
        if (resultMap.containsKey(k)) {
            if (resultMap[k] != mapB[k]) {
                resultMap[k] = resultMap[k].toString() + ", " + mapB[k].toString()
            }
        } else {
            resultMap[k] = mapB[k].toString()
        }
    }
    return resultMap
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val tempMap = stockPrices.groupBy { it.first }
    val resultMap = mutableMapOf<String, Double>()
    for ((k, v) in tempMap) {
        var total = 0.0
        for ((_, b) in v) {
            total += b
        }
        resultMap[k] = total / v.count()
    }
    return resultMap.toMap()
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val tempMap = stuff.filter { (_, data) -> data.first == kind }
    println(tempMap)
    if (tempMap.isNotEmpty()) {
        return tempMap.minBy { it.value.second }?.key
    }
    return null
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    // приводим к нижнему регистру, удаляем повторы букв
    val wordList = word.map { it.toLowerCase() }.distinct()
    // если буква из слова не найдена, указанное слово составить нельзя
    wordList.forEach { char -> if (chars.indexOf(char) == -1) return false }
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> =
    // создаем асс. массив элемент-частота появления, фильтруем по значению частоты
    list.groupingBy { it }.eachCount().filter { it.value > 1 }

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    // слова представляем как списки букв, сортируем полученные списки по возрастанию
    val controlList = mutableListOf<String>()
    words.mapTo(controlList, { it.toMutableList().sorted().toString() })
    // группируем списки, считаем их количество
    // если хотя бы один список встречается более одно раза, значит анаграммы содержатся в заданном списке
    if (controlList.groupingBy { it }.eachCount().filter { it.value > 1 }.isNotEmpty()) {
        return true
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val resultMap = mutableMapOf<String, MutableSet<String>>()
    for ((k, v) in friends) {
//        println("$k has $v")
        if (!resultMap.containsKey(k)) {
            resultMap[k] = v.toMutableSet()
        }
        // добавление друзей как ключей финального массива
        for (name in v) { // добавляем имя как ключ в финальный массив, если его там еще нет
            if (!resultMap.containsKey(name)) {
                resultMap[name] = mutableSetOf()
            }
//            println("$k - $v - $name")
//            println(resultMap)
            // есть ли это имя в заданном списке
            // если нет, пропусть добавление его друзей, оставить множество пустым
            if (friends.containsKey(name)) {
                for (friend in friends.getValue(name)) { // добавляем друзей друга во множество
                    //                println("$name has $friend")
                    resultMap[name]?.add(friend)
                }
            }
        }
    }

    for ((key, value) in resultMap) {
//        println("${value}")
        for (person in value) {
//            println(friends[person])
            friends[person]?.forEach {
                if (it != key) {
                    value.add(it)
                }
            }
        }
        value.toSet()
    }
    resultMap.toMap()
    println(resultMap)
    return resultMap
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    if (list.isNotEmpty()) { // список не пустой
        // forEach - нерационально, потому что проверять последнее число в этой задаче - избыточно
        for (item in list[0]..list[list.size - 1]) {
            // от (позиции числа + 1) - чтобы не складывать с самим собой
            for (index in list.indexOf(item) + 1 until list.size) {
                // возвращаем числа, сумма которых первой дала искомый результат
                if (item + list[index] == number) {
                    return Pair(list.indexOf(item), index)
                }
            }
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    // создадим списки стоимости и массы
    // добавим нулевой индекс, добавим туда 0 для последуюзего удобства чтения программы (чтобы не путаться в индексах)
    val weightsList = mutableListOf<Int>()
    weightsList.add(0)
    val pricesList = mutableListOf<Int>()
    pricesList.add(0)
    treasures.forEach {
        weightsList.add(it.value.first)
        pricesList.add(it.value.second)
    }
    // проверка списков
//    println("веса: $weightsList")
//    println("цены: $pricesList")

    // создаем таблицу мемоизации для хранения комбинаций предметов от нулевого до i-го
    // таблица - двумерный массив
    var memtable = arrayOf<Array<Int>>()
    // инициируем таблицу нулями
    // нули в первой строке и первом столбце - это базовые случаи, когда не взят ни один предмет или масса равна нулю
    // количество строк = количеству предложенных предметов
    for (i in 1..treasures.count()) {
        // количество столбцов = возможным массам от 1 до допустимой с шагом 1
        var row = arrayOf<Int>()
        for (w in 1..capacity) {
            row += 0
        }
        memtable += row
    }
    // проверка нулевого массива
//    for (row in memtable) {
//        for (value in row) {
//            print("$value ")
//        }
//        println()
//    }

    // заполняем массив
    for (i in 0..treasures.count() - 1) {
        for (w in 0..capacity - 1) {
            // если ничего не взяли
            if (i == 0 || w == 0) {
                memtable[i][w] = 0
            } else if (weightsList[i] <= w) { // если масса текущего предмета укладывается в текущую вместимость
                // добавление в списки цен и весов 0 в нулевой индекс позволяет обращаться к этим спискам по i
                memtable[i][w] = // если рассчитанная новая цена меньше предыдущей, она затирается предыдущей
                    max(
                        pricesList[i] + memtable[i - 1][w - weightsList[i]],
                        memtable[i - 1][w]
                    )
            } else {
                // если масса не укладывается, копируем предыдущий элемент
                memtable[i][w] = memtable[i][w - 1]
            }
        }
    }
    // проверка массива
//    for (row in memtable) {
//        for (value in row) {
//            print("$value\t")
//        }
//        println()
//    }
    // получение названий предметов
    // идем по списку предметов в обратном порядке, вычитаем массу из вместимости, запоминаем порядковый номер
    val resultList = mutableListOf<Int>()
    var weightLeft = capacity - 1
    for (i in treasures.count() - 1 downTo 1) {
        // если в предыдущей строке в этом же столбце масса такая же, значит, объект не брали, переходим к следующему индексу
        if (memtable[i][weightLeft] != memtable[i - 1][weightLeft]) {
//            println(memtable[i][weightLeft])
            weightLeft -= weightsList[i]
            resultList.add(i - 1)
        }
    }
    val result = mutableSetOf<String>()
    resultList.forEach { result.add(treasures.toList().elementAt(it).toList()[0].toString()) }
    // трансформируем список индексов в множество названий или в пустое множество
    return if (resultList.isEmpty()) {
        emptySet()
    } else {
        result
    }
}

fun main() {
    println(
        bagPacking(
            mapOf(
                "Греча" to (3 to 20),
                "Чипсы" to (1 to 30),
                "Кубок" to (2 to 10),
                "Слиток" to (2 to 20),
                "Мороженка" to (1 to 10)
            ),
            5
        )
    )
}