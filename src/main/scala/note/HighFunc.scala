package note

/**
 * 高阶函数分为
 * 1 函数的参数是一个函数
 * 2 函数的返回值是一个函数
 *
 */
object HighFunc {
  def main(args: Array[String]): Unit = {
    val arr1 = Array(1,2,3,4,5)

    val func1 = (x:Int, y:Int) => x + y
    val func2 = (x:Int, y:Int) => x - y
    val func3 = (x:Int) => x + 1

    println(arr1.map(func3).toBuffer)
    println(arr1.reduce(func1))
    println(arr1.reduce(func2))
    println(arr1.reduceRight(func2))
    println(arr1.fold(10)(func1))

    val sayHello = (name:String) => println("hello " + name)
    sayHello("Tom")

    println("------------------0--------------------")
    val f1 = (x:Int, y:Int) => x + y
    var f2 = (x:Int, y:Int) => x * y

    def d1(x:Int, y:Int):Int = x + y

    //函数作为函数的参数
    val f3 = (x:Int, y:Int, f:(Int, Int) => Int) => f(x,y)
    println(f3(2,3,f1))

    //方法作为函数的参数 方法不能直接作为参数 但是可以手动或者自动转化为函数 成为参数
    println(f3(2,4,d1 _))

    //函数作为方法的参数
    def d2(f:(Int,Int) => Int, x:Int, y:Int):Int = f(x, y)
    println(d2(f2,2,4))

    //方法作为方法的参数
    println(d2(d1 _,2,5))

    println("------------------1--------------------")

    //方法的返回值是一个函数
    def intro1 (name:String) = (age:Int) => println("my name is " + name + " my age is " +  age)
    intro1("dengchao")(30)

    //函数的返回值是一个函数
    val intro2 = (name:String) => ((age:Int) => println("my name is " + name + " my age is " +  age))
    val func4 = intro2("luhan")
    func4(20)
    println("------------------2--------------------")

    /**
     * currying
     * 柯里化是把接受多个参数的函数变换成接受一个单一参数(最初函数的第一个参数)的函数 并且返回接受余下的参数且返回结果的新函数的技术
     * 适用场景 当多个参数中的某些参数的值比较固定时 可以将这些参数的值先赋予 返回一个新的包含剩余参数的新函数 实现了参数的复用
     */
    def intro3(name:String,age:Int):String = {"my name is " + name + " my age is " +  age}
    def intro4(name:String)(age:Int):String = {"my name is " + name + " my age is " +  age}
    println(intro3("lichen",40))
    val intro5 = intro4("wangzulan")_
    println(intro5(50))

    println("------------------3--------------------")

    /*闭包是一个函数 func6就是一个闭包 闭包是返回值依赖于声明在函数外部的一个或者多个变量
    闭包通常来讲可以简单的认为是可以访问一个函数里面局部变量的另外一个函数
    e.g 变量id和func6是同级的 当func6定义的时候id的值是开放的 只有当func6被调用的时候 id的值才被确定
    这样由开放到闭合的过程 所以称为闭包*/
    val func5 = {
      var id = 4
      val func6 = (x:Int) => id + x

      println(func6(5))

      id = 5
      println(func6(15))
    }

    func5

    println("------------------4--------------------")

    /**
     * 参数的隐式转化基于currying 有三个特点
     * 1.隐式参数 先去全局找 找不到才使用隐式参数
     * 2.全局的隐式变量只能有一个 否则程序报错
     * 3.隐式转换只能定义在object中 如果需要可以使用import导入到当前的代码中
     */
    implicit val para:Int = 6
    def method1(x:Int)(implicit y:Int = 10) = {x + y}
    def method2(x:Int)(implicit y:Int) = {x + y}

    println(method1(2))
    println(method2(2))
  }
}
