object Main {
  def main(args: Array[String]): Unit = {

    // Ссылочная прозрачность (Referential Transparency)

    // Выражение может быть заменено своим значением без изменения поведения программы
    // Нет побочных эффектов

    def add(a: Int, b: Int): Int = a + b

    println(add(2, 3))
    println(5)          // а можем заменить просто на результат

    var counter = 0
    def addWithSideEffect(a: Int, b: Int): Int = {
      counter += 1
      a + b
    }

    println(addWithSideEffect(2, 3)) // counter станет 1
    println(counter)

    // так заменить на результат нельзя, т.к. counter в этом случае меняться не будет -
    // addWithSideEffect не ссылочно прозрачная функция
    println(5)
  }
}
