package jsonbench

import java.util.concurrent.TimeUnit
import jsonbench.model._
import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@Fork(1)
class Bench {

  val m = (1 to 10).map(i => (s"key-$i" -> s"value-$i")).toMap
  val entityWithMap = EntityWithMap(m)
  val entityWithSeq = EntityWithSeq(m.keys.toSeq)
  val plainEntity = PlainEntity(55, "foobar", Some("somestring"))
  val u = {
    val l3 = ComplexEntity(None, plainEntity, Some(entityWithMap))
    val l2 = ComplexEntity(Some(l3), plainEntity, None)
    ComplexEntity(Some(l2), plainEntity, None)
  }

  val s = PlayUserCodec.encode(u)


  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def playParse(): ComplexEntity = PlayUserCodec.decode(s)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def jacksonParse(): ComplexEntity = JacksonUserCodec.decode(s)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def sprayParse(): ComplexEntity = SprayUserCodec.decode(s)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def playMarshall(): String = PlayUserCodec.encode(u)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def sprayMarshall(): String = SprayUserCodec.encode(u)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def jacksonMarshall(): String = JacksonUserCodec.encode(u)

  @Benchmark
  @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
  def jawnParse(): ComplexEntity = JawnUserCodec.decode(s)

}