<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.restfb.types.Event"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Baladas Universitárias</title>
<style type="text/css">
body{
background-color: lightgray;
}
.alignCenter{
	margin-right: auto;
	margin-left: auto;
}
.divBanner{
	width: 802px;
	height: 250px;
}
</style>
</head>
<%
String caminhoApp = request.getContextPath();
%>
<body>
	<div class="divBanner alignCenter">
		<img class="alignCenter" src="<%=caminhoApp%>/imagem/baladas.jpg" alt="Banner"/>
	</div>
	<div>
	<table class="alignCenter" border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Local</th>
				<th>Início</th>
				<th>Fim</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Eventos}" var="ev" varStatus="status">
				<tr>
					<td><c:out value="${ev.id}"/></td>
					<td><c:out value="${ev.name}"/></td>
					<td><c:out value="${ev.location}"/></td>
					<td><c:out value="${ev.startTime}"/></td>
					<td><c:out value="${ev.endTime}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>