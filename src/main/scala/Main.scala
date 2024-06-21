import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, Executors}

object Main {
  def main(args: Array[String]): Unit = {
    val queue: BlockingQueue[Int] = new ArrayBlockingQueue[Int](3)

    // Исполнительный сервис для потоков-производителей
    val producerExecutor = Executors.newFixedThreadPool(2)
    // Исполнительный сервис для потоков-потребителей
    val consumerExecutor = Executors.newFixedThreadPool(2)

    // Поток-производитель
    val producer = new Runnable {
      override def run(): Unit = {
        var counter = 0
        while (true) {
          // Производим данные и кладём в очередь
          println(s"Производство: $counter")
          queue.put(counter) // Это блокирующий вызов, который ждёт, пока в очереди не освободится место
          counter += 1
          Thread.sleep(100)  // Имитируем задержку производства
        }
      }
    }

    // Поток-потребитель
    var consumer = new Runnable {
      override def run(): Unit = {
        while (true) {
          val item = queue.take() // Это блокирующий вызов, который ждёт, пока в очереди не появятся данные
          println(s"Получение: $item")
          Thread.sleep(50) // Имитируем задержку обработки
        }
      }
    }

    // Запуск потоков-производителей
    producerExecutor.execute(producer)
    producerExecutor.execute(producer)

    // Запуск потоков-потребителей
    consumerExecutor.execute(consumer)
    consumerExecutor.execute(consumer)
  }
}
