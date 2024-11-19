package pres

class S120_Pros_cons {
  /*

   Alpakka / Pekko Kafka:
   (+) versatile API
   (+) battle-proven implementation
   (+) flexible producers / consumer creation
   (+) support for Kafka transactions
   (-) difficult to create new stages
   (-) colored functions (Future / normal)
   (-) stack traces
   (-) additional runtime needed

   Ox:
   (+) performance (channels)
   (+) easy to create new stages
   (+) no colored functions
   (+) stack traces
   (+) no additional runtime needed
   (-) in development
   (-) no support for Kafka's transactions (yet)
   (-) not optimized (yet)
   (-) TBD?

   Both implementations are reactive:
   * back-pressured
   * error-handling
   * completion
   * asynchronous processing

   */
}
