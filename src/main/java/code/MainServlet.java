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

public class MainServlet extends HttpServlet {

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
        out.println("<h1>Military Calculator was deployed</h1>");
        out.println("<form action=\"http://localhost:8080/my-app/my-servlet\" method=\"post\">\n" +
                "   <label for=\"task\">Enter your task:</label>"+
                "   <input type=\"text\" id=\"task\" name=\"task\" placeholder = \"There\">"+
                "   <!-- Form fields -->\n" +
                "   <button type=\"submit\">Submit</button>\n" +
                "</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

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
    public void destroy() {
        super.destroy();
        log("Method destroy =)");
    }
}
