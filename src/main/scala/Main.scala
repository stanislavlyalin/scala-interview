object Main {

  // Дан отсортированный односвязный список. Напишите функцию, которая удаляет все дубликаты из списка,
  // чтобы каждый элемент появился только один раз.

  case class LinkedList(value: Int, var next: LinkedList = null)

  private def removeDuplicates(l: LinkedList): LinkedList = {
    var curr = l

    while (curr.next != null) {
      if (curr.value == curr.next.value) {
        curr.next = curr.next.next
      } else {
        curr = curr.next
      }
    }
    l
  }

  def main(args: Array[String]): Unit = {
    assert(removeDuplicates(LinkedList(1, LinkedList(1, LinkedList(2)))) == LinkedList(1, LinkedList(2)))
    assert(removeDuplicates(LinkedList(1, LinkedList(1, LinkedList(2, LinkedList(3, LinkedList(3)))))) == LinkedList(1, LinkedList(2, LinkedList(3))))
    assert(removeDuplicates(LinkedList(1, LinkedList(1, LinkedList(1)))) == LinkedList(1))
    assert(removeDuplicates(LinkedList(1, LinkedList(2, LinkedList(3)))) == LinkedList(1, LinkedList(2, LinkedList(3))))
  }
}
