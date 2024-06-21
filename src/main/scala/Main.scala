import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.syntax.all._

object Main {
  def main(args: Array[String]): Unit = {
    // Длительная задача
    def longTask(id: Int): IO[Int] = IO.blocking {
      println(s"Запуск долгой задачи $id")
      Thread.sleep(5000) // Имитация длительной работы
      println(s"Завершение долгой задачи $id")
      id
    }

    // Короткая задача
    def shortFunnyTask: IO[Unit] = IO {
      println("Я только хочу напечатать 'Привет, мир', и мне больше не нужно ждать! :)")
    }

    (for {
      _ <- List(longTask(1), longTask(2), longTask(3), longTask(4), shortFunnyTask).parSequence
    } yield ()).unsafeRunSync()
  }
}
