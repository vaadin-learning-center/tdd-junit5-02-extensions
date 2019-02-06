package com.vaadin.tutorials.tdd.junit5.demo;

import static java.util.Arrays.asList;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.success;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.rapidpm.dependencies.core.logger.HasLogger;
import com.vaadin.flow.i18n.I18NProvider;

public class MyI18NProvider implements I18NProvider, HasLogger {

  private static final Map<String, String> STORAGE_DE = new ConcurrentHashMap<>();
  private static final Map<String, String> STORAGE_EN = new ConcurrentHashMap<>();

  static {
    STORAGE_EN.put("btnOK" , "press me");
    STORAGE_DE.put("btnOK" , "Drück Mich");
  }

  @Override
  public List<Locale> getProvidedLocales() {
    return asList(GERMAN ,
                  ENGLISH);
  }

  @Override
  public String getTranslation(String key , Locale locale , Object... params) {
    // params are not used in this impl.
    return match(
        matchCase(() -> success(ENGLISH)) ,
        matchCase(() -> locale == null , () -> success(ENGLISH)) ,
        matchCase(() -> locale == GERMAN , () -> success(GERMAN))
    ).ifFailed(failed -> logger().info("requested Locale was .. " + locale))
     .map(l -> (l == GERMAN) ? STORAGE_DE : STORAGE_EN)
     .map(s -> s.getOrDefault(key , "NOT FOUND " + key))
     .get();
  }
}
