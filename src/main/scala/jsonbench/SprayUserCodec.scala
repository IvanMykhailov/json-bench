package jsonbench

import jsonbench.model._
import spray.json._
import DefaultJsonProtocol._

object SprayUserCodec {

  implicit val entityWithMapFormat = jsonFormat(EntityWithMap, "map")

  implicit val entityWithSeqFormat = jsonFormat(EntityWithSeq, "seq")

  implicit val plainEntityFormat = jsonFormat(PlainEntity, "intval", "stringval", "optval")

  implicit val complexEntityFormat: JsonFormat[ComplexEntity] = lazyFormat(jsonFormat(ComplexEntity, "complexEntity", "plainEntity", "entityWithMap"))


  def decode(s: String): ComplexEntity = {
    val json = s.parseJson
    json.convertTo[ComplexEntity]
  }

  def encode(u: ComplexEntity): String = {
    val json = u.toJson
    json.compactPrint
  }
}
