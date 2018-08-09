<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Peliculas</title>
<spring:url value="/resources" var="urlPublic"></spring:url>
<spring:url value="/peliculas" var="urlPeliculas"></spring:url>
<spring:url value="/peliculas/create" var="urlCreate"></spring:url>
<spring:url value="/peliculas/edit" var="urlEdit"></spring:url>
<spring:url value="/peliculas/delete/" var="urlDelete"></spring:url>
<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
<link rel="stylesheet" href="${urlPublic}/bootstrap/bootstrap-table-master/dist/bootstrap-table.css">
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

		<a href="${urlCreate}" class="btn btn-success" role="button" title="Nueva Pelicula">Nueva</a><br><br>

		<div class="table-responsive">
			<table data-toggle="table" data-search="true" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 15, ALL]" 
				class="table table-hover table-striped table-bordered" id="dataTable">
				<thead>
					<tr>
						<th data-field="titulo">Titulo</th>
						<th data-field="genero">Genero</th>
						<th data-field="clasificacion">Clasificacion</th>
						<th data-field="duracion">Duracion</th>
						<th data-field="fechaEstreno">Fecha Estreno</th>
						<th data-field="estatus">Estatus</th>
						<th data-field="opciones">Opciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${peliculas}" var="pelicula">
					<tr>
						<td>${pelicula.titulo}</td>
						<td>${pelicula.genero}</td>
						<td>${pelicula.clasificacion}</td>
						<td>${pelicula.duracion} min.</td>
						<td><fmt:formatDate value="${pelicula.fechaEstreno}"
								pattern="dd-MM-yyyy" /></td>
						<td>
							<c:choose>
								<c:when test="${ pelicula.estatus == 'Activa' }">
									<span class="label label-success">ACTIVA</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">INACTIVA</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="${urlEdit}/${pelicula.id}"
								class="btn btn-success btn-sm" role="button" title="Editar" data-toggle="tooltip" data-placement="left">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
							<button class="btn btn-danger btn-sm delete" id="${pelicula.id}" title="Eliminar" data-toggle="tooltip" data-placement="right">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
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
				<div class="modal-body" style="text-align:center">
					<p><img src="${urlPublic}/images/error.png" width="48" height="48" class="center">¿Estas seguro de querer eliminar la película?</p>
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
	<!-- <script src="${urlPublic}/jquery/dist/jquery.min.js"></script> -->
	<script src="${urlPublic}/js/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/bootstrap-table.js"></script>
	<!-- put your locale files after bootstrap-table.js -->
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/locale/bootstrap-table-es-MX.js"></script>
	<script type="text/javascript">
		var $urlPage = "${urlPeliculas}";
		var $urlPageDelete = "${urlDelete}";
	</script>
	<script src="${urlPublic}/js/deleteField.js"></script> 
</body>
</html>
