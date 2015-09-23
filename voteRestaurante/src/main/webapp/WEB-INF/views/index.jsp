<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/padrao.css' />" />

<div id="conteudo">
	<div class="container">
		<section id="content">
			<h3>Vote no restaurante</h3>
			<br/>
			<h6>Participe da votação e eleja os restaurantes de sua preferência. São 5 restaurantes onde vc irá
			escolher a ordem de prioridade deles. O seu restaurante preferido recebe 4 votos, 
			o segundo 3, e assim por diante, de acordo com suas escolhas.</h6>
			
			<div style="padding: 20px; display:inline-block">
				<div 
					style="display:inline-block; background-color:yellow; cursor: pointer; padding: 5px; margin: 5px; border-color: black; border-style: dashed; border-width: 1px;" 
					onclick="location.href='votacao/nova';">
					<div style="font-weight:bold">Participar</div>
				</div>
				<div style="display:inline-block; background-color:#E0E0EA; cursor: pointer; padding: 5px; margin: 5px; border-color: black; border-style: dashed; border-width: 1px;" 
					onclick="location.href='ranking';">
					<div style="font-weight:bold">Ranking geral</div>
				</div>
			</div>
		</section><!-- content -->
	</div><!-- container -->
</div>

	
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="<c:url value='/resources/js/views/votacao/votacao.js' />"></script>