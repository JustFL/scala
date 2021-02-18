package note

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * var和val的区别 val类型的变量指的是引用不可更改 引用指向哪片内存空间永远指向那里 不会改变 但是引用对象内容可以改变
 * 可变集合可以修改 添加 移除
 * 不可变集合永远不会改变 但是可以模拟添加 移除和更新操作 返回的都是新集合
 */
object Collections {
  def main(args: Array[String]): Unit = {

    println("-----------------array数组-----------------")
    /**
     * Array是不可变数组：长度不可变，内容可变
     * ArrayBuffer是可变数组：长度数组都可变
     *
     * 数组不属于集合 集合才分为immutable和mutable
     */
    var arr1 = Array(1,2,3,4,5)
    //通过map函数后产生了一个新的array
    var arr2 = arr1.map(_ + 1)
    println("arr2: "+arr2)
    println("arr2: "+arr2.toBuffer)
    println("arr2: "+arr2.mkString(","))
    println("arr2: "+arr2.mkString("<","-",">"))

    var ab = ArrayBuffer[Int]()
    ab += 2;
    ab += 2;
    ab += 4;
    println("ab: "+ab)

    //将Array转化为ArrayBuffer
    var arr3 = arr2.toBuffer
    arr3 += 10
    println("arr3:"+arr3)

    //将ArrayBuffer转化为Array
    var arr4 = arr3.toArray
    println(arr4(arr4.length-1))

    arr4(arr4.length-1) = 100

    //插入删除
    arr3.insert(0,222)
    arr3.remove(3,1)
    println("arr3:"+arr3)

    //new方式创建数组 会自动添加默认值
    var arr5 = new Array[Int](3)
    arr5 :+ 3;
    println(arr5.toBuffer)
    var arr6 = arr5 :+ 2;
    println(arr6.toBuffer)

    println("-----------------immutable和mutable-----------------")
    val map1 = Map("a"->1,"b"->2)
    val map2 = scala.collection.mutable.Map("a"->3,"b"->4)
    val original_map2 = map2
    var map3 = Map("a"->5,"b"->6)
    val original_map3 = map3
    var map4 = scala.collection.mutable.Map("a"->7,"b"->8)
    val original_map4 = map4

    //“+=”操作符或者函数是mutable的函数 但不是immutable的函数
    //map1 += ("c" -> 100)
    map2 += ("c" -> 101)
    map3 += ("c" -> 102)
    map4 += ("c" -> 103)

    println(map2)
    println(original_map2 == map2)
    println(map3)
    println(original_map3 == map3)
    println(map4)
    println(original_map4 == map4)

    //通过记录添加元素前后的指针情况 immutable类型确实创建了新的引用指向新的集合

    println("--------------------list序列--------------------")
    //这种写法是调用了apply方法
    var l1 = List(4,3,2)
    println(l1)
    println(l1.head)
    println(l1.tail)
    println(l1.init)
    println(l1.last)

    val l2 = 0::Nil
    val l3 = 9::5::2::Nil
    println(l2)
    println("l3:" + l3.reverse)

    //将0插入到list的最前面的4种写法
    val l31 = 0::l3
    val l32 = l3.::(0)
    val l33 = 0 +: l3
    val l34 = l3.+:(0)
    println(l31)
    println(l32)
    println(l33)
    println(l34)

    //向list中添加元素
    val l300 = l3 :+ 0
    println("l300：" + l300)

    //取list中的某个元素
    println("l3第二个元素： "+l3(2))

    //两个list相连
    val l35 = l33 ++ l34
    println("l35: "+l35)

    //丢弃前几个元素
    val l36 = l35.drop(2)
    println("l36: "+l36)

    //取前几个元素
    println(l36.take(3))

    println("---------------set---------------")
    val set1 = new scala.collection.immutable.HashSet[Int]()
    val set2 = set1 + 4
    println(set2)

    val set3 = set2 ++ Set(5,6)
    println(set3)
    println(set3.getClass)

    val set4 = scala.collection.mutable.HashSet[Int]()
    set4 += 3
    set4.add(5)
    println("set: "+set4)

    set4 -= 3
    println(set4)
    set4.remove(5)
    println(set4)

    println("-----------------map-----------------")
    val map5 = Map(("a",1), ("b",2))
    val map6 = Map("c" -> 3, "d" -> 4)
    println(map5)
    println(map6)

    val list1 = List(1,2)
    val list2 = List("a","b")
    val map7 = list1 zip list2
    println(map7)
    println(map7.getClass)
    //list和map可以相互转化
    println(map7.toMap)

    /**
     * 大多数语言都有一个特殊的关键字或者对象来表示一个对象引用的是“无”，在Java，它是null。
     * 在Java 里，null 是一个关键字，不是一个对象，所以对它调用任何方法都是非法的。但是这对语言设计者来说是一件令人疑惑的选择。
     * 为什么要在程序员希望返回一个对象的时候返回一个关键字呢？
     *
     * 为了让所有东西都是对象的目标更加一致，也为了遵循函数式编程的习惯，Scala鼓励你在变量和函数返回值可能不会引用任何值的时候使用Option类型。
     * 在没有值的时候，使用None，这是Option的一个子类。如果有值可以引用，就使用Some来包含这个值。Some也是Option的子类。
     * Option类型的值通常作为Scala集合类型（List,Map等）操作的返回类型。
     *
     * Option有两个子类别，Some和None。
     * 当程序回传Some的时候，代表这个函式成功地给了你一个值，而你可以透过get()函数拿到那个值，如果程序返回的是None，则代表没有值可以给你。
     * 在返回None，也就是没有值给你的时候，如果你还硬要调用get()来取的话，Scala一样是会抛出一个NoSuchElementException异常给你的。
     *
     * 这样做好处
     * 1 规避了异常处理 没有空指针异常
     * 2 方便模式匹配
     */
    println(map5("a"))
    println(map5.get("a"))
    //因为没有哪个值 会抛出异常
    //println(map5("c"))
    println(map5.getOrElse("c", 333))
    println(map5.get("c"))


    val map8 = new mutable.HashMap[Int, String]()
    map8.put(1,"a")
    map8(2) = "b"
    map8 += ((3,"c"))
    println(map8)

    map8.remove(1)
    map8 -= 2
    println(map8)

    println("--------------tuple--------------")
    val t = ("huangbo",42,false)
    println(t._1)
    println(t._2)

    val t1,(a,b,c) = ("huangbo",42,false)
    println(t1)
    println(c)


    println("------------------apply方法测试------------------")
    val ap1 = ApplyTest()

    /**
     * 手写面试代码题目
     * 1 单例模式
     * 2 双线程交替输出
     * 3 mr的wordcount
     * 4 scala的wordcount
     * 5 spark的wordcount
     */
  }

  /**
   * 当创建对象的时候 没有使用new关键字 而是使用了类名直接创建 这种方式是调用了apply方法
   */
  class ApplyTest{
    println("主构造器被调用了")
  }
  object ApplyTest{

    def apply(): ApplyTest = new ApplyTest(){
      println("apply方法被调用了")
    }
  }
}
