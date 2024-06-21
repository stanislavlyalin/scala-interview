import cats.effect.unsafe.implicits.global
import cats.effect.{IO, Resource}
import cats.syntax.all._

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext

object Main {
  def main(args: Array[String]): Unit = {

    // Создаем Resource для фиксированного пула потоков
    def createThreadPool: Resource[IO, (ExecutionContext, ExecutorService)] = {
      Resource.make {
        IO {
          val executor = Executors.newFixedThreadPool(4)
          val ec       = ExecutionContext.fromExecutor(executor)
          (ec, executor)
        }
      } { case (_, executor) =>
        IO(executor.shutdown())
      }
    }

    // Длительная задача
    def longTask(id: Int)(implicit ec: ExecutionContext): IO[Int] = IO {
      println(s"Запуск долгой задачи $id")
      Thread.sleep(5000) // Имитация длительной работы
      println(s"Завершение долгой задачи $id")
      id
    }.evalOn(ec)

    // Короткая задача
    def shortSadTask(implicit ec: ExecutionContext): IO[Unit] = IO {
      println("Я только хочу напечатать 'Привет, мир', но вынуждена ждать завершения 4-х долгих задач")
    }.evalOn(ec)

    createThreadPool.use { case (ec, _) =>
      implicit val implicitEc: ExecutionContext = ec
      for {
        _ <- List(longTask(1), longTask(2), longTask(3), longTask(4), shortSadTask).parSequence
      } yield ()
    }.unsafeRunSync()
  }
}
