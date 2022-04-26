package Futures

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Future_Exceptions extends App {

  val startTime = currentTime

  val f1:Future[Int] = Future {
    sleep(2000)
    1
  }
  val f2:Future[Int] = Future {
    sleep(550)
    throw new Exception("Sample Exception thrown !!")
    2
  }
  val f3:Future[Int] = Future {
    sleep(1000)
    3
  }

  val result = for {
    r1 <- f1
    r2 <- f2
    r3 <- f3
  } yield (r1 + r2 + r3)

  val a1= Future {
    1
  }
  println(a1)
  val a2 = Future(10)
  println(a2)


  result.onComplete {
    case Success(x) => {
      // the code won't come here
      println(s"result = $x")
    }
    case Failure(e) => {
      // the code comes here because of the intentional exception
      val finishTime = currentTime
      val delta = finishTime - startTime
      System.err.println(s"delta = $delta")
      System.err.println("Failure happened!")
      // just a short message
      System.err.println(e.getMessage)
    }
  }

  sleep(4000)

  def sleep(time: Long) = Thread.sleep(time)

  def currentTime = System.currentTimeMillis()

}