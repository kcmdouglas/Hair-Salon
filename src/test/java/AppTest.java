import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.Rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome to your Stylist Database");
  }

  @Test
  public void stylistIsCreatedTest() {
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Otto Von Bismarck");
  }

  @Test
  public void stylistIsDisplayedTest() {
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    String stylistPath = String.format("http://localhost:4567/");
    goTo(stylistPath);
    assertThat(pageSource()).contains("Otto Von Bismarck");
  }

  @Test
  public void newClientIsCreatedTest() {
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    Client newClient = new Client("Wilhelm II", newStylist.getId());
    newClient.save();
    String stylistPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Wilhelm II");
  }

  @Test
  public void newStylistIsDeletedTest() {
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    Stylist newStylistTwo = new Stylist("Franz Ferdinand");
    newStylistTwo.save();
    newStylist.delete();
    goTo("http://localhost:4567/");
    assertThat(!(pageSource()).contains("Otto Von Bismarck"));
  }

  @Test
  public void newStylistIsUpdatedTest(){
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    newStylist.update("Wilhelm II");
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Wilhelm II");
  }

  @Test
  public void newClientIsUpdatedTest(){
    Stylist newStylist = new Stylist("Otto Von Bismarck");
    newStylist.save();
    Stylist newStylistTwo = new Stylist("Franz Ferdinand");
    newStylistTwo.save();
    Client newClient = new Client("Wilhelm II", 0);
    newClient.update("Wilhelm II", newStylistTwo.getId());
    String newPath = String.format("http://localhost:4567/stylist/%d", newClient.getStylistId());
    goTo(newPath);
    assertThat(pageSource()).contains("Franz Ferdinand");
  }

}
