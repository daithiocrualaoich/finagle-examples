package com.twitter.docrualaoich.finagle

import com.twitter.finagle.{ Http, ListeningServer, Service }
import com.twitter.util.Future
import org.jboss.netty.handler.codec.http._
import com.twitter.finagle.builder.ClientBuilder

object HttpServer {
  class HttpService extends Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = Future.value {
      request.getUri match {
        case "/" => new DefaultHttpResponse(request.getProtocolVersion, HttpResponseStatus.OK)
        case _ => new DefaultHttpResponse(request.getProtocolVersion, HttpResponseStatus.NOT_FOUND)
      }
    }
  }

  def apply(port: Int): ListeningServer = Http.serve(":%d" format port, new HttpService)
}

object HttpClient {
  def apply(host: String): Service[HttpRequest, HttpResponse] = ClientBuilder()
    .codec(com.twitter.finagle.http.Http())
    .hosts(host)
    .hostConnectionLimit(1)
    .build()
}
