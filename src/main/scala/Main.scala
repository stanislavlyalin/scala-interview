object Main {
  def main(args: Array[String]): Unit = {

    // Как обрабатывать исключения в языке Scala?

    // try-catch-finally
    try {
      val result = 10 / 0 // Это вызовет ArithmeticException
      println(result)
    } catch {
      case e: ArithmeticException => println(s"Arithmetic exception caught: ${e.getMessage}")
      case e: Exception => println(s"Exception caught: ${e.getMessage}")
    } finally {
      println("This will always be executed.")
    }

    // Try
    import scala.util.{Try, Success, Failure}

    Try(10 / 0) match {
      case Success(result) => println(result)
      case Failure(e: ArithmeticException) => println(s"Arithmetic exception caught: ${e.getMessage}")
      case Failure(e: Exception) => println(s"Exception caught: ${e.getMessage}")
    }
    println("This will always be executed.")

    Try(10 / 0).foreach(result => println(result))
    Try(10 / 0).failed.foreach(e => println(s"Arithmetic exception caught: ${e.getMessage}"))
  }
}
