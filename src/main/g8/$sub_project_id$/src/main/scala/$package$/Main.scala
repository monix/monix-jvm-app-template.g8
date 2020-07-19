/*
 * Copyright (c) 2020 $developer_name$.
 * All rights reserved.
 */

package $package$

import cats.effect.ExitCode
import cats.implicits._
import java.time.LocalDateTime
import monix.eval.Task
import monix.eval.TaskApp
import monix.reactive.Observable
import scala.concurrent.duration._
import scala.io.StdIn
import java.time.format.DateTimeFormatter

object Main extends TaskApp {

  def stream(period: FiniteDuration): Observable[LocalDateTime] =
    Observable.intervalAtFixedRate(period)
      .mapEval(_ => ExampleUtils.currentTime)

  override def run(args: List[String]): Task[ExitCode] = 
    Task.suspend {
      println("\nClock:\n")
      
      stream(500.millis)
        .map(_.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        .mapEval(dt => Task(print(s"\r\$dt")))
        .completedL
        .start
        .bracket(_ => Task(StdIn.readLine()))(_.cancel)
        .flatMap(_ => Task(println("\nBye!\n")))
        .as(ExitCode.Success)
    }
}
