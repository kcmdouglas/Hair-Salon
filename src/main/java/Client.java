import java.util.List;
import org.sql2o.*;

public class Client{
  private int mId;
  private String mName;
  private int mStylistId;

  public Client(String name, int stylistId){
    this.mName = name;
    this.mStylistId = stylistId;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public int getStylistId() {
    return mStylistId;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName());
    }
  }

  public static List<Client> all(){
    String sql = "SELECT id AS mId, name AS mName, stylist_id AS mStylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
  //
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO stylists(name) VALUES (:name)";
  //     this.mId = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.mName)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static Stylist find(int mId) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT id AS mId, name AS mName FROM stylists WHERE id=:id";
  //     Stylist stylist = con.createQuery(sql)
  //       .addParameter("id", mId)
  //       .executeAndFetchFirst(Stylist.class);
  //     return stylist;
  //   }
  // }
  //
  // public void update(String name) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE stylists SET name = :name WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("name", mName)
  //       .addParameter("id", mId)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String sql = "DELETE FROM stylists WHERE id = :id;";
  //   con.createQuery(sql)
  //     .addParameter("id", mId)
  //     .executeUpdate();
  //   }
  // }
  //
}
