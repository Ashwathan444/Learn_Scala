package Futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Mixed extends App{

  def sample_action(n: Int) = Future {
    Thread.sleep(1000)
    if(n==1){
      Thread.sleep(1000)
    }
    println(n) // for demo only as this is side-effecting 
    n + 1
  }

  val f = for {
    f1 <- sample_action(1)
    f2 <- Future.sequence(List(sample_action(f1), sample_action(f1)))
    f3 <- sample_action(f2.head)
    f4 <- Future.sequence(List(sample_action(f3), sample_action(f3)))
    f5 <- sample_action(f4.head)
  } yield (f2.size + f4.size)

  f.map(z => println(s"Done. $z sample_actions run in parallel"))

  Thread.sleep(10000)
  
}
