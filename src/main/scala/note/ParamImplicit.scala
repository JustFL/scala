package note

/**
 * 参数隐式转换
 */
class Specical(var name:String)
class Older(var name:String)
class Child(var name:String)

//这里将Older类型和Child类型转化为了Spceical类型
object ParaImp{
  implicit def toSpec(old:Older):Specical = new Specical(old.name)
  implicit def toSpec(chi:Child):Specical = new Specical(chi.name)
}

object TicketHouse{
  def buyTicket(specical: Specical) = println(specical.name + " got ticket")
}

object ParamImplicit {
  def main(args: Array[String]): Unit = {
    TicketHouse.buyTicket(new Specical("dengchao"))
    import ParaImp._
    TicketHouse.buyTicket(new Older("chenhe"))
    TicketHouse.buyTicket(new Child("luhan"))
  }
}
