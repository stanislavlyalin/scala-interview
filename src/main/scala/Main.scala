object Main {
  def main(args: Array[String]): Unit = {

    // Напишите функцию, которая принимает произвольный объект
    // и возвращает его тип в виде строки (используя pattern matching)

    def typeToString[A](a: A): String = a match {
      case _: Int => "Int"
      case _: Double => "Double"
      case _ => "Unknown"
    }

    println(typeToString(1))
    println(typeToString(2.0))
    println(typeToString("test"))
  }
}
