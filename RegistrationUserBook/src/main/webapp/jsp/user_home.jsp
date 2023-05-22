<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration App</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/sign-out" style = "color:red;">Sign Out</a>
	<div style = "margin-left: auto; margin-right: auto; width: 300px;">
        <form action="edit" method="post">
        <div class ="text-center">
        <caption>
            <h2>
                 User Page
            </h2>
        </caption>
        </div>
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
            <input type="hidden" name="password" value="<c:out value='${user.password}' />" />
        </c:if>
        <fieldset class="form-group">
            <label>Surname</label> <input type="text"
                value="<c:out value='${user.surname}' />" class="form-control"
                name="surname" required="required" readonly>
        </fieldset>

        <fieldset class="form-group">
            <label>Name</label> <input type="text"
                value="<c:out value='${user.name}' />" class="form-control"
                name="name" required="required" readonly>
        </fieldset>

        <fieldset class="form-group">
            <label>Birthday</label> <input type="text"
                value="<c:out value='${user.birthday}' />" class="form-control"
                name="birthday" placeholder="yyyy-mm-dd" required="required" readonly>
        </fieldset>

        <fieldset class="form-group">
            <label>Phone</label> <input type="text"
                value="<c:out value='${user.phone}' />" class="form-control"
                name="phone" required="required" readonly>
        </fieldset>

        <fieldset class="form-group">
            <label>Email</label> <input type="text"
                value="<c:out value='${user.email}' />" class="form-control"
                name="email" required="required" readonly>
        </fieldset>

        <div style = "width: 100%; text-align: center;">
            <button type="submit" class="btn btn-success">Edit Profile</button>
             <a href="<%=request.getContextPath()%>/book/form"><input type="button" value="Add New Book"></a>
             <a href="<%=request.getContextPath()%>/book/all"><input type="button" value="Show My Books"></a>
        <div class="text-center"></div>
        </form>
	</div>
</body>
</html>