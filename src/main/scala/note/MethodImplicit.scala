package note

import java.io.File
import java.net.URL

import scala.io.Source

/*方法的隐式转换 file没有readAll方法 但是RichFile中有 实现File到RichFile的隐式转换*/

object MyImplicit{
  implicit def file2Rich(file:File):RichFile = new RichFile(file)
}

class RichFile(var file:File){
  def readAll:String = Source.fromFile(file).mkString
}

object MethodImplicit {
  def main(args: Array[String]): Unit = {

    val f = new File("E:\\code-workspace\\IdeaProjects\\helloScala\\src\\main\\resources\\implicit.txt")
    import MyImplicit._
    println(f.readAll)

  }
}
