<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container" align="center">
        <h1>Your login is: ${login}, your roles are:</h1>
        <c:forEach var="s" items="${roles}">
            <h3><c:out value="${s}" /></h3>
        </c:forEach>

        <c:if test="${isAdmin}">
            <a href="/admin">Click here to visit ADMIN page.</a><br/>
        </c:if>

        <form role="form" class="form-horizontal" action="/update" method="POST">
            <input class="form-control form-group" type="text" name="email" value="${email}" placeholder="E-mail"/>
            <input class="form-control form-group" type="text" name="phone" value="${phone}" placeholder="Phone" />
            <input class="btn btn-primary" type="submit" value="Update" />
        </form>

        <p>Click to logout: <a href="/logout">LOGOUT</a></p>
    </div>
</body>
</html>
