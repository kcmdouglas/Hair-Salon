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
      return this.getName().equals(newClient.getName())&&
             this.getId() == newClient.getId() &&
             this.getStylistId() == newClient.getStylistId();
    }
  }

  public static List<Client> all(){
    String sql = "SELECT id AS mId, name AS mName, stylist_id AS mStylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .addParameter("stylist_id", this.mStylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int mId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName, stylist_id AS mStylistId FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", mId)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name, int stylistId) {
    this.mName = name;
    this.mStylistId = stylistId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, stylist_id = :stylist_id  WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", mName)
        .addParameter("stylist_id", mStylistId)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", mId)
      .executeUpdate();
    }
  }

}
