package pres

import ox.*
import ox.channels.Channel
import ox.channels.ChannelClosed
import ox.channels.selectOrClosed
import ox.flow.Flow

import scala.concurrent.duration.DurationInt

@main def s080_Flow_1(): Unit =
  Flow
    .fromValues(11, 24, 51, 76, 78, 9, 1, 44)
    .map(_ + 3)
    .filter(_ % 2 == 0)
    .intersperse(5)
    .mapStateful(() => 0) { (state, value) =>
      val newState = state + value
      (newState, newState)
    }
    .runToList()
    .pipe(println)

@main def s080_Flow_2(): Unit =
  val f1 = Flow.tick(1.second, "tick1")
  val f2 = Flow.tick(2.seconds, "tick2")

  f1.merge(f2).runForeach(println)

@main def s080_Flow_3(): Unit =
  val data = Channel.bufferedDefault[Int]
  val errors = Channel.unlimited[Exception]

  Flow
    .usingEmit: emit =>
      // connect to Kafka using data & errors
      forever:
        selectOrClosed(errors.receiveClause, data.receiveClause) match
          case data.Received(i) =>
            if i % 2 == 0 then emit(i)
            else
              emit(i)
              emit(i + 1)
          case errors.Received(e)     => throw e
          case ChannelClosed.Done     => // end
          case ChannelClosed.Error(e) => throw e
    .discard
