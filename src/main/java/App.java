import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/change.vtl");
      Float totalCash = Float.parseFloat(request.queryParams("money-amount"));
      ChangeMachine cash = new ChangeMachine();
      String change = cash.makeChange(totalCash);
      model.put("change", change);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
