package cn.spark.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo05_Aggregate_NoKey {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val rdd1: RDD[Int] = sc.parallelize(1 to 10)

    //TODO -3.transformation

    //TODO -4.sink/输出/Action
    println(rdd1.sum())
    println(rdd1.reduce(_ + _))
    println(rdd1.fold(0)(_ + _))
    println(rdd1.aggregate(0)(_ + _, _ + _))

  }
}
