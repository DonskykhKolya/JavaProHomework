<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
    <% String statistics = (String)session.getAttribute("statistics"); %>
    <h3>Results:</h3>
    <br><br>
    <p><%= statistics %></p>
</body>
</html>
