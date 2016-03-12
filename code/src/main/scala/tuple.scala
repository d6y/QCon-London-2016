trait Monoid[T] {
  def empty: T
  def combine(a: T, b: T): T
}

object PairTest extends App {

  def fold[T](vs: List[T], m: Monoid[T]): T =
    vs match {
      case Nil       => m.empty
      case v :: rest => m.combine(v, fold(rest, m))
    }

  def pairMonoid[A,B](am: Monoid[A], bm: Monoid[B]) =
    new Monoid[(A,B)] {
      def empty: (A, B) = (am.empty, bm.empty)
      def combine(a: (A,B), b:(A,B)): (A,B) =
        (a, b) match {
          case ( (a1, b1), (a2, b2) ) =>
            ( am.combine(a1,a2), bm.combine(b1,b2) )
        }
  }

  val additionMonoid = new Monoid[Int] {
    def empty = 0
    def combine(a: Int, b: Int) = a +b
  }

  val stringConcatMonoid = new Monoid[String] {
    def empty = ""
    def combine(a: String, b: String) = a +b
  }

  val intPairs = List(
    (1,2),
    (3,4)
  )

  println(
    fold(intPairs, pairMonoid(additionMonoid, additionMonoid))
  )

  val mixedPairs = List(
    (1,"hello "),
    (3,"world")
  )

  println(
    fold(mixedPairs, pairMonoid(additionMonoid, stringConcatMonoid))
  )


  def sum[A,B]
    (vs: List[(A,B)])
    (implicit am: Monoid[A], bm: Monoid[B]): (A,B) =
      fold(vs, pairMonoid(am,bm))

 implicit val m1 = additionMonoid
 implicit val m2 = stringConcatMonoid

  println(
    sum(mixedPairs)
  )

}