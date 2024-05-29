object Main {

  // Реализуйте бесконечный стрим простых чисел

  private def isPrime(n: Int): Boolean = {
    if (n < 1) false
    else if (n == 2) true
    else !(2 until Math.sqrt(n).toInt + 1).exists(i => n % i == 0)
  }

  private def primes: LazyList[Int] = {
    def nextPrime(current: Int): Int = LazyList.from(current + 1).filter(isPrime).head

    LazyList.iterate(1)(nextPrime)
  }

  def main(args: Array[String]): Unit = {
    println(primes.take(10).toList)
  }
}
