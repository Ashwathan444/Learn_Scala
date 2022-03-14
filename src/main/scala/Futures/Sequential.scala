package Futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Sequential extends App {

  def sample_action(n: Int) = Future {
    Thread.sleep(1000)
    if(n==1){
      Thread.sleep(1000)
    }
    println(n)  
    n + 1
  }

  //Sequential futures dependant on each other
  val dep_f = for {
    f1 <- sample_action(1)
    f2 <- sample_action(f1)
    f3 <- sample_action(f2)
    f4 <- sample_action(f3)
    f5 <- sample_action(f4)
  } yield List(f1, f2, f3, f4, f5)
  dep_f.map(z => println(s"Done. ${z.size} sample actions run"))

  //Sequential futures dependant on each other
  val indep_f = for {

    f6 <- Future.sequence(List(sample_action(6), sample_action(7), sample_action(8)))

  }yield f6
  indep_f.map(x => println(s"Done. ${x.size} sample actions run simultaneously"))

  Thread.sleep(10000)

}
