package com.twitter.docrualaoich.finagle

import com.twitter.docrualaoich.finagle.thrift.PingService
import com.twitter.finagle.builder.{ ClientBuilder, ServerBuilder, Server }
import com.twitter.finagle.thrift.{ ThriftClientFramedCodec, ThriftServerFramedCodec }
import com.twitter.util.{ Await, Future }
import java.net.InetSocketAddress
import org.apache.thrift.protocol.TBinaryProtocol

object PingServer {
  class PingServiceImpl extends PingService.FutureIface {
    def ping(): Future[String] = Future.value("pong")
  }

  val service = new PingService.FinagledService(new PingServiceImpl, new TBinaryProtocol.Factory())

  def apply(port: Int): Server = ServerBuilder()
    .bindTo(new InetSocketAddress(port))
    .codec(ThriftServerFramedCodec())
    .name("PingServer")
    .build(service)
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
