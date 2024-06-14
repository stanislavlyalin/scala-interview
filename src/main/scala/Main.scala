object Main {

  // Дан массив целых чисел и целое число S. Найдите такой подмассив, сумма элементов которого равна S.
  // Если таких подмассивов несколько, верните любой из них. Если такого подмассива нет, верните пустой массив.

  private def removeAt(array: List[Int], idx: Int): List[Int] = {
    val (l, r) = array.splitAt(idx)
    l ++ r.tail
  }

  private def subArrayForSum(array: List[Int], s: Int): List[Int] = {
    def recF(subArray: List[Int], reminder: List[Int]): List[Int] = {
      reminder.find(v => subArray.sum + v == s).map(v => subArray :+ v).orElse {
        reminder
          .zipWithIndex
          .map { case (v, idx) =>
            val newSubArray = subArray :+ v
            val newReminder = removeAt(reminder, idx)
            recF(newSubArray, newReminder)
          }.find(_.sum == s)
      }.getOrElse(List.empty[Int])
    }

    recF(List.empty[Int], array)
  }

  def main(args: Array[String]): Unit = {
    assert(subArrayForSum(List(1, 2, 3, 4, 5), 9).sum == 9)
    assert(subArrayForSum(List(1, 2, 3, 7, 5), 12).sum == 12)
    assert(subArrayForSum(List(1, 2, 3, 4, 5), 15).sum == 15)
    assert(subArrayForSum(List(1, 2, 3, 4, 5), 20).sum == 0)
    assert(subArrayForSum(List(-1, -2, 4, 7, 1, -3, 2), 5).sum == 5)
  }
}
