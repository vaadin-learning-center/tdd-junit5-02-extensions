package junit.com.vaadin.tutorials.tdd.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.vaadin.tutorials.tdd.junit5.Service;

public class ServiceTest {


  @Test
  @EnabledIfSystemProperty(named = "activate.vaadin.protools", matches = "true")
//  @Tag("vaadin-protools")
  void test007() {
    Throwable runtimeException = assertThrows(NumberFormatException.class , () -> {
      Integer.parseInt("no number");
    });
    assertEquals("For input string: \"no number\"" , runtimeException.getMessage());
  }


  //nested Tests - simple life cycle
  private List<String> strings;

  @Nested
  class NestedTestClass {

    @BeforeEach
      //only for this nested class
    void init() {
      strings = new ArrayList<>();
    }

    @Test
    void listIsEmpty() {
      assertTrue(strings.isEmpty());
    }

    @Nested //sub nested classes are possible
    class afterAddingString {

      @BeforeEach
      void init() {
        strings.add("hello");
      }

      @Test
      void listIsNotEmpty() {
        assertFalse(strings.isEmpty());
      }
    }
  }


  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  interface Interfaces{
    @BeforeAll
    default void initAll(TestReporter reporter){
      reporter.publishEntry("@BeforeAll runs once before all tests");
    }
    @BeforeEach
    default void init(TestReporter reporter){
      reporter.publishEntry("@BeforeEach runs before each test");
    }
    @AfterAll
    default void tearDownAll(TestReporter reporter){
      reporter.publishEntry("@AfterAll runs once after all tests");
    }
    @AfterEach
    default void tearDown(TestReporter reporter){
      reporter.publishEntry("@AfterEach runs after each test");
    }
  }
  @Nested
  class ClassImplementingInterfaceTests implements Interfaces {
    @Test
    void myTest(TestReporter reporter){
      reporter.publishEntry("the test itself");
    }
  }


}
