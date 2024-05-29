object Main {

  // Создайте простой DSL для описания графов и напишите функции для поиска в графе

  case class Node[A <: AnyVal](v: A, neighbours: List[Node[A]])

  trait Graph[A <: AnyVal] {
    def allNodes: List[Node[A]]
    def addNode(node: Node[A]): Unit
    def exists(v: A, from: Node[A]): Boolean
  }

  private class GraphImpl[A <: AnyVal] private (var nodes: List[Node[A]]) extends Graph[A] {
    override def allNodes: List[Node[A]] = nodes
    override def addNode(node: Node[A]): Unit = {
      nodes = nodes.appended(node)
    }

    override def exists(v: A, from: Node[A]): Boolean = {
      def recursiveExists(v: A, from: Node[A]): Boolean = {
        if (from.neighbours.map(_.v).contains(v)) true
        else from.neighbours.exists(node => recursiveExists(v, node))
      }
      recursiveExists(v, from)
    }
  }

  private object GraphImpl {
    def apply[A <: AnyVal] = new GraphImpl[Int](List.empty)
    def apply[A <: AnyVal](nodes: List[Node[A]]) = new GraphImpl[A](nodes)
  }

  def main(args: Array[String]): Unit = {

    val graph = GraphImpl[Int]

    val node1: Node[Int] = Node(1, List.empty)
    graph.addNode(node1)

    val node2 = Node(2, graph.allNodes)
    graph.addNode(node2)

    val node3 = Node(3, graph.allNodes)
    graph.addNode(node3)

    println(graph.exists(2, node1))  // false
    println(graph.exists(1, node3))  // true
  }
}
