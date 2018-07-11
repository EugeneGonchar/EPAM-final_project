<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <%--<link rel="stylesheet" href="../css/style.css">--%>
</head>

<body>

<form id="signUpForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup"/>
    Login:<br/>
    <input type="text" name="login" value=""/><br/>
    ${emptyField}
    <br/>
    ${loginExist}
    <br/>
    Email:<br/>
    <input type="text" name="email" value=""><br/>
    ${emptyField}
    <br/>
    Phone:<br/>
    <input type="text" name="phone" value=""><br/>
    ${emptyField}
    <br/>
    Name:<br/>
    <input type="text" name="first_name" value=""><br/>
    ${emptyField}
    <br/>
    Surname:<br/>
    <input type="text" name="last_name" value=""><br/>
    ${emptyField}
    <br/>
    Password:<br/>
    <input type="password" name="password" value=""/>
    ${emptyField}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Sign up"/>
</form>

</body>
</html>
