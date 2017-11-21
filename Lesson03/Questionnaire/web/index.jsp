<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Questionnaire</title>
</head>
<body>
<h3>Please, fill in our questionnaire.</h3>
<form action="/question" method="post">
  Firstname:<br>
  <input type="text" name="firstname"><br>
  Lastname:<br>
  <input type="text" name="lastname"><br>
  Age:<br>
  <input type="number" name="age" min="18" max="99"><br>
  <br>
  Do you like dogs?<br>
  <input type="radio" name="q1" value="yes"> Yes<br>
  <input type="radio" name="q1" value="no"> No<br>
  Do you like cats?<br>
  <input type="radio" name="q2" value="yes"> Yes<br>
  <input type="radio" name="q2" value="no"> No<br>
  <br>
  <input type="submit" value="Send">
</form>
</body>
</html>
