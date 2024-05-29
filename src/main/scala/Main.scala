import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {

    // Напишите функцию, которая парсит строку в целое число, возвращая Option[Int]

    def toInt(s: String): Option[Int] = Try(s.toInt).toOption

    println(toInt("555"))
    println(toInt("test"))
  }
}
