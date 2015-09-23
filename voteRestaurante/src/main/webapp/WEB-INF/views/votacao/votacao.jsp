<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/padrao.css' />" />

<div id="conteudo">
	<div class="container">
		<section id="content">
			<h3><spring:message code='project.name'/>
			<c:if test="${!votacaoConcluida}">
				<div style="padding: 20px">
					<div 
						style="background-color:yellow; cursor: pointer; padding: 5px; margin: 5px; border-color: black; border-style: dashed; border-width: 1px;" 
						onclick="votar(${primeiraOpcaoRestaurante.id});">
						<div>${primeiraOpcaoRestaurante.nome}</div>
					</div>
					<div style="background-color:#E0E0EA; cursor: pointer; padding: 5px; margin: 5px; border-color: black; border-style: dashed; border-width: 1px;" 
						onclick="votar(${segundaOpcaoRestaurante.id});">
						<div>${segundaOpcaoRestaurante.nome}</div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${votacaoConcluida}">
				<div style="padding: 5px; font-size:12px">	
					Informe seu email e nome e clique no botão concluir, para finalizar a participação.
				</div>
				<div style="padding: 20px">
					
					<form id="formUsuario" method="POST" action="confirmarVotacaoUsuario" onSubmit="return validarFormularioUsuario();">
						<div>
							<input type="text" name="email" placeholder="Email" required maxlength="100">
						</div>
						<div>
							<input type="text" name="nome" placeholder="Nome" required maxlength="100">
						</div>
						<div style="margin-left: 90px">
							<input type="submit" value="Concluir">
						</div>
					</form>
				</div>
			</c:if>
		</section><!-- content -->
	</div><!-- container -->
</div>

	
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="<c:url value='/resources/js/views/votacao/votacao.js' />"></script>