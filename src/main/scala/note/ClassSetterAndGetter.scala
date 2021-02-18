package note

/**
 * 测试getter和setter
 */

class ClassSetterAndGetter{
  //private 修饰的属性 在本类和伴生对象中可以访问
  private var id : Int = _
  private var name : String = _
  //默认public 哪里都可以访问
  var sex : Int = _

  //重写了id属性的getter和setter方法 注意这里的setter方法 def fuck_= (fuckid : Int) = {...} 这逼玩意的语法和普通def定义的方法 完全不同 必须这么写 垃圾！！！
  def fuck = id
  def fuck_= (fuckid : Int) = {
    id = fuckid
  }

  //使用了普通的def方法重新定义了getter和setter方法
  def getName : String = {
    name
  }
  def setName(fuckScala:String) = {
    name = fuckScala;
  }
}

object ClassSetterAndGetter {
  def main(args: Array[String]): Unit = {
    val c: ClassSetterAndGetter = new ClassSetterAndGetter
    c.fuck = 9527
    println(c.fuck)

    c.setName("唐伯虎")
    println(c.getName)

    //没有重写getter和setter方法 直接调用
    c.sex = 1;
    println(c.sex)
  }
}


