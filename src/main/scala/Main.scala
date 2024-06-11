import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main {
  def main(args: Array[String]): Unit = {

    // Promise и Future

    // Promise - контейнер для Future. Так и не смог понять, в каких случаях Promise полезен

    // Future - отложенное значение. Расчёт значения запускается в отдельном потоке

    Future {
      Thread.sleep(1000)
      println("Future завершена")
      42
    }.onComplete {
      case Success(value) => println(s"Future успешно вернула $value")
      case Failure(exception) => println(s"Ошибка в Future ${exception.getMessage}")
    }

    println("Выполняется сразу после создания Future")

    Thread.sleep(2000)

    println("К этому моменту Future завершена и напечатала строку")
  }
}
