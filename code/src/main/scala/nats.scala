object Natty extends App {
  
  sealed trait Nat 
  sealed trait Succ[P <: Nat] extends Nat 
  sealed trait Zero extends Nat 
  
  type One = Succ[Zero]
  type Two = Succ[One]

  implicitly[Succ[Zero] =:= One]
  implicitly[Succ[One] =:= Succ[Succ[Zero]]]

 /*
  implicitly[Succ[Zero] =:= Two]
  // error: Cannot prove that Succ[Zero] =:= Two.
  //       implicitly[Succ[Zero] =:= Two]
  */

  class Sized[N <: Nat](elements: String*)

  object Sized {
    def apply(a: String)            : Sized[One] = new Sized(a)
    def apply(a: String, b: String) : Sized[Two] = new Sized(a,b)
    // ...
  }

  def csv[N <: Nat](
    hdrs: Sized[N],
    rows: List[Sized[N]]
  ) = "...todo.."

  val text = csv( Sized("Date"), List(Sized("Monday")) )
  
  // val fail = csv( Sized("Date", "Metric"), List(Sized("Monday")) )
  /*
  <console>:20: error: type mismatch;
   found   : Sized[Two]
      (which expands to)  Sized[Succ[Succ[Zero]]]
   required: Sized[Succ[_ >: Zero with One <: Nat]]
      (which expands to)  Sized[Succ[_ >: Zero with Succ[Zero] <: Nat]]
  Note: Two <: Succ[_ >: Zero with One <: Nat], but class Sized is invariant in type N.
  You may wish to define N as +N instead. (SLS 4.5)
           val fail = csv( Sized("Date", "Metric"), List(Sized("Monday")) )
                                ^
  <console>:20: error: type mismatch;
   found   : List[Sized[One]]
      (which expands to)  List[Sized[Succ[Zero]]]
   required: List[Sized[Succ[_ >: Zero with One <: Nat]]]
      (which expands to)  List[Sized[Succ[_ >: Zero with Succ[Zero] <: Nat]]]
           val fail = csv( Sized("Date", "Metric"), List(Sized("Monday")) )
                                                      ^
   */
}