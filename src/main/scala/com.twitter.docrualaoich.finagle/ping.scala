package com.twitter.docrualaoich.finagle

import com.twitter.docrualaoich.finagle.thrift.PingService
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.util.Future
import org.apache.thrift.protocol.TBinaryProtocol
import com.twitter.finagle.{ ListeningServer, Thrift }

object PingServer {
  class PingServiceImpl extends PingService.FutureIface {
    def ping(): Future[String] = Future.value("pong")
  }

  val service = new PingService.FinagledService(new PingServiceImpl, new TBinaryProtocol.Factory())

  def apply(port: Int): ListeningServer = Thrift.serve(":%d" format port, service)
}

object PingClient {
  def apply(host: String): PingService.FinagledClient = {
    val service = ClientBuilder()
      .hosts(host)
      .codec(ThriftClientFramedCodec())
      .hostConnectionLimit(1)
      .build()

    new PingService.FinagledClient(service, new TBinaryProtocol.Factory())
  }
}
