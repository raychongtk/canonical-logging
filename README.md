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

# Demonstration

```java

@RestController
public class DemoController {
   @Autowired
   DemoService demoService;

   @CanonicalLog
   @GetMapping("/api/v1/demo")
   public String demo() {
      CanonicalLogContext.put("test", "test string");
      CanonicalLogContext.put("test_string", "test string");

      demoService.demo();
      return "canonical logging";
   }
}
```

```java

@Service
public class DemoService {
   private static final Logger logger = CanonicalLoggerFactory.getLogger("canonical-log");

   public void demo() {
      CanonicalLogContext.put("demo_key", "demo_value");
      CanonicalLogContext.put("demo_key2", "demo_value2");

      logger.info("intermediate log");

      for (int i = 0; i < 10; i++) {
         CanonicalLogContext.stat("read_count", 1);
         CanonicalLogContext.increase("read_count_v2");
      }
   }
}
```

1. Annotated the method that you want to have Canonical Logging with `@CanonicalLog`
2. Put key information into log context by invoking `CanonicalLogContext.put(key, value)`
3. Put stat information into log context by invoking `CanonicalLogContext.stat(key, value)`
   or `CanonicalLogContext.increase(key)`

Eventually, you will see something like this:

```
{test=test string, end_time=2024-08-28T13:12:09.510456, read_count_v2=10.0, start_time=2024-08-28T13:12:09.509363, method_name=demo, demo_key=demo_value, test_string=test string, elapsed_time=1 ms, log_message=Canonical Log Line Done, id=[b20e142b-87de-41e9-9f4f-c7e2eb12608a, 69798f06-b6a8-4afb-8541-7cc114213ca0], class_name=com.actionlog.log.controller.DemoController, demo_key2=demo_value2, read_count=10.0}
```

This library also provide a Canonical Logger for you to print logs with the key value pairs that exists in the canonical
log context

```java
private static final Logger logger = CanonicalLoggerFactory.getLogger("canonical-log");
```

Declare a logger in this way will print log message with existing values inside the canonical log context

This is useful when you need the intermediate values attached in a log

The `logger.info("intermediate log")` will print an intermediate log with canonical log context, the result will be:

```
{start_time=2024-08-29T01:29:01.543875, test=test string, method_name=demo, demo_key=demo_value, test_string=test string, log_message=intermediate log, id=f1ddcea7-e268-4040-8742-7b39cdd4839e, class_name=com.actionlog.log.controller.DemoController, demo_key2=demo_value2}
```

You will see the `log_message` is `intermediate log`

That said, the key-value pairs written into the canonical log context will all attach to the log when the `logger.info`
is called

If you declare a logger with normal logger factory, you will only get the log message without any canonical log
information attached

```java
private static final Logger logger = LoggerFactory.getLogger("canonical-log");
```

The result for this logger will be:

```
intermediate log
```

It will not have any canonical log information attached

Either way is fine. Choose the best one for your use case