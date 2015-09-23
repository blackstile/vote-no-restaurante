<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/padrao.css' />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/tabela.css' />" />

<div id="conteudo">
	<div class="container">
		<h2>Ranking de preferências de restaurantes</h2>
		<br/><br/>
		<p>Para realizar uma nova votação. Clique <a href="${pageContext.request.contextPath}/votacao/nova" class="linkConteudo">aqui</a></p>
		<br/>
		<c:if test="${votacaoUsuario != null}">
			<table summary="Ranking da sua votação" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td colspan="3">Ranking da sua votação</td>
					</tr>
					<tr>
						<th>Posição</th>
						<th>Restaurante</th>
						<th>Quant. Votos</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="3">Lista de opções escolhidas por você.</td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${votacaoUsuario.votos}" var="voto" varStatus="row">
						<tr>
							<td>
								${row.index + 1}
							</td>
							<td>
								${voto.restaurante.nome}
							</td>
							<td>${voto.quantidade}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</c:if>
		<br/>
		<c:if test="${fn:length(rankingGerais) >= 1}">
			<table summary="Ranking geral de votações" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td colspan="3">Ranking geral de votações</td>
					</tr>
					<tr>
						<th>Posição</th>
						<th>Restaurante</th>
						<th>Quant. Votos</th>
					</tr>
				</thead>
				
				<tfoot>
					<tr>
						<td colspan="3">Ranking geral de todos usuários participantes.</td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${rankingGerais}" var="ranking" varStatus="row">
						<tr>
							<td>
								${row.index + 1}
							</td>
							<td>
								${ranking.restaurante.nome}
							</td>
							<td>${ranking.quantidadeVotos}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</c:if>
		
	</div>
</div>