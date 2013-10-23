package com.twitter.docrualaoich.finagle

import com.twitter.util._
import org.scalatest.FlatSpec
import com.twitter.docrualaoich.finagle.PingServer.PingServiceImpl
import com.twitter.docrualaoich.finagle.thrift.PingService
import com.twitter.finagle.ListeningServer

class PingServiceTest extends FlatSpec {

  "A PingService" should "should pong" in {
    val service: PingServiceImpl = new PingServer.PingServiceImpl
    val response: String = Await.result(service.ping())

    assert(response == "pong")
  }
}

class PingIntegrationTest extends FlatSpec {

  "A PingServer" should "serve pong to a thrift client" in {
    withAvailablePort { port =>
      val server: ListeningServer = PingServer(port)
      val client: PingService.FinagledClient = PingClient("localhost:%d" format port)

      val response: String = Await.result(client.ping())

      assert(response == "pong")

      client.service.close()
      server.close()
    }
  }
}
