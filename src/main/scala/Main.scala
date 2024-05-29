object Main {

  // Реализуйте typeclass для сериализации объектов в строку и примеры его использования для различных типов данных

  private trait Serializable {
    def toStr: String
  }

  private class MySuperDuperType(val i: Int) extends Serializable {
    override def toStr: String = i.toString
  }

  private class MyAwesomeType(val d: Double, val s: String) extends Serializable {
    override def toStr: String = s"$d, $s"
  }

  private def printType[T <: Serializable](v: T): Unit = println(v.toStr)

  def main(args: Array[String]): Unit = {
    printType(new MySuperDuperType(5))
    printType(new MyAwesomeType(2.0, "test"))
  }
}
