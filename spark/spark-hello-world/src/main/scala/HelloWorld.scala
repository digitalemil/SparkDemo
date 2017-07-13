// import required spark classes
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
// define main method (scala entry point)
object HelloWorld {
  def main(args: Array[String]) {
 
    // initialise spark context
    val conf = new SparkConf().setAppName("HelloWorld")
    val sc = new SparkContext(conf)
    
    //val data= sc.textFile("hdfs://hdfs/appstudio/*");
    //data.collect().foreach(println);

    // do stuff
    println("************")
    println("************")
    println("Spark version: "+sc.version);    
    println("************")
    println("************")
    
    // terminate spark context
    sc.stop()
  }
}

