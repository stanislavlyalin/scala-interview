object Main {
  def main(args: Array[String]): Unit = {

    // Напишите функцию, которая принимает список строк и возвращает словарь,
    // где ключами являются строки, а значениями — их длины

    def stringsToMap(s: List[String]): Map[String, Int] = s.map(item => item -> item.length).toMap

    println(stringsToMap(List("Hello", "world", "test")))
  }
}
