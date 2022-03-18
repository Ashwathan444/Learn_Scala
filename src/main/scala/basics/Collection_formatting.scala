package basics

object Collection_formatting extends App{

  val votes = Seq(("scala", 1), ("java", 4), ("scala", 10), ("scala", 1), ("python", 10));
  println(votes);
  val votesByLang = votes groupBy { case (lang, _) => lang }
  println(votesByLang);
  val sumByLang = votesByLang map { case (lang, counts) =>
    val countsOnly = counts map { case (_, count) => count }
    (lang, countsOnly.sum)
  }
  println(sumByLang);
  val orderedVotes = sumByLang.toSeq
    .sortBy { case (_, count) => count }
    .reverse
  println(orderedVotes);

}
