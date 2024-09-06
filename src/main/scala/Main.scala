import java.util.concurrent.{ForkJoinPool, RecursiveTask}

object Main {
  // Определяем RecursiveTask для подсчета суммы элементов массива
  private class SumTask(arr: Array[Int], start: Int, end: Int) extends RecursiveTask[Int] {

    private val THRESHOLD = 1000 // Порог, при котором задача не делится на подзадачи

    override def compute(): Int = {
      if (end - start <= THRESHOLD) {
        // Прямая обработка (базовый случай)
        arr.slice(start, end).sum
      } else {
        // Делим задачу на подзадачи (fork)
        val mid       = (start + end) / 2
        val leftTask  = new SumTask(arr, start, mid)
        val rightTask = new SumTask(arr, mid, end)

        leftTask.fork() // Выполняем левую задачу асинхронно
        val rightResult = rightTask.compute() // Выполняем правую задачу синхронно
        val leftResult  = leftTask.join()     // Ожидаем завершения левой задачи

        leftResult + rightResult
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val array = Array.fill(1000000)(1)
    val pool  = new ForkJoinPool()

    val task   = new SumTask(array, 0, array.length)
    val result = pool.invoke(task)

    println(s"Sum: $result")
  }
}
