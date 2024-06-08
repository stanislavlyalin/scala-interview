object Main {
  def main(args: Array[String]): Unit = {

    // Наследование и композиция

    // Наследование:
    // * создаём новые классы путём наследования от существующих, расширяя их
    // * переопределение методов - реализуем специфику в потомках
    // * доступ к членам суперкласса - можем вызывать методы родителя
    // * полиморфизм - можем подставлять потомков везде, где нужен суперкласс

    // + можно строить логичные иерархии типов
    // + легко специфицировать поведение потомков через переопределение методов

    // - могут получаться жёсткие иерархии
    // - код сложно использовать вне иерархии
    // - изменения в суперклассах могут ломать поведение наследников

    abstract class Animal(val name: String) {
      def sound: String

      def makeSound(): Unit = println(s"$name makes a sound: ${sound}")
    }

    class Dog(name: String) extends Animal(name) {
      override def sound: String = "Woof"
    }

    class Cat(name: String) extends Animal(name) {
      override def sound: String = "Meow"
    }

    val dog = new Dog("Buddy")
    dog.makeSound() // Output: Buddy makes a sound: Woof

    val cat = new Cat("Whiskers")
    cat.makeSound() // Output: Whiskers makes a sound: Meow

    // Композиция:
    // * включаем одни классы в другие. Мы как из кирпичиков Lego строим новые объекты

    // + простое повторное использование кода
    // + меньше зависимостей от иерархий классов

    // - много кода для связывания компонентов

    trait SoundMaker {
      def makeSound(): Unit
    }

    class DogComposition(val name: String) extends SoundMaker {
      override def makeSound(): Unit = println(s"$name says: Woof")
    }

    class CatComposition(val name: String) extends SoundMaker {
      override def makeSound(): Unit = println(s"$name says: Meow")
    }

    class Zoo {
      private var animals: List[SoundMaker] = List()

      def addAnimal(animal: SoundMaker): Unit = {
        animals = animal :: animals
      }

      def makeAllSounds(): Unit = {
        animals.foreach(_.makeSound())
      }
    }

    val zoo = new Zoo
    val dogComposition = new DogComposition("Buddy")
    val catComposition = new CatComposition("Whiskers")

    zoo.addAnimal(dogComposition)
    zoo.addAnimal(catComposition)
    zoo.makeAllSounds()
    // Output:
    // Buddy says: Woof
    // Whiskers says: Meow
  }
}
