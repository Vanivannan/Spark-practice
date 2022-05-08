package cn.spark.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo04_Repartition {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val rdd1: RDD[Int] = sc.parallelize(1 to 10)
    val rdd2: RDD[Int] = rdd1.repartition(9)
    val rdd3: RDD[Int] = rdd1.repartition(7)

    println(rdd1.getNumPartitions)//底层为 partitions.length
    println(rdd2.partitions.length)
    println(rdd3.getNumPartitions)

    val rdd4: RDD[Int] = rdd1.coalesce(9)
    val rdd5: RDD[Int] = rdd1.coalesce(7)
    val rdd6: RDD[Int] = rdd1.coalesce(9, true)
    println(rdd4.getNumPartitions)
    println(rdd5.getNumPartitions)
    println(rdd6.getNumPartitions)

    //TODO -3.transformation

    //TODO -4.sink/输出

  }
}
