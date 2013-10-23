namespace java com.twitter.docrualaoich.finagle.thrift

exception PingException {
  1: string description
}

service PingService {
  string ping() throws(1: PingException ex)
}