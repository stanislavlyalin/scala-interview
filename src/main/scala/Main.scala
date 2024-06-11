import scala.collection.mutable

object Main {
  def main(args: Array[String]): Unit = {

    // Иммутабельные и мутабельные типы

    // Иммутабельные: не изменяются. Безопасно в многопоточных приложениях, но больше памяти и хуже производительность)

    val list = List(1, 2, 3)
    val newList = list :+ 4

    println(list) // Output: List(1, 2, 3)
    println(newList) // Output: List(1, 2, 3, 4)

    val map = Map("a" -> 1, "b" -> 2)
    val newMap = map + ("c" -> 3)

    println(map) // Output: Map(a -> 1, b -> 2)
    println(newMap) // Output: Map(a -> 1, b -> 2, c -> 3)

    val set = Set(1, 2, 3)
    val newSet = set + 4

    println(set) // Output: Set(1, 2, 3)
    println(newSet) // Output: Set(1, 2, 3, 4)

    case class Person(name: String, age: Int)

    val person = Person("Alice", 30)
    val olderPerson = person.copy(age = 31)

    println(person) // Output: Person(Alice,30)
    println(olderPerson) // Output: Person(Alice,31)

    // Мутабельные: изменяются. Производительнее и меньше памяти, но нужно синхронизировать в многопоточных приложениях

    val array = Array(1, 2, 3)
    array(0) = 10

    println(array.mkString(", ")) // Output: 10, 2, 3

    val listBuffer = mutable.ListBuffer(1, 2, 3)
    listBuffer += 4

    println(listBuffer) // Output: ListBuffer(1, 2, 3, 4)

    val mutableMap = mutable.Map("a" -> 1, "b" -> 2)
    mutableMap += ("c" -> 3)

    println(mutableMap) // Output: Map(a -> 1, b -> 2, c -> 3)

    class MutablePerson(var name: String, var age: Int)

    val mutablePerson = new MutablePerson("Bob", 25)
    mutablePerson.age = 26

    println(mutablePerson) // Output: MutablePerson@<hashcode>(Bob,26)
  }
}
