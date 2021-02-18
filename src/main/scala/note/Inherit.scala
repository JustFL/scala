package note

abstract class Animal{
  /**
   * 抽象类中的成员变量是否初始化随意 但是实体类中的必须初始化
   * 抽象类中可以定义抽象方法和具体方法
   */
  var name:String
  var location:String = "Earth"

  def eat(howmuch:Int):Int = {
    println(howmuch)
    5
  }
  def sleep()
}

class Tiger extends Animal{
  var name: String = "Tiger"
  override def sleep(): Unit = println("Tiger is sleeping")

  /**
   * override关键字 如果父类已经实现了的方法或者初始化的成员变量
   * 子类需要重写的时候必须要加override
   */

  override def eat(howmuch: Int): Int = {
    println("Tiger is eating "+howmuch)
    15
  }
}

object Inherit extends App {
  var t = new Tiger
  println(t.eat(10000))
  t.sleep


  println(t.isInstanceOf[Tiger])
  println(t.isInstanceOf[Animal])

  //类型转化 只能从父类向子类转化 因为子类范围大 不能转向父类
  println(t.asInstanceOf[Animal])

  //获取类的全限定名称
  println(classOf[Tiger])
}
