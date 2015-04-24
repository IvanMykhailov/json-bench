package jsonbench

import spray.json._
import DefaultJsonProtocol._
import jawn.support.spray.Parser
import jsonbench.model._


object JawnUserCodec {
  implicit val entityWithMapFormat = jsonFormat(EntityWithMap, "map")

  implicit val entityWithSeqFormat = jsonFormat(EntityWithSeq, "seq")

  implicit val plainEntityFormat = jsonFormat(PlainEntity, "intval", "stringval", "optval")

  implicit val complexEntityFormat: JsonFormat[ComplexEntity] = lazyFormat(jsonFormat(ComplexEntity, "complexEntity", "plainEntity", "entityWithMap"))

  def decode(s: String): ComplexEntity = {
    val json = Parser.parseFromString(s).get
    json.convertTo[ComplexEntity]
  }

  def encode(u: ComplexEntity): String = ???
}