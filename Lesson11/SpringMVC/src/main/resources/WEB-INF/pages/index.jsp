<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Photo loader</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#delete").click(function () {
                var val = [];
                $(':checkbox:checked').parent().siblings("#id").each(function () {
                    val.push($(this).text());
                });
                $.post("/delete", {ids: val.toString()}, function (data, status) {
                    window.location.reload();
                });
            });
        });
    </script>
</head>
<body>
<form action="/add_photo" enctype="multipart/form-data" method="POST">
    Photo: <input type="file" name="photo">
    <input type="submit"/>
</form>

<input type="button" id="delete" value="Delete">

<table border="1">
    <c:forEach items="${photos}" var="photo">
        <tr>
            <td><input type="checkbox" name="checkbox" id="checkbox"></td>
            <td><c:out value="${photo.name}"/></td>
            <td id="id"><a href="/photo/${photo.id}"><c:out value="${photo.id}"/></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
