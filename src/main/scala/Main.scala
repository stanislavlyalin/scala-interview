import scala.collection.mutable

object Main {

  // Дана строка, содержащая только символы (, ), {, }, [ и ]. Напишите функцию, которая проверяет,
  // является ли эта строка валидной. Строка считается валидной, если:
  //    Открывающие скобки должны быть закрыты скобками того же типа.
  //    Открывающие скобки должны быть закрыты в правильном порядке.

  private def isValid(s: String): Boolean = {
    val stack = mutable.Stack.empty[Char]

    s.foreach { ch =>
        if (List('{', '[', '(').contains(ch)) {
          stack.push(ch)
        }
        else if (List('}', ']', ')').contains(ch)) {
          if (stack.nonEmpty) {
            stack.top match {
              case '{' if ch == '}' => stack.pop()
              case '[' if ch == ']' => stack.pop()
              case '(' if ch == ')' => stack.pop()
              case _ => ()
            }
          }
        }
    }
    stack.isEmpty
  }

  def main(args: Array[String]): Unit = {
    assert(isValid("()"))
    assert(isValid("()[]{}"))
    assert(!isValid("(]"))
    assert(!isValid("([)]"))
    assert(isValid("{[]}"))
  }
}
