<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Detalhes do Evento</title>

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
			width: 500px;
			background-color: white;
			padding: 5px 10px 5px 10px;
		}
	</style>


</head>
<body>
<center>
<table border="1">
	<tbody>
		<tr >
			<td >
				<img  src="http://graph.facebook.com/${evento.id}/picture"	></img>
			</br>
		    	<b>${evento.name}</b>
		    </td >
		</tr>
		<tr>
			<td>
				<b>Descrição</b> 	${evento.description}
			</td>
		</tr>
		<tr>
			<td><b>Local:</b> ${evento.location}</td>
		</tr>
		<tr>
			<td><b>Início:</b> ${evento.dataInicio}</td>
		</tr>
		<tr>
			<td><b>Fim:</b> ${evento.dataFim}</td>
		</tr>
		<tr>
			<td><b>Criador do evento:</b> 
				<img border="1" src="http://graph.facebook.com/${evento.owner.id}/picture"	></img> ${evento.owner.name} 
			</td>
		</tr>
	</tbody>
</table>
</center>
</body>
</html>