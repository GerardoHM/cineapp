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
<title>Listado de Peliculas</title>
<spring:url value="/resources" var="urlPublic"></spring:url>
<spring:url value="/peliculas/create" var="urlCreate"></spring:url>
<spring:url value="/peliculas/edit" var="urlEdit"></spring:url>
<spring:url value="/peliculas/delete" var="urlDelete"></spring:url>
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
</head>

<body>
	<!-- Menu -->
	<jsp:include page="../includes/menu.jsp"></jsp:include>
	<!-- /Menu -->

	<div class="container theme-showcase" role="main">

		<h3>Listado de Peliculas</h3>

		<c:if test="${mensaje != null}">
			<div class="alert alert-success" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				${mensaje}
			</div>
		</c:if>

		<a href="${urlCreate}" class="btn btn-success" role="button"
			title="Nueva Pelicula">Nueva</a><br> <br>

		<div class="table-responsive">
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>Titulo</th>
					<th>Genero</th>
					<th>Clasificacion</th>
					<th>Duracion</th>
					<th>Fecha Estreno</th>
					<th>Estatus</th>
					<th>Opciones</th>
				</tr>
				<c:forEach items="${peliculas.content}" var="pelicula">
					<tr>
						<td>${pelicula.titulo}</td>
						<td>${pelicula.genero}</td>
						<td>${pelicula.clasificacion}</td>
						<td>${pelicula.duracion}min.</td>
						<td><fmt:formatDate value="${pelicula.fechaEstreno}"
								pattern="dd-MM-yyyy" /></td>
						<td><c:choose>
								<c:when test="${ pelicula.estatus == 'Activa' }">
									<span class="label label-success">ACTIVA</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">INACTIVA</span>
								</c:otherwise>
							</c:choose></td>
						<td><a href="${urlEdit}/${pelicula.id}"
							class="btn btn-success btn-sm" role="button" title="Editar">
								<span class="glyphicon glyphicon-pencil"></span>
						</a> <!-- <a href="${urlDelete}/${pelicula.id}" onclick="return confirm('¿Esta seguro de querer eliminar la pelicula?')"
							class="btn btn-danger btn-sm deletePelicula" role="button" title="Eliminar">
								<span class="glyphicon glyphicon-trash"></span>
						</a> -->
							<button class="btn btn-danger btn-sm deletePelicula" value="${pelicula.id}" title="Eliminar">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
							<a href="${urlDelete}/${pelicula.id}" id="eliminar_peli_${pelicula.id}" class="hide" ></a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<nav aria-label="">
				<ul class="pager">
					<li><a href="${urlPeliculas}/indexPaginate?page=${peliculas.number - 1}">Anterior</a></li>
					<li><a href="${urlPeliculas}/indexPaginate?page=${peliculas.number + 1}">Siguiente</a></li>
				</ul>
			</nav>
		</div>

		<hr class="featurette-divider">

		<!-- FOOTER -->
		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

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
				<div class="modal-body">
					<p>¿Esta seguro de querer eliminar la pelicula?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" value="false">Close</button>
					<button id="confirmacion" type="button" class="btn btn-danger" value="true">Confirmar</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="${urlPublic}/js/listPeliculas.js"></script> 
</body>
</html>
