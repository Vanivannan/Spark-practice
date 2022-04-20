package cn.spark.examples

import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo06_Aggregate_Key {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val lines: RDD[String] = sc.textFile("data/input/words.txt")

    //TODO -3.transformation
    val wordAndOneRDD: RDD[(String, Int)] = lines.filter(StringUtils.isNoneBlank(_))
      .flatMap(_.split(" "))
      .map((_, 1))

    val grouped: RDD[(String, Iterable[Int])] = wordAndOneRDD.groupByKey()
    val result1: RDD[(String, Int)] = grouped.mapValues(_.sum)

    val result2: RDD[(String, Int)] = wordAndOneRDD.reduceByKey(_ + _)

    val result3: RDD[(String, Int)] = wordAndOneRDD.foldByKey(0)(_ + _)

    val result4: RDD[(String, Int)] = wordAndOneRDD.aggregateByKey(0)(_ + _, _ + _)

    //TODO -4.sink/输出
    result1.foreach(println)
    result2.foreach(println)
    result3.foreach(println)
    result4.foreach(println)

  }
}
