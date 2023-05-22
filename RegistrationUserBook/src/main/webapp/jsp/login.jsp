<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration App</title>
</head>
<body>
        <div style = "margin-left: auto; margin-right: auto; width: 200px;">
            <form action="sign-in" method="post">
                <h2>Login</h2>
                <fieldset class="form-group">
                    <input type="email"
                            class="form-control"
                        name="login" placeholder="email" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <input type="password"
                            class="form-control"
                        name="password" placeholder="password" required="required">
                </fieldset>
                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div style = "width: 100%; text-align: center;">
                    <button type="submit" class="btn btn-success">Sign in</button>
                    <a href="<%=request.getContextPath()%>/new"><input type="button" value="Sign up"></a>
                </div>
            </form>
        </div>
</body>
</html>