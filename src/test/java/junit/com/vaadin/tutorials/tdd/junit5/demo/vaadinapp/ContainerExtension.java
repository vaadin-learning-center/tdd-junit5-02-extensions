package junit.com.vaadin.tutorials.tdd.junit5.demo.vaadinapp;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import com.vaadin.tutorials.tdd.junit5.demo.BasicTestUIRunner;

public class ContainerExtension implements BeforeAllCallback , AfterAllCallback {
  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    BasicTestUIRunner.start();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    BasicTestUIRunner.start();
  }
}
