
object Cat extends App {

  import cats._
  import cats.implicits.listInstance

  val listFold = Foldable[List]

  val addition = new Monoid[Int] {
    def empty = 0
    def combine(x: Int, y: Int) = x + y
  }

  val vs = List(1,2,3)

  println(
    listFold.fold(vs)(addition)
  )


}