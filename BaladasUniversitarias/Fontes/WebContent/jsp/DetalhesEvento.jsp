<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Detalhes do Evento</title>
</head>
<body>
<table border="1">
	<tbody>
		<tr>
			<td>
				${evento.name}
			</td>
		</tr>
		<tr>
			<td>
				Descrição
			</td>
		</tr>
		<tr>
			<td>
				${evento.description}
			</td>
		</tr>
		<tr>
			<td>Local: ${evento.location}</td>
		</tr>
		<tr>
			<td>Início: ${evento.dataInicio}</td>
		</tr>
		<tr>
			<td>Fim: ${evento.dataFim}</td>
		</tr>
		<tr>
			<td>Criador do evento: ${evento.owner.name}</td>
		</tr>
	</tbody>
</table>
</body>
</html>