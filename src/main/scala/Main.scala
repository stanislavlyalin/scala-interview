import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.DurationInt

object Main {
  // "Корутина" A
  private def coroutineA: IO[Unit] = for {
    _ <- IO.println("Coroutine A: Начало")
    _ <- IO.sleep(1.second) // Приостанавливаем выполнение на 1 секунду
    _ <- IO.println("Coroutine A: Продолжение после паузы")
    _ <- IO.sleep(1.second)
    _ <- IO.println("Coroutine A: Завершение")
  } yield ()

  // "Корутина" B
  private def coroutineB: IO[Unit] = for {
    _ <- IO.println("Coroutine B: Начало")
    _ <- IO.sleep(500.millis) // Приостанавливаем выполнение на 500 миллисекунд
    _ <- IO.println("Coroutine B: Продолжение после паузы")
    _ <- IO.sleep(500.millis)
    _ <- IO.println("Coroutine B: Завершение")
  } yield ()

  def main(args: Array[String]): Unit = {
    val program = for {
      fiberA <- coroutineA.start // Запускаем coroutineA асинхронно
      fiberB <- coroutineB.start // Запускаем coroutineB асинхронно
      _      <- fiberA.join      // Ждем завершения coroutineA
      _      <- fiberB.join      // Ждем завершения coroutineB
    } yield ()

    program.unsafeRunSync()
  }
}
