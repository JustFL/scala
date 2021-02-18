package note


/**
 * 在一个.java文件中 只能有一个public修饰的类 并且这个类必须和文件名同名
 * 在一个.scala文件中 可以包含多个类 并且这些类都是public的 而且这个文件名可以和这些类都不同名
 */

/**
 * 类名后面的()就是主构造器 后面的{}就是主构造器的执行体
 * 执行体中的所有代码都会被执行
 * 主构造器()中的变量 如果被var或者val修饰 表示这个变量是成员变量
 * 有一种例外的情况 如果主构造器()中的变量没有被var或者val修饰 但是在{}方法体中使用了
 * 也会提升为成员变量 但是是private[this]修饰的
 */

/**
 * 这里给主构造器传递参数后 无参构造消失 只能传递参数进行构造
 * 正常的设计应该是主构造器无参数 辅助构造器传递需要的参数
 *
 * 主构造器可以被private修饰  class FaceObject private (var age:Int, hobby:String)
 * 这里总结一下private关键字的作用 不论是变量 方法 主构造器 如果被private修饰 表示只能在本类中或者与本类同名的object中使用
 * @param age
 * @param hobby
 */
class FaceObject(var age:Int, hobby:String) {
  /**
   * 所有成员变量能否修改看var还是val
   * 没有private修饰的成员变量表示在任何地方都可以访问
   * private修饰表示在自己类中和与自己同名的object中都可以访问
   * private[this]表示只能在自己类中进行访问
   *
   * 方法的修饰符也遵循以上规则
   */
  //在class中成员变量必须初始化
  val id = 9527
  var name = "唐伯虎"
  private var salary = 10000
  private[this] var pet = "旺财"

  /**
   * 辅助构造器的参数列表中不能出现var val
   * 辅助构造器的方法名必须是this
   * 辅助构造器的第一步必须调用主构造器或者其他辅助构造器
   */
  def this(age:Int,hobby:String, location:String) = {
    this(age,hobby)
    println("生活在"+location)
    println("执行辅助构造器语句")
  }

  println(name + "的爱好是" + hobby)
  println("主构造器的语句都被会执行")
}

object TestObject{
  val v1 = 15
  def m1 = println("这是一个单例对象的方法")

  //函数可以看作一个变量 所以变量的动作被执行了
  val f1 = println("这是一个单例对象的函数")
}


/**
 * 定义一个单例对象
 * 与类名同名的单例对象 叫做类的伴生对象
 */
object FaceObject{
  def main(args: Array[String]): Unit = {

    val f1 = new FaceObject(30,"泡妞",location = "北京")
    println(f1.age)

    /**
     * 在object中定义的所有属性和方法都是static的
     */
    println("---------------------")
    println("v1: " + TestObject.v1)
    println("---------------------")
    TestObject.m1
    println("---------------------")
    TestObject.f1
    print(TestObject.f1)

  }
}