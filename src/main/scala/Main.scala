import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val arraySize = 1000000
    val numTasks  = 1000
    val random    = new Random()
    val array     = Array.fill(arraySize)(random.nextInt(100))

    // Function to measure time
    def time[R](block: => R): Long = {
      val t0 = System.nanoTime()
      block
      val t1 = System.nanoTime()
      t1 - t0
    }

    // Sequential sum
    val seqTime = time {
      val sum = array.sum
      println(s"Sequential sum: $sum")
    }
    println(s"Sequential time: $seqTime ns")

    // Parallel sum
    val parallelTime = time {
      val step    = arraySize / numTasks
      val futures = for (i <- 0 until numTasks) yield Future {
        val start = i * step
        val end   = if (i == numTasks - 1) arraySize else (i + 1) * step
        array.slice(start, end).sum
      }

      val sum = Await.result(Future.sequence(futures).map(_.sum), Duration.Inf)
      println(s"Parallel sum: $sum")
    }
    println(s"Parallel time: $parallelTime ns")
  }
}
