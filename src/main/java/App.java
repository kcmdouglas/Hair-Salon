
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }
    setPort(port);
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");

      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// POST actions for stylists:
// Add a new stylist on home page:
    post("/stylist/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("stylist");
      Stylist newStylist = new Stylist(name);

      newStylist.save();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Update a stylist name on the home page:

    post("/stylist/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newName = request.queryParams("updateStylist");
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      stylist.update(newName);

      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Delete a stylist on invididual stylist page:
    post("/stylist/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      stylist.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Update a stylist name on individual stylist page:
    post("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newName = request.queryParams("updateStylist");
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      stylist.update(newName);

      model.put("stylist", stylist);

      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


// POST actions for clients:

// Add a new client on the home page:
    post("/client/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("client");
      Integer stylistId = Integer.parseInt(request.queryParams("stylistId"));

      Client newClient = new Client(name, stylistId);

      newClient.save();

      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Add a new client on an individual stylist page:
    post("/stylist/:id/client/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("client");
      Integer stylistId = Integer.parseInt(request.params("id"));

      Client newClient = new Client(name, stylistId);

      newClient.save();

      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Delete a client on an individual stylist page:
    post("/stylist/:id/client/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("clientDelete")));
      client.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Update a client's information on home page:
    post("/client/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("clientId")));
      Integer stylistId = Integer.parseInt(request.queryParams("stylistId"));
      String name = request.queryParams("clientName");

      client.update(name, stylistId);
      model.put("client", client);
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Update a client's information on a stylist's page:
    post("/stylist/:id/client/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("clientId")));
      Integer stylistId = Integer.parseInt(request.params(":id"));
      String name = request.queryParams("clientName");

      client.update(name, stylistId);
      model.put("client", client);
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
