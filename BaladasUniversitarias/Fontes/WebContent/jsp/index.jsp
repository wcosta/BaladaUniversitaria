<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String caminhoApp = request.getContextPath();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicial</title>
        <link rel="stylesheet" type="text/css" href="<%=caminhoApp%>/css/style.css"/>
    </head>
    <body>
		<div class="divBanner">
			<img class="alignCenter" src="<%=caminhoApp%>/imagem/baladas.jpg" alt="Banner"/>
		</div>
		
		<div class="alignCenter">
			<span class="mensagemSistema">${mensagemSistema}</span><br/>
			<form action="<%=caminhoApp%>/jsp/Autorizacao.jsp" target="_top">
			<input class="permissao" type="submit" value="Requisitar permissões"/>
			</form>
		</div>
    </body>
</html>