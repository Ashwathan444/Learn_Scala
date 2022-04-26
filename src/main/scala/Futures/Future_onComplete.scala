package Futures
import Futures.MultipleFutures.sleep

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Future_onComplete extends App{

  def calculate(number : Int): Future[Int] = Future {
    val r = scala.util.Random
    val randomDivisor = r.nextInt(2)
    val result = number/randomDivisor
    result
  }

  val res = calculate(10)

  val result: Unit = res.onComplete {
    case Success(value) =>  println(value)
    value
    case Failure(t) => println("An error has occurred: " + t.getMessage)
      0
  }
  val res1 = calculate(11)
  val result1 = res1.map{rp =>
    rp
  }.recoverWith{
    case e => println("An error has occurred: " + e.getMessage)
      Future.successful(0)
  }

  val res2 = calculate(11)
  val res3 = calculate(12)
  val res4 = calculate(13)
  val res5 = calculate(14)
  val res6 = calculate(15)
  val list = List(res1,res2,res3,res4,res5)

}
