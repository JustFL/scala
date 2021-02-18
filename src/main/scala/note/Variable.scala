package note

object Variable {
  def main(args:Array[String]):Unit = {
    println("hello world")

    //scala中没有基本类型 都是引用类型 scala除了会引用java中的类型之外 还有一些自己定义的类型
    //scala有自动推断功能 但是一旦声明完成 就不能更换类型 除非重新声明
    var a = "huangbo"
    var b = 1.0
    var c = 1.0f
    //a = 1

    //scala变量可以不用声明类型 也可以指定类型
    var d:Float = 4

    //val关键字 相当于java的常量
    val e = "dengchao"
    //e = "lichen"

    //延迟 声明时不会计算 只有当使用这个变量的时候才会算出结果 并且scala中的运算符都是方法
    lazy val f = 3 + 4
    lazy val g = 3.+(4)
    println(f)
    println(g)

    var str1 = "\"huangbo\""
    println(str1)
    str1 = """\t\n\t\m\a\b"""
    println(str1)

    //流程控制 说明了scala所有的操作都有返回值 {}是代码块 代码块中的最后一行就是返回值
    var aa = 6
    var bb = 5
    var cc = if (aa > bb) bb else aa
    println(cc)

    println("-------------for-------------")
    //for循环
    for (x <- 1 to 5)
      println(x)

    //每隔2个打印
    for (x <- 1 to (5, 2))
      println(x)

    //yield
    val arr1 = for (x <- 1 to (5, 2))
      yield x * 2

    for (index <- 0 until arr1.length)
      println(arr1(index))

    for (x <- 1 to 3; y <- 1 to 3 if x == y)
      println(x * y)

    //方法 方法的返回值可以省略
    def Max(x:Int, y:Int):Int = {
      if(x >= y)
        x
      else
        y
    }
    println(Max(2,4))

    def sum(x:Int, y:Int):Int = {
      x + y
    }

    //函数
    var f1 = (x:Int,y:Int) => x + y
    println(f1(2,2))
    println(sum(2,2))


    println("-------------将函数作为参数传递给方法-------------")
    def Op(f:(Int,Int) => Int) = f(6,2)

    var f2 = (x:Int,y:Int) => x * y
    var f3 = (x:Int,y:Int) => x / y

    println(Op(f2))
    println(Op(f3))


    def Op1(f:(Int,Int) => Int, z:Int) = f(6,2) + z
    println(Op1(f3,5))

    def Op2(f:(Int,Int) => Int) = {var aa = 10; bb = 100; f(aa,bb)}
    println(Op2(f2))


    /**
     * scala中方法和函数中都可以自行规定运算逻辑
     * 并且可以将函数作为变量传递
     * 但是方法不可以直接作为变量传递 需要转化为函数
     * */

    println("-------------将方法转为函数-------------")
    def change(x:Int) = x + 1
    var fa = change _

    println(change(1))
    println(fa(2))

    println("-------------wordcount-------------")
    //_的含义
    val array = Array(1,2,3,4,5)
    val array1 = (array.map(_ + 1))
    for(x <- array1)
      println(x)

    var lines = Array("a b","b c","c c")
    var result = lines.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(x => (x._1,x._2.length)).toList.sortBy(_._2).reverse
    for (x <- result)
      println(x)
    //println(result.mkString(" "))

  }
}
