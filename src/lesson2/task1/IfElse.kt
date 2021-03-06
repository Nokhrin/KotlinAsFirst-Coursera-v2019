@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if (age % 100 in 10..14 || age % 10 in 5..9) {
        return "$age лет"
    } else if (age % 10 in 2..4) {
        return "$age года"
    }
    return "$age год"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfPath = (t1 * v1 + t2 * v2 + t3 * v3) / 2.0
    if (halfPath <= t1 * v1) {
        return halfPath / v1
    }
    if (halfPath <= t1 * v1 + t2 * v2) {
        return t1 + (halfPath - t1 * v1) / v2
    }
    return t1 + t2 + (halfPath - t1 * v1 - t2 * v2) / v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    // сначала проверяем угрозу от обеих ладей
    if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) return 3
    // проверяем угрозу от каждой ладьи в отдельности
    if (kingX == rookX1 || kingY == rookY1) return 1
    if (kingX == rookX2 || kingY == rookY2) return 2
    // если все проверки на угрозу отрицательны
    return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    // если разница X и Y координат короля и слона совпадают, то король под угрозой слона
    // если одна из координат короля и ладьи совпадают, то король под угрозой ладьи
    var kingBishopX = kingX - bishopX
    var kingBishopY = kingY - bishopY
    if (kingX < bishopX) {
        kingBishopX *= -1
    }
    if (kingY < bishopY) {
        kingBishopY *= -1
    }
    if ((kingBishopX == kingBishopY) && ((kingX == rookX) || (kingY == rookY))) {
        return 3
    }
    if (kingBishopX == kingBishopY) return 2
    if ((kingX == rookX) || (kingY == rookY)) return 1
    return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    // определяем наибольшую сторону
    var max = c
    var min1 = a
    var min2 = b

    if ((a > b) and (a > c)) {
        max = a; min1 = b; min2 = c
    } else if ((b > a) and (b > c)) {
        max = b; min1 = a; min2 = c
    }

    // определяем вид треугольника по теореме Пифагора
    if (max < min1 + min2) { // условие существования треугольника
        if (max * max < min1 * min1 + min2 * min2) return 0 // остроугольный
        if (max * max == min1 * min1 + min2 * min2) return 1 // прямоугольный
        if (max * max > min1 * min1 + min2 * min2) return 2 // тупоугольный
    }
    return -1 // такого треугольника не существует
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    // координаты внутри отрезков упорядочены по условию
    // упорядочим отрезки
    // var x1 = a // в расчете не используется
    var x2 = b
    var x3 = c
    var x4 = d
    if (c < a) {
        // x1 = c // в расчете не используется
        x2 = d
        x3 = a
        x4 = b
    }
    // считаем длину пересечения
    if (x2 - x3 >= 0) { // пересечение есть
        return if (x2 - x4 >= 0) {
            x4 - x3 // если второй отрезок принадлежит первому отрезку
        } else {
            x2 - x3 // если пересечение частичное
        }
    }
    return -1 // нет пересечения
}
