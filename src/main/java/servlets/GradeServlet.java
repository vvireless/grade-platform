package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/grades")
public class GradeServlet extends HttpServlet {
    private Connection conn;

    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:grades.db");
            conn.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS grades (name TEXT, subject TEXT, score REAL)");
        } catch (Exception e) { e.printStackTrace(); }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        double score = Double.parseDouble(req.getParameter("score"));

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO grades VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, subject);
            stmt.setDouble(3, score);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }

        res.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM grades");
            req.setAttribute("grades", rs);
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void destroy() {
        try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
    }
}
