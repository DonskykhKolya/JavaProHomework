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
    <h3>Upload single photo.</h3>
    Choose photo: <input type="file" name="photo">
    <input type="submit" value="Upload"/>
</form>

<form action="/add_multi_photos" enctype="multipart/form-data" method="POST">
    <h3>Upload multi photo.</h3>
    Photo 1: <input type="file" name="photo"><br>
    Photo 2: <input type="file" name="photo"><br>
    Photo 3: <input type="file" name="photo"><br>
    Photo 4: <input type="file" name="photo"><br>
    Photo 5: <input type="file" name="photo"><br>
    <br>
    <input type="submit" value="Upload"/>
</form>

<input type="button" id="delete" value="Delete">
<br>
<br>
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
