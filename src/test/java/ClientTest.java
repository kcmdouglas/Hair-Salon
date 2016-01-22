import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Rule;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Gavrilo Princip", 1);
    Client secondClient = new Client("Gavrilo Princip", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("Gavrilo Princip", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }
  //
  // @Test
  // public void find_findStylistInDatabase_true() {
  //   Stylist myStylist = new Stylist("Franz Ferdinand");
  //   myStylist.save();
  //   Stylist savedStylist = Stylist.find(myStylist.getId());
  //   assertTrue(myStylist.equals(savedStylist));
  // }
  //
  // @Test
  // public void update_updatesStylistNameInDatabase_newName() {
  //   Stylist myStylist = new Stylist("Franz Ferdinand");
  //   myStylist.save();
  //   myStylist.update("Otto Von Bismarck");
  //   assertEquals(myStylist.getName(), "Otto Von Bismarck", "Otto Von Bismarck");
  // }
  //
  // @Test
  // public void delete_deletesStylistEntry_true() {
  //   Stylist myStylist = new Stylist("Franz Ferdinand");
  //   myStylist.save();
  //   myStylist.delete();
  //   assertEquals(Stylist.all().size(), 0);
  // }

}
