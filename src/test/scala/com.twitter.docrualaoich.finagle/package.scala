package com.twitter.docrualaoich.finagle

object `package` {
  def withAvailablePort[T](body: Int => T) = {
    val port = {
      // Yeah, yeah, it's a touch racey...
      val socket = new java.net.ServerSocket(0)
      socket.close()
      socket.getLocalPort()
    }

    body(port)
  }
}
