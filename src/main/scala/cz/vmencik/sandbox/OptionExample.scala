package cz.vmencik.sandbox

trait AuthorizedUser {
  def user: Option[User]
}

trait User {
  def group: Option[Group]
}

trait Group {
  def name: String
}

object OptionExample {
  
  def getGroupName1(maybeAuthUser: Option[AuthorizedUser]): Option[String] = {
    maybeAuthUser.flatMap(_.user.flatMap(_.group.map(_.name)))
  }

  def getGroupName2(maybeAuthUser: Option[AuthorizedUser]): Option[String] = {
    maybeAuthUser.flatMap { authUser =>
      authUser.user.flatMap { user =>
        user.group.map { group =>
          group.name
        }
      }
    }
  }

  def getGroupName3(maybeAuthUser: Option[AuthorizedUser]): Option[String] = {
    for {
      authUser <- maybeAuthUser
      user <- authUser.user
      group <- user.group
    } yield group.name
  }
  
}