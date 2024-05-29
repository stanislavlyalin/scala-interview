object Main {

  // Создайте иерархию классов для геометрических фигур (круг, квадрат, прямоугольник) и напишите функцию,
  // которая вычисляет площадь фигуры, используя pattern matching

  private sealed trait Shape

  private case class Circle(r: Double) extends Shape
  private case class Square(x: Double) extends Shape
  private case class Rectangle(x: Double, y: Double) extends Shape

  private def area(shape: Shape): Double = shape match {
    case Circle(r) => Math.PI * r * r
    case Square(x) => x * x
    case Rectangle(x, y) => x * y
  }

  private sealed trait ShapeTypeClass {
    def area: Double
  }

  private case class CircleTypeClass(r: Double) extends ShapeTypeClass {
    override def area: Double = Math.PI * r * r
  }

  private case class SquareTypeClass(x: Double) extends ShapeTypeClass {
    override def area: Double = x * x
  }

  private case class RectangleTypeClass(x: Double, y: Double) extends ShapeTypeClass {
    override def area: Double = x * y
  }

  private def areaTypeClass[A <: ShapeTypeClass](s: A): Double = s.area

  def main(args: Array[String]): Unit = {
    println(area(Circle(10.0)))
    println(area(Square(10.0)))
    println(area(Rectangle(10.0, 20.0)))

    println(areaTypeClass(CircleTypeClass(10.0)))
  }
}
