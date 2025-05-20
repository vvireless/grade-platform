<html>
<body>
  <form method="post" action="grades">
    Name: <input name="name" />
    Subject: <input name="subject" />
    Score: <input name="score" />
    <button type="submit">Add</button>
  </form>

  <h3>Grades:</h3>
  <table border="1">
    <tr><th>Name</th><th>Subject</th><th>Score</th></tr>
    <%
      ResultSet rs = (ResultSet) request.getAttribute("grades");
      if (rs != null) {
        while (rs.next()) {
          out.print("<tr><td>" + rs.getString("name") + "</td><td>" +
                             rs.getString("subject") + "</td><td>" +
                             rs.getDouble("score") + "</td></tr>");
        }
      }
    %>
  </table>
</body>
</html>
