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
		table tr.link{
			cursor: pointer;
		}
		table tr td.nome {
			width: 500px;
			background-color: white;
			font-size: large;
			padding: 5px 10px 5px 10px;
		}
		table tr td {
			background-color: white;
			padding: 5px 10px 5px 10px;
		}
	</style>
</head>
<%
String caminhoApp = request.getContextPath();
%>
<body>
<script type="text/javascript">
function detalhesEvento(id) {
	var form = document.forms['detalhesEventoForm'];
	form.elements['idEvento'].value = id;
	form.submit();
}
</script>
	<div class="divBanner alignCenter">
		<img class="alignCenter" src="<%=caminhoApp%>/imagem/baladas.jpg" alt="Banner"/>
	</div>
	<div>
		<table class="alignCenter" border="1">
			<tbody>
				<c:forEach items="${Eventos}" var="ev" varStatus="i">
					<tr class="link" onclick="javascript: detalhesEvento(${ev.id})">
						<td colspan="2" class="nome">
							<b><c:out value="${ev.name}"/></b><br/>							
							Local: <c:out value="${ev.location}"/><br/>
							Início: <c:out value="${ev.dataInicio}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form name="detalhesEventoForm" action="<%=request.getContextPath()%>/DetalhesEvento" method="post">
			<input type="hidden" name="token" value="${access_token}"/>
			<input type="hidden" name="idEvento" value=""/>
		</form>
	</div>
</body>
</html>