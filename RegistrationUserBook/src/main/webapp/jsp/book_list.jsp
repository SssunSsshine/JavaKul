<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration App</title>
</head>
<body>
   <a href="<%=request.getContextPath()%>/user/home">Home</a>
   <a style = "color: red;" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>
   <div style = "margin-left: auto; margin-right: auto; width: 300px;">
        <caption>
            <h2>
                 Information about your favorite books
            </h2>
        </caption>

        <c:if test="${books != null}">
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>

                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td style = "height: 100px; width: 400px;">
                                <p>ID: ${book.id}</p>
                                <p>Author: ${book.author}</p>
                                <p>Title: ${book.title}</p>
                                <p>Release Year: ${book.year}</p>
                            </td>
                            <td style = "height: 100px; width: 200px; text-align: center;">
                                <a href="<%=request.getContextPath()%>/book/delete?id=${book.id}" class="btn btn-danger" role="button">Delete</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
              </table>
        </c:if>
    </div>
</body>
</html>

