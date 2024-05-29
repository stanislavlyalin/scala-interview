object Main {

  def main(args: Array[String]): Unit = {

    // Напишите функцию, которая принимает функцию и список, и возвращает список,
    // полученный применением функции к каждому элементу исходного списка

    def applyFunTo[A, B](l: List[A])(f: A => B): List[B] = l.map(f)
    def toDouble(v: Int) = v.toDouble

    println(applyFunTo(List(1, 2, 3))(toDouble))
    println(applyFunTo(List(1, 2, 3))((x: Int) => x.toDouble))
  }
}
