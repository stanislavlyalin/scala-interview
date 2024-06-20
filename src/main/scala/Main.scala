import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.syntax.all._

import scala.concurrent.duration.DurationInt

object Main {
  def main(args: Array[String]): Unit = {
    // На вход List[IO[String]]
    // Получить IO[(List[String], List[Throwable]) – результат агрегации выполненных IO и исключений

    val talk = List(
      IO.sleep(1.second).as("red"),
      IO.raiseError(new RuntimeException("exception1")),
      IO.pure("blue"),
      IO.raiseError(new RuntimeException("exception2")),
      IO.pure("green"),
      IO.raiseError(new RuntimeException("exception3"))
    )

    val l = (for {
      s         <- talk.traverse(_.attempt)
      (thr, str) = s.partition(_.isLeft)
    } yield {
      (str.collect { case s: Right[Throwable, String] => s.value }, thr.collect { case t: Left[Throwable, String] => t.value })
    }).unsafeRunSync()

    println(l)

  }
}
