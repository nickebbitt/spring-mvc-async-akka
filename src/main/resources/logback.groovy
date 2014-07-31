/*
 * $Header: http://devdbs01.ds.local:18080/svn/BugleDayOne/IntegrationAdapter/trunk/src/main/resources/logback.groovy 801 2014-06-20 10:28:52Z nebbitt $
 */

def APPENDER_PATTERN = "%date{ISO8601} %-5level %logger{36}:%M:%L: %X{sourceThread} %X{akkaSource} - %msg%n";
def appenderList = ["CONSOLE"]

appender("CONSOLE", ConsoleAppender) {
      encoder(PatternLayoutEncoder) {
        pattern = "${APPENDER_PATTERN}"
      }
    }

logger("uk.co.nickebbitt", DEBUG)


root(INFO, appenderList)
