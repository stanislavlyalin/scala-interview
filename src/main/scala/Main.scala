object Main {
  def main(args: Array[String]): Unit = {

    // Напишите ленивую версию функции для вычисления n-го числа Фибоначчи

    lazy val fibs: LazyList[Int] = {
      def fibHelper(a: Int, b: Int): LazyList[Int] = {
        a #:: fibHelper(b, a + b)
      }
      fibHelper(0, 1)
    }

    def fibonacci(n: Int): Int = fibs(n)

    println(fibonacci(6))
  }
}
