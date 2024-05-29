object Main {

  // Реализуйте неизменяемую (immutable) очередь (queue) с методами enqueue и dequeue
  class Queue[A] private (items: List[A]) {
    def enqueue(item: A): Queue[A] = Queue[A](items :+ item)
    def dequeue: (Option[A], Queue[A]) = items match {
      case h :: tail => (Some(h), Queue[A](tail))
      case Nil => (None, Queue[A]())
    }
    override def toString: String = s"Queue(${items.mkString(", ")})"
  }

  object Queue {
    def apply[A]() = new Queue[A](List.empty[A])
    def apply[A](items: List[A]) = new Queue[A](items)
  }

  def main(args: Array[String]): Unit = {
    val queue = Queue(List(1, 2, 3))
    println(queue.enqueue(4))
    println(queue.dequeue)
  }
}
