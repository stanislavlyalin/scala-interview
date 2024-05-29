object Main {
  def main(args: Array[String]): Unit = {

    // Напишите универсальную функцию для слияния двух списков произвольного типа

    def concatenateLists[A, B](a: List[A], b: List[B]): List[Any] = a ++ b

    println(concatenateLists(List(1, 2, 3), List(4.0, 5.0)))
    println(concatenateLists(List("a", "b", "c"), List(true, false)))
  }
}
