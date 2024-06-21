import java.util.concurrent.Executors
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Main {
  def main(args: Array[String]): Unit = {
    val boundedThreadPool             = Executors.newFixedThreadPool(4)
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(boundedThreadPool)

    // Длительная задача
    def longTask(id: Int): Future[Int] = Future {
      println(s"Запуск долгой задачи $id")
      Thread.sleep(5000) // Имитация длительной работы
      println(s"Завершение долгой задачи $id")
      id
    }

    // Запускаем 4 длительные задачи, которые занимают весь пул
    val longTasks = Future.sequence((1 to 4).map(longTask))

    // Запускаем короткую задачу
    val shortSadTask: Future[Unit] = Future {
      println("Я только хочу напечатать 'Привет, мир', но вынуждена ждать завершения 4-х долгих задач")
    }

    Await.result(longTasks, Duration.Inf)
    Await.result(shortSadTask, Duration.Inf)

    boundedThreadPool.shutdown()
  }
}
