object MyMerge extends App {
  
  val a: String = "Bruce"
  val u: Option[String] = None //Some("Batman")
  
  println(u getOrElse a)
  
  import shapeless._, syntax._
  
  trait Merge[A <: HList, B <: HList] {
    def apply(a: A, b: B) : A  
  }
  
  type User   = String         :: Option[String]         :: HNil
  type Change = Option[String] :: Option[Option[String]] :: HNil
  
  //def create[A <: HList, B <: HList](a: A, b: B): Merge[A,B] = ???
  
  def create[U, RestU <: HList, RestC <: HList]
    (u: U :: RestU, b: Option[U] :: RestC): Merge[U :: RestU, Option[U] :: RestC] =  ???
      

  
  
}