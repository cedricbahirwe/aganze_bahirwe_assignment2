<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Forget Password</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="form">
  <h1>Forget Password</h1>
  <form action="index.jsp" name="reset-password">

    <h3>Since, you have forgotten password,
      <br>
      You can use the default password of: <u> ${defaultPassword} </u>
    </h3>

    <p>Your Email is: ${email}</p>

    <br>

    <input type="submit"
           value="Go back to Login Page"
           class="button button-block"
    />
  </form>
</div>
</body>
</html>