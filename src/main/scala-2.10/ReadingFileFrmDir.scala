import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * Created by ke20506 on 3/30/2017.
  */
object ReadingFileFrmDir {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)
    if(args.length < 1) {
      System.err.println("Usage: DirWordCount")
      System.exit(1)

    }
    val sc = new SparkContext("Local[*]", "WordCount")
    val ssc = new StreamingContext(sc, Seconds(1))

    val myStream = ssc.textFileStream("C:/Users/ke20506/Desktop/Projects/spark/streamtest/")
    //val myStream = ssc.fileStream(args(0))
    val words = myStream.flatMap(x => x.split(" "))
    val wrdCount = words.map(x => (x,1)).reduceByKey((v1,v2) => v1+v2)
    wrdCount.print()

    ssc.start()
    ssc.awaitTermination()
  }
}


