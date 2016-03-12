object MergeExample {
  case class User(id: Long, name: String, email: Option[String])
  case class Update(name: Option[String], email: Option[Option[String]])
  val user = User(123L, "Bruce Wayne" ,Some("bruce@example.org"))
  val update = Update(Some("Batman"), None)
  import bulletin._
  val updated = user.merge(update)

}