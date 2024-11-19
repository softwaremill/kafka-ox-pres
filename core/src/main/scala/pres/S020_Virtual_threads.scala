package pres

import ox.discard
import java.util.concurrent.ConcurrentHashMap

@main def s020_Virtual_threads() =
  timed("loom") {
    val threads = new Array[Thread](10_000_000)
    val results = ConcurrentHashMap.newKeySet[Int]()

    var i = 0
    while i < threads.length do
      threads(i) = Thread
        .ofVirtual()
        .start(
          new Runnable:
            def run() = results.add(0).discard
        );
      i += 1
    end while

    i = 0
    while i < threads.length do
      threads(i).join()
      i += 1
    end while
  }
