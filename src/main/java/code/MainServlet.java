package code;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.http.Cookie;
import static database.HibernateMain.canLogIn;

public class MainServlet extends HttpServlet {


    private void CalculatorPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Military Calculator was deployed</h1>");
        out.println("<form action=\"http://localhost:8080/my-app/my-servlet\" method=\"post\">\n" +
                "   <label for=\"task\">Enter your task:</label>"+
                "   <input type=\"text\" id=\"task\" name=\"task\" placeholder = \"There\">"+
                "   <!-- Form fields -->\n" +
                "   <button type=\"submit\">Submit</button>\n" +
                "</form>");
        out.println("</body></html>");

        String expression = req.getParameter("task");
        //PrintWriter out = resp.getWriter();

        try {
            Expression answer = new ExpressionBuilder(expression).build();
            double result = answer.evaluate();
            //System.out.println("The answer is " + result);
            out.println("The answer is " + result);
        } catch (IllegalArgumentException e){
            out.println("Incorrect expression");
        }
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("Method init =)");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        //resp.getWriter().write("Method service\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h3>Hello. Prove you connections with the army.</h3>");
            out.println("<form action=\"http://localhost:8080/my-app/my-servlet\" method=\"post\">\n" +
                    "  <label for=\"field1\">Login</label><br>\n" +
                    "  <input type=\"text\" id=\"field1\" name=\"field1\"><br>\n" +
                    "  <label for=\"field2\">Password</label><br>\n" +
                    "  <input type=\"text\" id=\"field2\" name=\"field2\"><br><br>\n" +
                    "  <input type=\"submit\" value=\"Submit\">\n" +
                    "</form> ");
            out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String field1Value = req.getParameter("field1");

        System.out.println(field1Value);
        String field2Value = req.getParameter("field2");
        System.out.println(field1Value + field2Value);
        if (canLogIn(field1Value, field2Value) || field1Value == null) {
            CalculatorPage(req, resp);
        }
        else doGet(req, resp);

        //super.doPost(req, resp);
        //CalculatorPage(req, resp);

    }

    @Override
    public void destroy() {
        super.destroy();
        log("Method destroy =)");
    }
}
