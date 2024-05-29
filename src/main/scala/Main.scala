object Main {
  def main(args: Array[String]): Unit = {

    // Используя монаду Option, напишите функцию, которая принимает два аргумента типа Option[Int]
    // и возвращает их сумму, если оба аргумента определены

    def sumOption(a: Option[Int], b: Option[Int]): Option[Int] = for {
      aVal <- a
      bVal <- b
    } yield aVal + bVal

    println(sumOption(Some(1), Some(2)))
    println(sumOption(Some(1), None))
    println(sumOption(None, Some(2)))
    println(sumOption(None, None))
  }
}
