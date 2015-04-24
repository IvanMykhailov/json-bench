package jsonbench.model

case class ComplexEntity(
  complexEntity: Option[ComplexEntity],
  plainEntity: PlainEntity,
  entityWithMap: Option[EntityWithMap]
)