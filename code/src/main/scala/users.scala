case class Id(get: Long)
case class Email(get: String)
case class Fact()
case class Advert()

sealed trait Visitor

final case class Anonymous(id: Id) extends Visitor
final case class User(id: Id, info: List[Fact]) extends Visitor

object Example extends App {

  def relevantAds(is: List[Fact]) = ???
  def adRotation(id: Id) = ???

  val visitor: Visitor = User(Id(1), Nil)

  def serveAdQQQQQ(visitor: Visitor): Advert = ???

  def serveAd(visitor: Visitor): Advert =
    visitor match {
      case User(_, facts) => relevantAds(facts)
      case Anonymous(id)  => adRotation(id)
    }

}
