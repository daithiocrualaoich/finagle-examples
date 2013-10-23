package com.twitter.docrualaoich.finagle

import com.twitter.util._
import com.twitter.finagle.http.{ Method, Version }
import org.scalatest.FlatSpec
import org.jboss.netty.handler.codec.http._
import com.twitter.finagle.Service

class HttpServiceTest extends FlatSpec {

  "A HttpService" should "serve a 200 on /" in {
    val service: Service[HttpRequest, HttpResponse] = new HttpServer.HttpService
    val request: HttpRequest = new DefaultHttpRequest(Version.Http11, Method.Get, "/")
    val response: HttpResponse = Await.result(service(request))

    assert(response.getStatus.getCode == 200)
  }

  it should "serve a 404 on other stuff" in {
    val service: Service[HttpRequest, HttpResponse] = new HttpServer.HttpService
    val request: HttpRequest = new DefaultHttpRequest(Version.Http11, Method.Get, "/other/stuff")
    val response: HttpResponse = Await.result(service(request))

    assert(response.getStatus.getCode == 404)
  }
}

class HttpIntegrationTest extends FlatSpec {
  "A HttpServer" should "serve a 200 response on / to a HttpClient" in {
    withAvailablePort { port =>
      val server = HttpServer(port)
      val client = HttpClient("localhost:%d" format port)
      val request: HttpRequest = new DefaultHttpRequest(Version.Http11, Method.Get, "/")
      val response: HttpResponse = Await.result(client(request))

      assert(response.getStatus.getCode == 200)

      server.close()
    }
  }

  it should "serve a 404 response on other stuff to a HttpClient" in {
    withAvailablePort { port =>
      val server = HttpServer(port)
      val client = HttpClient("localhost:%d" format port)
      val request: HttpRequest = new DefaultHttpRequest(Version.Http11, Method.Get, "/other/stuff")
      val response: HttpResponse = Await.result(client(request))

      assert(response.getStatus.getCode == 404)

      server.close()
    }
  }
}
