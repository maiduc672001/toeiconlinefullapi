<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="guideline" value="/exercise-listen.guideline.html">
    <c:param name="urlType" value="guideline"/>
    <c:param name="exerciseListenId" value="${item.exerciseListenId}"/>
</c:url>
<c:url value="/exercise-listen.question.html" var="question">
    <c:param name="urlType" value="question"/>
    <c:param name="exerciseListenId" value="${item.exerciseListenId}"/>
</c:url>
<html>
<head>
    <title>Option</title>
</head>
<body>
<h1><a href="${guideline}">Guideline</a></h1>
<h1><a href="${question}">Learn Now</a></h1>
</body>
</html>
