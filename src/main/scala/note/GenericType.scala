package note

class A
class B extends A
class C extends B
class D extends C

/*
如果使用 T <: A 表示泛型T必须是A的子类 A是泛型T的上界
   使用 T >: A 表示泛型T必须是A的父类 A是泛型T的下界
   使用 T <% A 表示泛型T不一定必须是A的子类 如果可以隐式转化为A的子类也可以
* */

class GenericImp[E <% Comparable[E]](e:E)

class TestUp{
  def myCom[T <: B](o1:T,o2:T): Unit ={
    println("上界测试！")
  }
}

class TestDown{
  def myCom[T >: B](o1:T,o2:T): Unit ={
    println("下界测试！")
  }
}

/*在方法中使用泛型*/
class UpCompara{
  def myCom[T <: Comparable[T]](first:T, second:T): Int = {
    return first.compareTo(second)
  }
}

class Generic[T,S] (var id:T,var name:S)

object GenericType {
  def main(args: Array[String]): Unit = {
    //使用泛型时 任何类型都可以传入
    val g1 = new Generic(25,"dengchao")
    val g2 = new Generic(g1,2.0)

    val up1 = new TestUp()
    up1.myCom(new B,new C)
    up1.myCom(new D,new C)
    //up1.myCom(new D,new A)

    /*下界貌似不好使...*/
    val down1 = new TestDown()
    down1.myCom(new A,new B)
    down1.myCom(new D,new C)

    //隐式转化为可匹配的泛型
    val imp1 = new GenericImp("Tom")
    //视图界定 将Int隐式转化为RichInt类型 RichInt类型是Comparable的子类
    val imp2 = new GenericImp(100)
  }
}







