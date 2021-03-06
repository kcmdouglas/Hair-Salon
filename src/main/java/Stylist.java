import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Stylist{
  private int mId;
  private String mName;

  public Stylist(String name){
    this.mName = name;
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

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int mId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", mId)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public void update(String name) {
    this.mName = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", mName)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", mId)
      .executeUpdate();
    }
  }

  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName, stylist_id AS mStylistId FROM clients where stylist_id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeAndFetch(Client.class);
    }
  }

}
