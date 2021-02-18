package note

/**
 * trait可以定义抽象成员 抽象方法 具体成员 具体方法
 */
trait t1 {
  val age:Int
  val name:String = "邓超"
  def say:Unit
  def eat(food:String):Unit = println(name + " eat " + food)
}

trait t2 {
  val hobby:String = "泡妞"
}

class c1 extends t1 with t2{
  /**
   * 当继承trait时 必须初始化未初始化的变量 实现其未实现的方法
   */
  val age: Int = 18
  def say: Unit = println("i have nothing to say")
}

object TraitStudy {
  def main(args: Array[String]): Unit = {
    val c: c1 = new c1
    c.eat("火锅")
    println(c.age + c.hobby)

    /**
     * trait混入和调用链
     */
  }
}
