package Futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureTest {
  def main(args: Array[String]): Unit = {

    println("Before");
    val f = Future {
      println("Printing in the Future");
    }
    println("After");

  }
}
