package note

/**
 * 泛型 逆变和协变
 */

//泛型类：指定类可以接受任意类型参数
//泛型方法：指定方法可以接受任意类型参数
class Person[T](var name:T)
class Worker[T,S](name:T,var age:S) extends Person(name)


//在泛型的基础上 对泛型的范围进行进一步的界定 从而缩小泛型的具体
//以下代码编译不通过 因为 泛型T并不一定具备compareTo方法
//class GenericBorder {
//  def compare[T](first:T,second:T) = {
//    if (first.compareTo(second)>0)
//      first
//    else
//      second
//  }
//}

//修改为T<:Comparable[T]compareTo方法中如果输入的类型处于Comparable类对应继承层次结构中 则是合法的 否则的话编译会报错
class GenericBorder {
  def compare[T <: Comparable[T]](first:T,second:T)={
    if (first.compareTo(second)>0)
      first
    else
      second
  }
}


//泛型的上下界
class GranderFather
class Father extends GranderFather
class Son extends Father


//逆变和协变
//首先 Java中不存在协变
/**
 * 然在类层次结构上看String是Object类的子类 但List<String>并不是的 List<Object>子类 也就是说它不是协变的
 * Java的灵活性就这么差吗？其实Java不提供协变和逆变这种特性是有其道理的
 * 这是因为协变和逆变会破坏类型安全
 * 假设上面的代码是合法的 我们此时完全可以s2.add(new Person(“xuzheng”)往集合中添加 Person对象
 * 但此时我们知道 s2已经指向了s1 而s1里面的元素类型是String类型 这时其类型安全就被破坏了
 * 从这个角度来看 Java不提供协变和逆变是有其合理性的
 */
//public class TypeTest {
//
//  public static void main(String[] args) {
//
//    java.util.List<String> s1 = new LinkedList<String>();
//    java.util.List<Object> s2 = new LinkedList<Object>();
//    /**
//     * 下面这条语句会报错
//     * Type mismatch: cannot convert from List<String> to List<Object>
//     */
//    s2 = s1;
//  }
//}




/**
 * //申明逆变 没事
 * class person2[-A](){
 *  def test(x:A): Unit ={}
 * }
 *
 * 申明协变 报错..
 * class person1[+A](){
 *  def test(x:A): Unit ={}
 * }
 *
 * //假设协变成立 假设以下两种类型
 * class person1[Any](){
 *  def test(x:A): Unit ={}
 * }
 *
 * class person1[String](){
 *  def test(x:A): Unit ={}
 * }
 *
 * 由于any时string的父类
 * val pAny = new person1[Any]
 * val pString = new person1[String]
 * 此时调用pAny.test(123)是合法的 根据里氏替换原则 可以将pAny=pString 这时再调用pAny(123)是非法的
 * 因为子类型不接受非String类型的值 也就是父类能做的事情 子类不一定能做 子类只能部分满足
 * 所以这时 子类中函数的参数必须是父类函数中参数的超类 这样父类能做的子类也能做 这样就是逆变
 *
 * 使用协变
 * val pAny = new person2[Any]
 * val pString = new person2[String]
 *
 * any是string的父类 pAny是pString的子类 pString=pAny之后 pString不仅可以pString.test("123") 而且可以pString(123)
 * pString的函数可以接收更加广泛的参数 这就是逆变
 */

object Genericity {
  def main(args: Array[String]): Unit = {
    println (new Worker[String,Int]("黄渤",33).name)

    //下届应用
    def getIDCard[R >: Son](person:R): Unit ={
      println("好吧，他的身份证就交给你保管了");
    }
    // getIDCard[T](t:T)前面这个T表示方法中的参数类型被固定下来。
    // 在定义的时候还不知道这个T类型到底应该是什么 但是调用的时候被确定下来是某种类型
    // 但是 参数中的类型 无论如何都可以是T的子类类型 这属于多态范畴

    getIDCard[GranderFather](new GranderFather)
    getIDCard[GranderFather](new Father)
    getIDCard[Father](new Father)
    getIDCard[Son](new Son)

    //这句代码会报错
    //getIDCard[Tongzhuo](new Tongzhuo)


  }
}
