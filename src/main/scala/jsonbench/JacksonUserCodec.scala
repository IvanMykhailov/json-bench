package jsonbench

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import jsonbench.model._

object JacksonUserCodec {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def decode(s: String): ComplexEntity = mapper.readValue(s, classOf[ComplexEntity])

  def encode(u: ComplexEntity): String = {
    mapper.writeValueAsString(u)
  }
}
