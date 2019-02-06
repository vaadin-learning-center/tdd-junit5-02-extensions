package junit.com.vaadin.tutorials.tdd.junit5.demo;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.tutorials.tdd.junit5.demo.MyI18NProvider;

public class MyI18NProviderTest {

  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  interface TestInfrastructure {

    void setI18NProvider(I18NProvider i18NProvider);

    @BeforeAll
    default void initAll(TestReporter reporter) {
//      reporter.publishEntry("@BeforeAll " + MyI18NProviderTest.class.getSimpleName());
    }

    @BeforeEach
    default void init(TestReporter reporter) {
      reporter.publishEntry("@BeforeEach " + MyI18NProviderTest.class.getSimpleName());
      setI18NProvider(new MyI18NProvider());
    }

    @AfterAll
    default void tearDownAll(TestReporter reporter) {
//      reporter.publishEntry("@AfterAll " + MyI18NProviderTest.class.getSimpleName());
    }

    @AfterEach
    default void tearDown(TestReporter reporter) {
//      reporter.publishEntry("@AfterEach " + MyI18NProviderTest.class.getSimpleName());
    }
  }


  @Nested
  class TestClass001 implements TestInfrastructure {

    private I18NProvider i18NProvider;

    @Override
    public void setI18NProvider(I18NProvider i18NProvider) {
      this.i18NProvider = i18NProvider;
    }


    @Test
    void test001(TestReporter reporter) {
      reporter.publishEntry("the test itself");
      final List<Locale> providedLocales = i18NProvider.getProvidedLocales();
      assertAll("check locales" ,
                () -> assertTrue(providedLocales.contains(GERMAN)) ,
                () -> assertTrue(providedLocales.contains(ENGLISH)));
    }
  }


}
