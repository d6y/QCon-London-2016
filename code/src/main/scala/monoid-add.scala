import cats._
import cats.std.all._
import cats.implicits._

object Add extends App {

  val addition = new Monoid[Int] {
    def empty = 0
    def combine(x: Int, y: Int) = x + y
  }

  var a = addition.empty
  for { v <- List(1,2,3) }
   a = addition.combine(a, v)
  println(a)

  // Fold:

  println(
     List(1,2,3).fold(0) { (a, v) => a+v }
   )

  def fold(vs: List[Int], empty: Int): Int =
    vs match {
      case Nil       => empty
      case v :: rest => v + fold(rest, empty)
    }

  println(
   fold(List(1,2,3), 0)
  )

 def foldM(vs: List[Int], m: Monoid[Int]): Int =
   vs match {
     case Nil       => m.empty
     case v :: rest => m.combine(v, foldM(rest, m))
   }

  println(
   foldM(List(1,2,3), addition)
  )

  def foldM2[T](vs: List[T], m: Monoid[T]): T =
    vs match {
      case Nil       => m.empty
      case v :: rest => m.combine(v, foldM2(rest, m))
    }

  println(
    foldM2(List(1,2,3), addition)
  )

}