$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/launch.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: your.email@your.domain.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary :"
    },
    {
      "line": 3,
      "value": "#Feature: List of scenarios."
    },
    {
      "line": 4,
      "value": "#Scenario: Business rule through list of steps with arguments."
    },
    {
      "line": 5,
      "value": "#Given: Some precondition step"
    },
    {
      "line": 6,
      "value": "#When: Some key actions"
    },
    {
      "line": 7,
      "value": "#Then: To observe outcomes or validation"
    },
    {
      "line": 8,
      "value": "#And,But: To enumerate more Given,When,Then steps"
    },
    {
      "line": 9,
      "value": "#Scenario Outline: List of steps for data-driven as an Examples and \u003cplaceholder\u003e"
    },
    {
      "line": 10,
      "value": "#Examples: Container for s table"
    },
    {
      "line": 11,
      "value": "#Background: List of steps run before each of the scenarios"
    },
    {
      "line": 12,
      "value": "#\"\"\" (Doc Strings)"
    },
    {
      "line": 13,
      "value": "#| (Data Tables)"
    },
    {
      "line": 14,
      "value": "#@ (Tags/Labels):To group Scenarios"
    },
    {
      "line": 15,
      "value": "#\u003c\u003e (placeholder)"
    },
    {
      "line": 16,
      "value": "#\"\""
    },
    {
      "line": 17,
      "value": "## (Comments)"
    },
    {
      "line": 18,
      "value": "#Sample Feature Definition Template"
    }
  ],
  "line": 20,
  "name": "launch",
  "description": "I want to use this template for my feature file",
  "id": "launch",
  "keyword": "Feature",
  "tags": [
    {
      "line": 19,
      "name": "@launch"
    }
  ]
});
formatter.before({
  "duration": 3905700,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "login",
  "description": "",
  "id": "launch;login",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 23,
      "name": "@login"
    }
  ]
});
formatter.step({
  "line": 25,
  "name": "login using credentials on apllication",
  "keyword": "Given "
});
formatter.match({
  "location": "login.invokeBrowser()"
});
formatter.result({
  "duration": 169228300,
  "error_message": "java.lang.NoSuchFieldError: INSTANCE\r\n\tat org.apache.http.impl.io.DefaultHttpRequestWriterFactory.\u003cinit\u003e(DefaultHttpRequestWriterFactory.java:53)\r\n\tat org.apache.http.impl.io.DefaultHttpRequestWriterFactory.\u003cinit\u003e(DefaultHttpRequestWriterFactory.java:57)\r\n\tat org.apache.http.impl.io.DefaultHttpRequestWriterFactory.\u003cclinit\u003e(DefaultHttpRequestWriterFactory.java:47)\r\n\tat org.apache.http.impl.conn.ManagedHttpClientConnectionFactory.\u003cinit\u003e(ManagedHttpClientConnectionFactory.java:72)\r\n\tat org.apache.http.impl.conn.ManagedHttpClientConnectionFactory.\u003cinit\u003e(ManagedHttpClientConnectionFactory.java:84)\r\n\tat org.apache.http.impl.conn.ManagedHttpClientConnectionFactory.\u003cclinit\u003e(ManagedHttpClientConnectionFactory.java:59)\r\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager$InternalConnectionFactory.\u003cinit\u003e(PoolingHttpClientConnectionManager.java:494)\r\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager.\u003cinit\u003e(PoolingHttpClientConnectionManager.java:149)\r\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager.\u003cinit\u003e(PoolingHttpClientConnectionManager.java:138)\r\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager.\u003cinit\u003e(PoolingHttpClientConnectionManager.java:114)\r\n\tat org.openqa.selenium.remote.internal.HttpClientFactory.getClientConnectionManager(HttpClientFactory.java:74)\r\n\tat org.openqa.selenium.remote.internal.HttpClientFactory.\u003cinit\u003e(HttpClientFactory.java:57)\r\n\tat org.openqa.selenium.remote.internal.HttpClientFactory.\u003cinit\u003e(HttpClientFactory.java:60)\r\n\tat org.openqa.selenium.remote.internal.ApacheHttpClient$Factory.getDefaultHttpClientFactory(ApacheHttpClient.java:242)\r\n\tat org.openqa.selenium.remote.internal.ApacheHttpClient$Factory.\u003cinit\u003e(ApacheHttpClient.java:219)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.getDefaultClientFactory(HttpCommandExecutor.java:93)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.\u003cinit\u003e(HttpCommandExecutor.java:72)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.\u003cinit\u003e(DriverCommandExecutor.java:63)\r\n\tat org.openqa.selenium.chrome.ChromeDriverCommandExecutor.\u003cinit\u003e(ChromeDriverCommandExecutor.java:36)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:181)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:147)\r\n\tat common.DriverFactory.getDriverPath(DriverFactory.java:76)\r\n\tat common.DriverFactory.initializeBrowser(DriverFactory.java:46)\r\n\tat module.HomePage.initializeBrowser(HomePage.java:13)\r\n\tat test.steps.login.invokeBrowser(login.java:13)\r\n\tat ✽.Given login using credentials on apllication(src/test/resources/features/launch.feature:25)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 12600,
  "status": "passed"
});
});