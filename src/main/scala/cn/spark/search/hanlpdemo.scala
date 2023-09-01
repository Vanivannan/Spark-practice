package cn.spark.search

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.seg.common.Term

import java.util

object hanlpdemo {
  def main(args: Array[String]): Unit = {
    val terms: util.List[Term] = HanLP.segment("我爱大自然")
    import scala.collection.JavaConverters._
//    terms.asScala
  }
}
