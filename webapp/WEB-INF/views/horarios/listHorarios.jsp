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
	<title>Listado de Horarios</title>
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/horarios" var="urlHorarios" />
			
	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	<link rel="stylesheet" href="${urlPublic}/bootstrap/bootstrap-table-master/dist/bootstrap-table.css">
	
	</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>
	
	<div class="container theme-showcase" role="main">

		<h3>Listado de Horarios</h3>
      
        <c:if test="${mensaje != null}">
			<div class="alert alert-success" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				${mensaje}
			</div>
		</c:if>
      
        <a href="${urlHorarios}/create" class="btn btn-success" role="button" title="Nuevo horario" >Nuevo</a><br><br>        
      
      	<div class="table-responsive">	
	        <table data-toggle="table" data-search="true" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 15, 20]" 
				class="table table-hover table-striped table-bordered" id="dataTable">
				<thead>
					<tr>
					  <th data-field="idhorario" data-visible="false">ID</th>
		              <th data-field="pelicula">Pelicula</th>
		              <th data-field="fecha">Fecha</th>
		              <th data-field="hora">Hora</th>
		              <th data-field="sala">Sala</th>
		              <th data-field="precio">Precio</th>
		              <th data-field="opciones" data-align="center">Opciones</th>
		          	</tr>
				</thead>
				<tbody id="dataTableTbody">
					<c:forEach var="horario" items="${horarios}">
						<tr>
							<td>${horario.id}</td>
							<td>${horario.pelicula.titulo}</td>
							<td><fmt:formatDate pattern="dd-MM-yyyy" value="${horario.fecha}" /></td>
							<td>${horario.hora}</td>
							<td>${horario.sala} </td>
							<td>$${horario.precio}</td>					
							<td>
								<a href="${urlHorarios}/edit/${horario.id}" class="btn btn-success btn-sm" role="button" title="Editar" data-toggle="tooltip" data-placement="left">
									<span class="glyphicon glyphicon-pencil"></span>
								</a>							
								<button class="btn btn-danger btn-sm delete" id="${horario.id}" title="Eliminar" data-toggle="tooltip" data-placement="right">
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
					<h4 class="modal-title">�Alerta!</h4>
				</div>
				<div class="modal-body" style="text-align:center">
					<p><img src="${urlPublic}/images/error.png" width="48" height="48" class="center">�Estas seguro de querer eliminar el horario?</p>
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
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/bootstrap-table.js"></script>
	<!-- put your locale files after bootstrap-table.js -->
	<script src="${urlPublic}/bootstrap/bootstrap-table-master/src/locale/bootstrap-table-es-MX.js"></script>
	<script type="text/javascript">
		var $urlPage = "${urlHorarios}";
		var $urlPageDelete = "${urlHorarios}/delete/";
	</script>
	<script src="${urlPublic}/js/deleteField.js"></script>
</body>
</html>