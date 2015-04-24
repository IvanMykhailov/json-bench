package jsonbench

import jsonbench.model._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object PlayUserCodec {
  implicit val entityWithMapFormat = Json.format[EntityWithMap]

  implicit val entityWithSeqFormat = Json.format[EntityWithSeq]

  implicit val plainEntityFormat = Json.format[PlainEntity]

  implicit val complexEntityFormat = Json.format[ComplexEntity]

  def decode(s: String): ComplexEntity = {
    val json = Json.parse(s)
    json.validate[ComplexEntity] match {
      case u: JsSuccess[ComplexEntity] => u.get
      case e: JsError => throw new RuntimeException(JsError.toFlatJson(e).toString())
    }
  }

  def encode(u: ComplexEntity): String = {
    val json = Json.toJson(u)
    json.toString()
  }
}
