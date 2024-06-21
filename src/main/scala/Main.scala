import java.util.concurrent.Executors
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Main {
  def main(args: Array[String]): Unit = {
    val unboundedThreadPool           = Executors.newCachedThreadPool()
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(unboundedThreadPool)

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
    val shortFunnyTask: Future[Unit] = Future {
      println("Я только хочу напечатать 'Привет, мир', и мне больше не нужно ждать! :)")
    }

    Await.result(longTasks, Duration.Inf)
    Await.result(shortFunnyTask, Duration.Inf)

    unboundedThreadPool.shutdown()
  }
}
