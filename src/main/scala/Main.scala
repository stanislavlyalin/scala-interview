import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object Main {

  def main(args: Array[String]): Unit = {

    // Напишите программу, которая считает сумму чисел в массиве, используя параллельные вычисления

    val arr = (1 to 1000).toArray

    val parallelSum: Future[Int] = Future.sequence(
      Seq(
        Future { arr.slice(0, 250).sum },
        Future { arr.slice(250, 500).sum },
        Future { arr.slice(500, 750).sum },
        Future { arr.slice(750, 1000).sum }
      )
    ).map(_.sum)

    parallelSum.onComplete {
      case Success(sum) => println(sum)
      case Failure(exception) => println(exception)
    }

    Await.result(parallelSum, 10.seconds)
  }
}
