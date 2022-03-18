package basics

import scala.io.Source

object File_basics extends App{

    println("Contents of file are : ")

    Source.fromFile("Sample.txt").foreach(ln => print(ln))

   println("Writing into a file : ")

}
