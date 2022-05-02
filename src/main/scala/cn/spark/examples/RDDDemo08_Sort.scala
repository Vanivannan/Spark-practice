package cn.spark.examples

import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo08_Sort {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val lines: RDD[String] = sc.textFile("data/input/words.txt")

    //TODO -3.transformation
    val result: RDD[(String, Int)] = lines.filter(StringUtils.isNoneBlank(_))
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

    val sortResult1: Array[(String, Int)] = result.sortBy(_._2, false) //按照数量降序排列
      .take(3) //取出前三个

    val sortResult2: RDD[(Int, String)] = result.map(_.swap).sortByKey(false)

    val sortResult3: Array[(String, Int)] = result.top(3)(Ordering.by(_._2))//topN默认降序

    //TODO -4.sink/输出
    result.foreach(println)
    sortResult1.foreach(println)
    sortResult2.foreach(println)
    sortResult3.foreach(println)
//    result4.foreach(println)

  }
}
