package junit.com.vaadin.tutorials.tdd.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

public class LifeCycleTest {

  @BeforeAll
  static void step001(TestReporter testReporter){
    testReporter.publishEntry("step001");
  }

  @BeforeAll
  static void step002(TestReporter testReporter){
    testReporter.publishEntry("step002");
  }

  @BeforeEach
  private void step003(TestReporter testReporter){
    testReporter.publishEntry("step003");
  }

  @BeforeEach
  private void step004(TestReporter testReporter){
    testReporter.publishEntry("step004");
  }


  @AfterAll
  static void step005(TestReporter testReporter){
    testReporter.publishEntry("step005");
  }

  @AfterAll
  static void step006(TestReporter testReporter){
    testReporter.publishEntry("step006");
  }

  @AfterEach
  private void step007(TestReporter testReporter){
    testReporter.publishEntry("step007");
  }

  @AfterEach
  private void step008(TestReporter testReporter){
    testReporter.publishEntry("step008");
  }

  @Test
  void test001(TestReporter testReporter){
    testReporter.publishEntry("test001");
  }
}
