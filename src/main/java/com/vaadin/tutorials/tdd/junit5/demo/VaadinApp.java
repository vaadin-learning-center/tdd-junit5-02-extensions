package com.vaadin.tutorials.tdd.junit5.demo;


import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import org.rapidpm.dependencies.core.logger.HasLogger;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.tutorials.tdd.junit5.Service;


@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class VaadinApp extends Composite<Div> implements HasLogger {

  public static final String TF_A = "tfA";
  public static final String TF_B = "tfB";
  public static final String TF_RESULT = "tfResult";
  public static final String BTN_OK = "btnOK";


  //fully static part - layout and elements
  private final TextField tfA = new TextField();
  private final TextField tfB = new TextField();
  private final TextField tfResult = new TextField();
  private final HorizontalLayout inputFields = new HorizontalLayout(
      tfA , new Label("+") , tfB , new Label("=") , tfResult);

  private final Button ok = new Button(getTranslation("btnOK"));

  private final VerticalLayout parentLayout = new VerticalLayout(
      inputFields ,
      ok);

  //managed part
  //@Inject
  private final Service service = new Service();

  public VaadinApp() {
    //technically via CDI - here for the demo manually
    postProcess();
    getContent().add(parentLayout);
  }

  //@Postprocess
  private void postProcess() {

    tfA.setId(TF_A);
    tfB.setId(TF_B);
    tfResult.setId(TF_RESULT);

    ok.setId(BTN_OK);

    ok.addClickListener(event -> {
      int result = service
          .add(parseInt(tfA.getValue()) ,
               parseInt(tfB.getValue()));
      tfResult.setValue(valueOf(result));
    });
  }
}
