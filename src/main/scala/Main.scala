object Main {

  // Есть список из интов. Вывести значения локальных максимумов
  // Для List(1, 2, 5, 4, 6, 2) ответ List(5, 6)
  // Для List(1, 2, 2, 4) ответ List() – максимумов нет
  // Для List(1, 3, 3, 1) ответ List(3) – плато из троек также является локальным максимумом

  private def localMax(l: List[Int]): List[Int] = {

    /*val len = l.length

    var maximums = List.empty[Int]
    var i = 1
    while (i < len - 1) {
      if (l(i) >= l(i - 1) && l(i) > l(i + 1)) {
        maximums = maximums :+ l(i)
      }
      i = i + 1
    }

    maximums*/

    val maximums = l.tail.foldLeft((l.head, false, List.empty[Int])) { case ((prev, prevIsGreater, maxs), v) =>
      if (v >= prev) {
        (v, true, maxs)
      } else {
        if (prevIsGreater) {
          (v, false, maxs :+ prev)
        } else {
          (v, prevIsGreater, maxs)
        }
      }
    }

    maximums._3
  }

  def main(args: Array[String]): Unit = {
    assert(localMax(List(1, 2, 5, 4, 6, 2)) == List(5, 6))
    assert(localMax(List(1, 2, 2, 4)) == List())
    assert(localMax(List(1, 3, 3, 1)) == List(3))
  }
}
