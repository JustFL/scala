package note

import scala.util.Random

/**
 * 样例类
 * 1、构造器中的参数如果不被声明为 var 的话，它默认的话是 val 类型的，但一般不推荐将构造器中的参数声明为 var
 * 2、自动创建伴生对象，同时在里面给我们实现子 apply 方法，使得我们在使用的时候可以不直接显示地 new 对象
 * 3、伴生对象中同样会帮我们实现 unapply 方法，从而可以将 case class 应用于模式匹配
 * 4、实现自己的 toString、hashCode、copy、equals 方法
 * 5、case class 主构造函数里面没有修饰符，默认的是 val
 *
 * @param id
 * @param name
 * @param age
 */
case class Student(id:Int,name:String,age:Int)
case class Teacher(id:Int,name:String)

class ModeCheck{

}

object ModeCheck extends App{
  /*模式匹配的基本语法*/
  val arr1 = Array(1,2,3)
  val no1 = arr1(Random.nextInt(arr1.length))
  no1 match {
    case 1 => println("dengchao")
    case 2 => println("luhan")
    case _ => println("chenhe")
  }

  /*模式匹配对各种类型进行匹配*/
  val arr2 = Array(1,true,"dengchao",Array(1,2,3))
  val no2 = arr2(Random.nextInt(arr2.length))
  no2 match {
    case a:Int => println("匹配到了数字: " + a)
    case b:Boolean => println("匹配到了boolean: " + b)
    case c:String => println("匹配到了字符串: " + c)
    case d:Array[Int] => println("匹配到了数组: " + d.toBuffer)
  }

  /**
   * 模式匹配对存在或者不存在进行匹配 map.get()方法如果key值存在
   * 返回一个Some()包装好的值 不存在就返回一个None
   */
  val map1 = Map(("a",1),("b",2))
  val no3 = map1.get("c")
  no3 match {
    case Some(x) => println("取到map中的值为"+x)
    case None => println("map中不存在对应的key")
  }


  /*模式匹配某种复杂数据类型对应位置上的值*/
  val list1 = List(1,2,3,4,5,6)
  list1 match {
    case List(1,2,x,4,5) => println(x)
    case 1::2::Nil => println("这是一个只有1，2两个元素的list")
    case 1::2::x => println(x)
    case _ => println("无法识别的list")
  }

  /*样例类 匹配自定义类型*/
  val obj1:AnyRef = Teacher(5,"dengchao")
  obj1 match {
    case Teacher(id, name) => println("This is a teacher " + id + name)
    case Student(id, name, age) => println("this is a student " + id + name + age)
  }

  /*偏函数原型*/
  def func1(t:Int):String = {
    t match {
      case 1 => println("1");"1"
      case 2 => println("2");"2"
    }
  }
  func1(2)
  /*偏函数*/
  val flag = true
  def func2:PartialFunction[Int, String] = {
    case 1 => "1"
    case 2 => "2"
  }
  println("func2:"+ func2(2))
}
