<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration App</title>
</head>
<body>
    <div style = "margin-left: auto; margin-right: auto; width: 300px;">
        <c:if test="${user != null && !isNotLogged}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${user == null || isNotLogged}">
            <form action="<%=request.getContextPath()%>/signup" method="post">
        </c:if>
        <div class ="text-center">
            <caption>
                <h2>
                    <c:if test="${user != null && !isNotLogged}">
                        Edit User
                    </c:if>
                    <c:if test="${user == null || isNotLogged}">
                        Sign Up
                    </c:if>
                </h2>
            </caption>
        </div>
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        </c:if>

        <fieldset class="form-group">
            <label>Surname</label> <input type="text"
                value="<c:out value='${user.surname}' />" class="form-control"
                name="surname" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Name</label> <input type="text"
                value="<c:out value='${user.name}' />" class="form-control"
                name="name" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Birthday</label> <input type="text"
                value="<c:out value='${user.birthday}' />" class="form-control"
                name="birthday" placeholder="yyyy-mm-dd" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Phone</label> <input type="tel"
                value="<c:out value='${user.phone}' />" class="form-control"
                name="phone" placeholder="+79103449422" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Email</label> <input type="email"
                value="<c:out value='${user.email}' />" class="form-control"
                name="email" required="required">
        </fieldset>

        <fieldset class="form-group">
            <label>Password</label> <input type="password"
                value="<c:out value='${user.password}' />" class="form-control"
                name="password">
        </fieldset>
        <c:if test="${error != null}">
            <font color="red"> <c:out value='${error}' /></font>
        </c:if>
        <div style = "width: 100%; text-align: center;">
            <button type="submit" class="btn btn-success">Save</button>
        </div>
        </form>
	</div>
</body>
</html>