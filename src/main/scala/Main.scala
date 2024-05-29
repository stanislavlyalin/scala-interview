import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {

    // Напишите функцию, которая принимает список чисел и возвращает их сумму, используя рекурсию и хвостовую рекурсию

    def sum(nums: List[Int]): Int = {
      nums match {
        case h :: Nil => h
        case h :: tail => h + sum(tail)
      }
    }

    @tailrec
    def sumTail(nums: List[Int], total: Int = 0): Int = {
      if (nums.isEmpty) total else sumTail(nums.tail, total + nums.head)
    }

    val nums = List(1, 2, 3, 4, 5)
    println(sum(nums))
    println(sumTail(nums))
  }
}
