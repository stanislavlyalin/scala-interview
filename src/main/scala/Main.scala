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
      val threads = new Array[Thread](numTasks)
      val results = new Array[Int](numTasks)

      for (i <- 0 until numTasks) {
        val taskIndex = i
        threads(i) = new Thread(new Runnable {
          def run(): Unit = {
            val start = taskIndex * step
            val end   = if (taskIndex == numTasks - 1) arraySize else (taskIndex + 1) * step
            results(taskIndex) = array.slice(start, end).sum
          }
        })
      }

      // Start all threads
      threads.foreach(_.start())

      // Wait for all threads to finish
      threads.foreach(_.join())

      // Sum up the results
      val sum = results.sum
      println(s"Parallel sum: $sum")
    }
    println(s"Parallel time: $parallelTime ns")
  }
}
