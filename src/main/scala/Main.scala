object Main {

  // Реализуйте MapReduce для подсчета частоты слов в тексте

  private def map(text: String): Seq[(String, Int)] =
    text.split("\\W+").filter(_.nonEmpty).map(w => (w.toLowerCase, 1))

  private def reduce(pairs: Seq[(String, Int)]): Map[String, Int] =
    pairs.groupBy(_._1).view.mapValues(_.map(_._2).sum).toMap

  private def mapReduce(text: String): Map[String, Int] = reduce(map(text))

  def main(args: Array[String]): Unit = {
    println(mapReduce("one two two three three three"))
  }
}
