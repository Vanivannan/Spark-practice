package cn.spark.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo01_Create {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val rdd1: RDD[Int] = sc.parallelize(1 to 10)
    val rdd2: RDD[Int] = sc.parallelize(1 to 10,3)

    val rdd3: RDD[Int] = sc.makeRDD(1 to 10)
    val rdd4: RDD[Int] = sc.makeRDD(1 to 10,4)

    val rdd5: RDD[String] = sc.textFile("data/input/words.txt")
    val rdd6: RDD[String] = sc.textFile("data/input/words.txt",3)

    val rdd7: RDD[String] = sc.textFile("data/input/ratings10")
    val rdd8: RDD[String] = sc.textFile("data/input/ratings10",3)

    val rdd9: RDD[(String, String)] = sc.wholeTextFiles("data/input/ratings10")
    val rdd10: RDD[(String, String)] = sc.wholeTextFiles("data/input/ratings10",3)

    println(rdd1.getNumPartitions)//底层为 partitions.length
    println(rdd2.partitions.length)
    println(rdd3.getNumPartitions)
    println(rdd4.getNumPartitions)
    println(rdd5.getNumPartitions)
    println(rdd6.getNumPartitions)
    println(rdd7.getNumPartitions)
    println(rdd8.getNumPartitions)
    println(rdd9.getNumPartitions)
    println(rdd10.getNumPartitions)

    //TODO -3.transformation

    //TODO -4.sink/输出

  }
}
