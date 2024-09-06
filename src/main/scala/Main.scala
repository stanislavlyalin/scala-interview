import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.DurationInt

object Main {
  // Функция для работы с fiber
  private def task(id: Int): IO[Unit] =
    IO.println(s"Task $id started") *>
      IO.sleep(1.second) *>
      IO.println(s"Task $id finished")

  def main(args: Array[String]): Unit = {
    val program = for {
      fiber1 <- task(1).start // Запуск задачи как fiber
      fiber2 <- task(2).start
      _      <- fiber1.join   // Ожидание завершения первой fiber
      _      <- fiber2.join   // Ожидание завершения второй fiber
    } yield ()

    program.unsafeRunSync()
  }
}
