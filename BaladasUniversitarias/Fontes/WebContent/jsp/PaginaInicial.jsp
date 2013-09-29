<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%
String caminhoApp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Baladas Universitárias</title>
	<link rel="stylesheet" type="text/css" href="<%=caminhoApp%>/css/style.css"/>
	<script src="<%=caminhoApp%>/js/jquery-1.10.2.js"></script>
</head>

<body>
<script type="text/javascript">
function detalhesEvento(id) {
	var form = document.forms['detalhesEventoForm'];
	form.elements['idEvento'].value = id;
	form.submit();
}
</script>
	<div class="divBanner">
		<img class="alignCenter" src="<%=caminhoApp%>/imagem/baladas.jpg" alt="Banner"/>
	</div>
	<div class="usuario">
		<img src="http://graph.facebook.com/${Usuario.id}/picture" alt="Foto do perfil"/>
		<br/>
		<span>Logado como: ${Usuario.firstName} ${Usuario.lastName}</span>
	</div>
	<table class="main">
		<tr>
			<td>
				<table class="alignCenter lista" border="1">
					<tbody>
						<c:forEach items="${Eventos}" var="ev" varStatus="i">
							<tr class="link" onclick="javascript: detalhesEvento(${ev.eid})">
								<td colspan="2" class="nome">
									<b><c:out value="${ev.name}"/></b><br/>							
									Local: <c:out value="${ev.location}"/><br/>
									Início: <c:out value="${ev.dataInicio}"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form name="detalhesEventoForm" action="<%=caminhoApp%>/DetalhesEvento" method="post" target="iframeDetalhes">
					<input type="hidden" name="idEvento" value=""/>
				</form>
			</td>
			<td>
				<iframe class="detalhes" id="divDetalhes" name="iframeDetalhes">
				</iframe>
			</td>
		</tr>
	</table>
</body>
</html>