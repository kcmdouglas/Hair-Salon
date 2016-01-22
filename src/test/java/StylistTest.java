import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Franz Ferdinand");
    Stylist secondStylist = new Stylist("Franz Ferdinand");
    assertTrue(firstStylist.equals(secondStylist));
  }

  // @Test
  // public void save_savesIntoDatabase_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   assertTrue(Category.all().get(0).equals(myCategory));
  // }
  //
  // @Test
  // public void find_findCategoryInDatabase_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Category savedCategory = Category.find(myCategory.getId());
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task firstTask = new Task("Mow the lawn", myCategory.getId());
  //   firstTask.save();
  //   Task secondTask = new Task("Do the dishes", myCategory.getId());
  //   secondTask.save();
  //   Task[] tasks = new Task[] { firstTask, secondTask };
  //   assertTrue(myCategory.getTasks().containsAll(Arrays.asList(tasks)));
  // }
}
