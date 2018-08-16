<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Listado de Noticias</title>
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/noticias/create" var="urlCreate"></spring:url>
	<spring:url value="/noticias" var="urlNoticias"></spring:url>
	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	<link rel="stylesheet" href="${urlPublic}/bootstrap/bootstrap-table-master/dist/bootstrap-table.css">
	
	</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>
	
	<div class="container theme-showcase" role="main">

		<h3>Listado de Noticias</h3>
      
        <c:if test="${mensaje != null}">
	      <div class="alert alert-success" role="alert">
	      	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${mensaje}
		  </div>
		</c:if>
      
        <spring:url value="/noticias/edit" var="urlEdit" />
        <spring:url value="/noticias/delete" var="urlDelete" />
        <spring:url value="/noticias/create" var="urlCreate" />
        <a href="${urlCreate}" class="btn btn-success" role="button" title="Nueva noticia" >Nueva</a><br><br>        
      
        <div class="table-responsive">	
	        <table data-toggle="table" data-search="true" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 15, 20]"
	        	class="table table-hover table-striped table-bordered" id="dataTable">
        		<thead>
        			<tr>
		              <th data-field="idnoticia" data-visible="false">Id</th>
		              <th data-field="titulo">Titulo</th>
		              <th data-field="fecha">Fecha</th>
		              <th data-field="estatus">Estatus</th>              
		              <th data-field="opciones">Opciones</th>
		          </tr>
        		</thead>
        		<tbody>
        			<c:forEach var="noticia" items="${noticias}">
						<tr>
							<td>${noticia.id}</td>
							<td>${noticia.titulo}</td>
							<td><fmt:formatDate pattern="dd-MM-yyyy" value="${noticia.fecha}" /></td>
							<c:choose>
								<c:when test="${noticia.estatus eq 'Activa'}">
									<td><span class="label label-success">${noticia.estatus}</span></td>
								</c:when>
								<c:otherwise>
									<td><span class="label label-danger">${noticia.estatus}</span></td>
								</c:otherwise>
							</c:choose>								
							<td>
								<a href="${urlNoticias}/edit/${noticia.id}" class="btn btn-success btn-sm tooltipE" title="Editar" role="button">
									<span class="glyphicon glyphicon-pencil"></span>
								</a>							
								<button class="btn btn-danger btn-sm delete tooltipE" title="Eliminar" id="${noticia.id}">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
						   </td>
						</tr>
					</c:forEach>
        		</tbody>	
			</table>
		</div>

      <hr class="featurette-divider">

      <jsp:include page="../includes/footer.jsp"></jsp:include>		

	</div> <!-- /container -->
	
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content alert-danger">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">¡Alerta!</h4>
				</div>
				<div class="modal-body" style="text-align:center">
					<p><img src="${urlPublic}/images/error.png" width="48" height="48" class="center">¿Estas seguro de querer eliminar la noticia?</p>
					<input type="hidden" id="idFieldDelete" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" value="false">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						Cerrar
					</button>
					<button id="confirmacion" type="button" class="btn btn-danger" value="true">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						Confirmar
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${urlPublic}/js/jquery-3.3.1.min.js"></script>
	<script src="${urlPublic}/jquery/jquery-ui-1.12.1.custom.base/jquery-ui.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/bootstrap-table.js"></script>
	<!-- put your locale files after bootstrap-table.js -->
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/locale/bootstrap-table-es-MX.js"></script>
	<script type="text/javascript">
		var $urlPage = "${urlNoticias}";
		var $urlPageDelete = "${urlNoticias}/delete/";
	</script>
	<script src="${urlPublic}/js/deleteField.js"></script>    
</body>
</html>