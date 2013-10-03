<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script src="<%=caminhoApp%>/js/jquery-1.10.2.js"></script>
	</head>
	<body>
		<table border="1" class="termometro alignCenter">
			<tbody>
				<tr >
					<td class="img">
						<img src="http://graph.facebook.com/${evento.eid}/picture"/>
					</td>
					<td class="nome">
				    	<b>${evento.name}</b>
				    </td>
				    <td class="termometro alignCenter" rowspan="11">
				    	<c:if test="${termometro eq 0}">
					    	<div claas="alignCenter">
						    	<span class="legenda">
						    		O evento já passou!<br/>
				    			</span>
			    			</div>
		    			</c:if>
		    			<c:if test="${termometro ne 0}">
					    	<div class="alignCenter">
						    	<span class="legenda">
						    		<c:if test="${termometro eq 0}">
						    			O evento já passou!<br/>
					    			</c:if>
						    		${legendaTermometro}
					    		</span><br/>
				    		</div>
					    	<div class="${styleTermometro}">
					    	</div><br/><br/>
				    	</c:if>
				    	<c:if test="${empty evento.listaAmigosPresentes}">
				    		Nenhum dos seus amigos confirmaram presença.
				    	</c:if>
				    	<c:if test="${!empty evento.listaAmigosPresentes}">
							Os seguintes amigos estarão presentes:<br />
							<c:forEach items="${evento.listaAmigosPresentes}" var="amigo" varStatus="i">
								<img border="1" src="http://graph.facebook.com/${amigo.id}/picture"/> ${amigo.name}<br/>
							</c:forEach>
						</c:if>
				    </td>
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
						<img border="1" src="http://graph.facebook.com/${evento.criador.id}/picture"></img> 
					</td>
					<td class="nome">
						<b>Criador do evento:</b>${evento.criador.name} 
					</td>
				</tr>
		
				<tr>
					<td class="img">
						Todos
					</td>
					<td class="nome">
						${evento.all_members_count}
					</td>
				</tr>
				<tr>
					<td class="img">
						Confirmados
					</td>
					<td class="nome">
						${evento.attending_count}
					</td>
				</tr>
				<tr>
					<td class="img">
						Recusado
					</td>
					<td class="nome">
						${evento.declined_count}
					</td>
				</tr>
				<tr>
					<td class="img">
						Talvez
					</td>
					<td class="nome">
						${evento.unsure_count}
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>