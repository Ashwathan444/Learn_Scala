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

  res onComplete {
    case Success(value) =>  println(value)
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  }

}
