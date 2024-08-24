# Canonical Logging

Canonical Logging is a way to design the logging approach in your system. In traditional logging approach, you will need
to call `log.info(...)` multiple times in order to print logs in different locations. This increases number of logs and
makes logs difficult to read and understand.
With canonical logging, every API request will have a single log with key-value pair to indicate the key information of
the request lifecycle, meaning that, you can put different data into a log context, the data can be the key information
that you need for the request like user id, error message, invoke dependency services success or not, and etc.

# Benefit

1. Reduce number of logs: you don't need to print multiple logs in multiple locations in many use cases
2. Increase observability: now, you only need to look at the canonical log whenever you need to investigate something
   because the log already provide key information for you

# Architecture

![](./docs/canonical-logging.jpg)

Now, you have canonical log. Does it mean you don't need traditional logging? No. You still need it. Canonical log is
used to provide key information in high-level, however, if you want to dive into something, you might still need
traditional logging to provide more details. Therefore, we use a mixed approach for logging. Canonical log provide key
information and traditional logs provide details.

How to link the canonical log with traditional logs? Use distributed tracing!
