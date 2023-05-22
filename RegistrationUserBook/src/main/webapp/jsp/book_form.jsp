<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration App</title>
</head>
<body>
   <a href="<%=request.getContextPath()%>/user/page">Profile</a>
   <a href="<%=request.getContextPath()%>/sign-out">Sign Out</a>

   <div style = "margin-left: auto; margin-right: auto; width: 300px;">
        <c:if test="${book != null}">
           <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
           <form action="add" method="post">
        </c:if>

        <caption>
           <h2>
              <c:if test="${book != null}">
                     Edit Favorite book
                  </c:if>
              <c:if test="${book == null}">
                     Add New Favorite book
                  </c:if>
           </h2>
        </caption>

        <c:if test="${book != null}">
                <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
            </c:if>
            <fieldset class="form-group">
               <label>Author</label> <input type="text"
                  value="<c:out value='${book.author}' />" class="form-control"
                  name="author" required="required">
            </fieldset>
            <fieldset class="form-group">
               <label>Title</label> <input type="text"
                  value="<c:out value='${book.title}' />" class="form-control"
                  name="title" required="required">
            </fieldset>

            <fieldset class="form-group">
               <label>Year</label> <input type="text"
                  value="<c:out value='${book.year}' />" class="form-control"
                  name="year" required="required">
            </fieldset>
            <c:if test="${error != null}">
                <font color="red"> <c:out value='${error}' /></font>
            </c:if>
            <div style = "width: 100%; text-align: center;">
                <button type="submit" class="btn btn-success">Save</button>
                <c:if test="${book != null}">
                    <a href="<%=request.getContextPath()%>/book/delete?id=${book.id}" class="btn btn-danger" role="button">Delete</a>
                </c:if>
            </div>
        </form>
     </div>
</body>
</html>

