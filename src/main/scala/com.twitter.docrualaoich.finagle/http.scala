package com.twitter.docrualaoich.finagle

import com.twitter.finagle.Service
import com.twitter.finagle.builder.{ Server, ServerBuilder, ClientBuilder }
import com.twitter.finagle.http.Http
import com.twitter.util.Future
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._

object HttpServer {
  class HttpService extends Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = Future.value {
      request.getUri match {
        case "/" => new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK)
        case _ => new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND)
      }
    }
  }

  def apply(port: Int): Server = ServerBuilder()
    .codec(Http())
    .bindTo(new InetSocketAddress(port))
    .name("HttpServer")
    .build(new HttpService())
}

object HttpClient {
  def apply(host: String): Service[HttpRequest, HttpResponse] = ClientBuilder()
    .codec(Http())
    .hosts(host)
    .hostConnectionLimit(1)
    .build()
}
