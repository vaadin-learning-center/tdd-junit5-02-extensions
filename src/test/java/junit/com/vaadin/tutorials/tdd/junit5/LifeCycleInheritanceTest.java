package junit.com.vaadin.tutorials.tdd.junit5;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifeCycleInheritanceTest {


  public static class MyExtension implements
      BeforeEachCallback,
      AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext ctx) throws Exception {
      final List<String> values = new ArrayList<>();
      values.add("something magic");
      ctx
          .getStore(ExtensionContext.Namespace.create("my-storage"))
          .put("instance" , values);
    }

    @Override
    public void afterEach(ExtensionContext ctx) throws Exception {
      final List<String> values = ctx
          .getStore(ExtensionContext.Namespace.create("my-storage"))
          .get("instance" , List.class);

      values.forEach(System.out::println);
    }
  }


  @Test
  @ExtendWith(MyExtension.class)
  void test001() {

  }
}
