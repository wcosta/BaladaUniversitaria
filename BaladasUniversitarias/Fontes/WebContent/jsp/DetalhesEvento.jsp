<?xml version="1.0" encoding="ISO-8859-1" ?>
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
		<title>Detalhes do Evento</title>
		<link rel="stylesheet" type="text/css" href="<%=caminhoApp%>/css/style.css"/>
	</head>
	<body>
	<script type="text/javascript">
		function confirmaPresenca(){
			var form = document.forms[0];
			form.action = "<%=caminhoApp%>/ConfirmarPresenca";
			form.submit();
		}
	</script>
		<table border="1" class="detalhes alignCenter">
			<tbody>
				<tr >
					<td class="img">
						<img  src="http://graph.facebook.com/${evento.id}/picture"/>
					</td>
					<td class="nome">
				    	<b>${evento.name}</b>
				    </td >
				</tr>
				<tr>
					<td colspan="2">
						<b>Descrição:</b> 	${evento.description}
					</td>
				</tr>
				<tr>
					<td colspan="2"><b>Local:</b> ${evento.location}</td>
				</tr>
				<tr>
					<td colspan="2"><b>Início:</b> ${evento.dataInicio}</td>
				</tr>
				<tr>
					<td colspan="2"><b>Fim:</b> ${evento.dataFim}</td>
				</tr>
				<tr>
					<td class="img">
						<img border="1" src="http://graph.facebook.com/${evento.owner.id}/picture"></img> 
					</td>
					<td class="nome">
						<b>Criador do evento:</b>${evento.owner.name} 
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<form action="<%=caminhoApp%>/PublicarEvento" method="post">
							<input type="hidden" name="idEvento" value="${evento.id}"/>
							<input type="hidden" name="nomeEvento" value="${evento.name}"/>
							<input type="hidden" name="imgEvento" value="http://graph.facebook.com/${evento.owner.id}/picture"/>
							<input class="publicar" type="submit" value="Publicar no Facebook"/>
						</form>
						<input class="confirmar" type="button" value="Confirmar presença" onclick="javascript: confirmaPresenca();"/>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="alignCenter">
			<input type="button" class="voltar" onclick="location. href='<%=caminhoApp%>/PaginaInicial'" value="Voltar"/>
		</div>
	</body>
</html>