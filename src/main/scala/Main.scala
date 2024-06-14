object Main {

  // Дан неотсортированный массив целых чисел. Найдите длину самой длинной последовательности
  // последовательных чисел, которая может быть создана из элементов массива.

  def maxSequenceLength(arr: List[Int]): Int = {
    def maxSequencies(pairs: List[(Int, Int)], lengths: List[Int]): Int = {
      if (pairs.length <= 1) Math.max(1, lengths.maxOption.getOrElse(1))
      else {
        val l = pairs.takeWhile { case (a, b) => b - a  == 1 }
        val b = pairs.takeWhile { case (a, b) => b - a  != 1 }
        maxSequencies(pairs.drop(l.length + b.length), lengths :+ (l.length + 1))
      }
    }

    val sortedArr = arr.sorted.distinct
    val pairs = sortedArr.dropRight(1).zip(sortedArr.tail)

    maxSequencies(pairs, List.empty[Int])
  }

  def main(args: Array[String]): Unit = {
    assert(maxSequenceLength(List(100, 4, 200, 1, 3, 2)) == 4)
    assert(maxSequenceLength(List(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)) == 9)
    assert(maxSequenceLength(List(10, 5, 12, 3, 55, 2, 1, 11)) == 3)
    assert(maxSequenceLength(List(1, 9, 3, 10, 2, 20, 4)) == 4)
  }
}
