import java.util.List;
import org.sql2o.*;

public class Stylist{
  private int mId;
  private String mName;

  public Stylist(String name){
    mName = name;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
    }
  }

  public static List<Stylist> all(){
    String sql = "SELECT id AS mId, name AS mName FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }



}
