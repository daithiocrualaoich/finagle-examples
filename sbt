#!/bin/bash

set -o nounset
set -o errexit

java -Xmx1024m -XX:MaxPermSize=512m \
  -XX:+UseConcMarkSweepGC \
  -XX:+CMSIncrementalMode \
  -Djava.awt.headless=true \
  -jar `dirname $0`/dev/sbt-launch-0.13.0.jar "$@"

