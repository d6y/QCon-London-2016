import shapeless._
import syntax.sized._

object SizedExample {

  def csv[N <: Nat](
    hdrs: Sized[Seq[String], N],
    rows: List[Sized[Seq[String], N]]
  ): String = ???

  csv(
    Sized("a", "b"),
    List(
      Sized("1", "2"),
      Sized("3", "4")
    )
  )


}