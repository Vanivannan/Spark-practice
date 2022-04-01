package cn.spark.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo01_Create {
  def main(args: Array[String]): Unit = {
    //todo -1.env
    val conf: SparkConf = new SparkConf().setAppName("sparkExamples").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //todo -2.load source
    val rdd1: RDD[Int] = sc.parallelize(1 to 100)

    //todo -3.输出
    println(rdd1.getNumPartitions)
  }
}
