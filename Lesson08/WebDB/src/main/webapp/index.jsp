<html>
<head>
    <title>Sample</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#add_address").click(function(){
                $("<div class=\"address\">\n" +
                    "Country:<br>\n" +
                    "<input type=\"text\" name=\"country\" /><br>\n" +
                    "City:<br>\n" +
                    "<input type=\"text\" name=\"city\" /><br>\n" +
                    "Street:<br>\n" +
                    "<input type=\"text\" name=\"street\" /><br>\n" +
                    "House:<br>\n" +
                    "<input type=\"text\" name=\"house\" /><br>\n" +
                    "<br>\n" +
                    "</div>").insertBefore("#add_address");
            });
        });
    </script>
</head>
<body>
<h3>Add user: </h3>
<form action="add" method="post">
    <h4>Personal info</h4>
    Name:<br>
    <input type="text" name="name" /><br>
    Age:<br>
    <input type="number" name="age" /><br>
    <h4>Address</h4>
    <div class="address">
        Country:<br>
        <input type="text" name="country" /><br>
        City:<br>
        <input type="text" name="city" /><br>
        Street:<br>
        <input type="text" name="street" /><br>
        House:<br>
        <input type="text" name="house" /><br>
        <br>
    </div>
    <input type="button" id="add_address" value="Add address" />
    <input type="submit" />
</form>
</body>
</html>
