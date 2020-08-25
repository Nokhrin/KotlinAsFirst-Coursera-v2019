fun main() {
    val user = mutableMapOf("name" to "Aleksandr", "occupation" to "programmer")
    println(user)

    user["location"] = "Russia"
    println(user)

    user.remove("occupation")
    println(user)

    user.clear()
    println(user)

    if (user.isEmpty()) {
        println("empty user")
    } else {
        println("not empty user")
    }
}
