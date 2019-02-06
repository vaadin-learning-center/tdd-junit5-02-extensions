package junit.com.vaadin.tutorials.tdd.junit5.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.tutorials.tdd.junit5.demo.BasicTestUIRunner;
import com.vaadin.tutorials.tdd.junit5.demo.VaadinApp;

public class VaadinAppTest extends TestBenchTestCase {

  @Test
  void test001() {
    BasicTestUIRunner.start();

    System.setProperty("webdriver.chrome.driver",
                       "_data/webdrivers/chromedriver-mac-64bit");

    final ChromeDriver webDriver = new ChromeDriver();
    setDriver(webDriver);

    getDriver().get("http://localhost:8080/");

    final TextFieldElement tfA = $(TextFieldElement.class).id(VaadinApp.TF_A);
    final TextFieldElement tfB = $(TextFieldElement.class).id(VaadinApp.TF_B);
    final TextFieldElement tfResult = $(TextFieldElement.class).id(VaadinApp.TF_RESULT);

    tfA.setValue("2");
    tfB.setValue("2");

    ButtonElement btnOk = $(ButtonElement.class).first();
    btnOk.click();

    final String result = tfResult.getValue();

    // Check the the value of the button is "Clicked"
    Assertions.assertEquals("4" , result);

    getDriver().quit();
    BasicTestUIRunner.stop();
  }
}
