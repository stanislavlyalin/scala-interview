object Main {
  def main(args: Array[String]): Unit = {

    // Функции высшего порядка в Scala
    // Широко применяются в функциональном программировании. Например, map, reduce, collect, foldLeft и т.д.

    // Функция, которая принимает другую функцию
    def applyOperation(a: Int, b: Int, op: (Int, Int) => Int): Int = op(a, b)
    println(applyOperation(5, 10, _ + _))
    println(applyOperation(5, 10, _ * _))

    // Функция, которая возвращает функцию как результат
    def createAdder(x: Int): Int => Int = (y: Int) => x + y
    val add5 = createAdder(5)
    println(add5(10))

    // Функции высшего порядка из стандартной библиотеки Scala

    // map - применяет функцию к каждому элементу коллекции и возвращает новую коллекцию с результатами
    val numbers = List(1, 2, 3, 4)
    val doubled = numbers.map(_ * 2) // List(2, 4, 6, 8)

    // flatMap - применяет функцию к каждому элементу коллекции и объединяет полученные коллекции в одну (устраняет вложенность)
    val nested = List(List(1, 2), List(3, 4))
    val flattened = nested.flatMap(identity) // List(1, 2, 3, 4), можно просто .flatten

    // filter - возвращает новую коллекцию, содержащую только те элементы, которые удовлетворяют предикату
    val even = numbers.filter(_ % 2 == 0) // List(2, 4)

    // reduce - объединяет элементы коллекции, используя заданную бинарную функцию. Коллекция должна быть непустой
    val sumReduce = numbers.reduce(_ + _) // 10

    // foldLeft - объединяет элементы коллекции с начальным значением, используя заданную бинарную функцию, слева направо
    val sumFoldLeft = numbers.foldLeft(0)(_ + _) // 10

    // foldRight - то же самое, но справа налево

    // collect - применяет частичную функцию к элементам коллекции и возвращает новую коллекцию с результатами применения этой функции
    val evens = numbers.collect { case x if x % 2 == 0 => x * 2 } // List(4, 8)

    // partition - разделяет коллекцию на два списка в зависимости от предиката
    val (evenPartition, oddPartition) = numbers.partition(_ % 2 == 0) // (List(2, 4), List(1, 3))

    // groupBy - группирует элементы коллекции по ключу, определенному функцией
    val words = List("apple", "banana", "avocado")
    val grouped = words.groupBy(_.charAt(0)) // Map(a -> List(apple, avocado), b -> List(banana))

    // find - возвращает первый элемент коллекции, который удовлетворяет предикату, как Option
    val firstEven = numbers.find(_ % 2 == 0) // Some(2)

    // exists - возвращает true, если хотя бы один элемент коллекции удовлетворяет предикату
    val hasEven = numbers.exists(_ % 2 == 0) // true

    // forall - возвращает true, если все элементы коллекции удовлетворяют предикату
    val allGreaterZero = numbers.forall(_ > 0)

    // takeWhile - возвращает самую длинную начальную часть коллекции, в которой все элементы удовлетворяют предикату
    // val numbers = List(2, 4, 5, 6)
    val evensTakeWhile = numbers.takeWhile(_ % 2 == 0) // List(2, 4)

    // dropWhile - удаляет самую длинную начальную часть коллекции, в которой все элементы удовлетворяют предикату
    // val numbers = List(2, 4, 5, 6)
    val remaining = numbers.dropWhile(_ % 2 == 0) // List(5, 6)

    // zip - объединяет два списка в список пар
    val a = List(1, 2, 3)
    val b = List("a", "b", "c")
    val zipped = a.zip(b) // List((1, "a"), (2, "b"), (3, "c"))
  }
}
