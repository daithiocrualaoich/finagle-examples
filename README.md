Finagle Examples
================
Example Finagle service with tests.

* `Http`: Simple HTTP service responding on `/` only.
* `Ping`: A Thrift ping service.


## Quick Start
The services examples are exercised using tests, i.e. no run target. Instead
use the bundled SBT to run the tests with:

    ./sbt test

See the tests for examples of how to create the services.


## Thrift
Thrift interface definitions are compiled using Scrooge. See
[here](http://twitter.github.io/scrooge/SBTPlugin.html) for information on the
SBT plugin, and [here](http://twitter.github.io/scrooge/Finagle.html) for notes
on using the generated files with Finagle.


## Versions
The SBT and Scala versions are elderly, 0.12.4 and 2.9.2 respectively. These
are the latest versions that work with the Scrooge SBT plugin.


## IntelliJ Project Files
Make project files for IntelliJ with:

    ./sbt gen-idea
