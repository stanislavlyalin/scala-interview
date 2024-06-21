import cats.effect.ExitCode

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

object Main {
  def main(args: Array[String]): Unit = {
    val cancelled: AtomicBoolean = new AtomicBoolean(false)

    // Запускам длительную задачу и периодически проверяем, не завершена ли она
    val cancelableLongTask = Future {
      for (_ <- 1 until 10) {
        if (cancelled.get()) throw new RuntimeException("cancelled")
        Thread.sleep(1000)
      }
      42
    }

    // Поток, который через 3 секунды устанавливает флаг отмены длительной задачи
    val cancelTaskThread = Future {
      Thread.sleep(3000)
      cancelled.set(true)
    }

    cancelableLongTask.onComplete {
      case Success(value)               =>
        println(s"Задача завершилась успешно с значением $value")
      case Failure(_: RuntimeException) =>
        println("Задача была прервана")
      case Failure(_)                   =>
        println("Ошибка выполнения задачи")
    }

    Try(Await.result(cancelableLongTask, Duration.Inf))
    Await.result(cancelTaskThread, Duration.Inf)

    ExitCode(0)
  }
}
