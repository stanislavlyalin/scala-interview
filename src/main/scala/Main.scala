import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.DurationInt

object Main {
  def main(args: Array[String]): Unit = {

    // Если вместо IO.interruptible указать IO.blocking, то асинхронная операция не прервётся,
    // так как выполняется в отдельном, не прерываемом пуле потоков.
    // Задача, созданная через `IO {}` и `IO.delay {}`, тоже не прервётся, так как это
    // обычное вычисление в том же потоке, и нет механизма для его прерывания.
    def longBlockingTask: IO[Unit] = IO.interruptible {
      (1 to 10).foreach { i =>
        println(s"Итерация $i началась")
        Thread.sleep(1000) // Имитация длительной работы
        println(s"Итерация $i завершилась")
      }
    }

    (for {
      // Запуск долгой задачи в отдельном Fiber
      fiber <- longBlockingTask.start

      // Отмена задачи через 3 секунды
      _ <- IO.sleep(3.seconds) *> fiber.cancel

      // Печать сообщения об отмене
      _ <- IO(println("Задача была отменена"))
    } yield ()).unsafeRunSync()
  }
}
