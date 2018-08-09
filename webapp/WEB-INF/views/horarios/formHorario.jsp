<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="author" content="">
		<title>Creación de Horarios</title>
		<spring:url value="/resources" var="urlPublic"></spring:url>
		<spring:url value="/" var="urlRoot"></spring:url>
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
		<link rel="stylesheet" href="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.min.css">
		<link rel="stylesheet" href="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.theme.min.css">
	</head>

	<body>

		<!-- Menu -->
      	<jsp:include page="../includes/menu.jsp"></jsp:include>

		<div class="container theme-showcase" role="main">

			<h3 class="blog-title"><span class="label label-success">Datos del Horario</span></h3>
			
			<spring:hasBindErrors name="horario">
				<div class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<c:forEach var="error" items="${errors.allErrors}">
							<li><spring:message message="${error}" /></li>
						</c:forEach>
					</ul>
				</div>
			</spring:hasBindErrors>

			<form:form action="${urlRoot}horarios/save" method="post" modelAttribute="horario">
				<div class="row">         
					<div class="col-sm-3">
						<div class="form-group">
							<label for="idPelicula" class="control-label">Pelicula</label>
							<form:select id="idPelicula" path="pelicula.id"	class="form-control" items="${peliculas}" itemLabel="titulo" itemValue="id" />
							<form:hidden path="id" id="idhorario"/>
						</div> 
					</div>
				</div>
				<div class="row"> 
					<div class="col-sm-3">
						<div class="form-group">
							<label for="fecha">Fecha</label>             
							<form:input type="text" class="form-control" path="fecha" id="fecha" required="required" />
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="hora">Hora</label>
							<form:input type="text" class="form-control" path="hora" id="hora" placeholder="Formato: HH:mm Ejemplo 21:30" required="required"/>
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="sala" class="control-label">Sala</label>              
							<form:select id="sala" path="sala" class="form-control">
								<form:option value="Premium">Sala Premium</form:option>
								<form:option value="Sala 1">Sala 1</form:option>
								<form:option value="Sala 2">Sala 2</form:option>
								<form:option value="Sala 3">Sala 3</form:option>                
							</form:select>             
						</div> 
					</div>

					<div class="col-sm-3">
						<div class="form-group">
							<label for="precio">Precio</label>
							<form:input type="text" class="form-control" path="precio" id="precio" required="required"/>
						</div>  
					</div>

				</div>

				<button type="submit" class="btn btn-primary" >Guardar</button>
			</form:form> 

			<hr class="featurette-divider">

			<!-- FOOTER -->
         	<jsp:include page="../includes/footer.jsp"></jsp:include>

		</div> <!-- /container -->

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
		<script src="${urlPublic}/jquery/dist/jquery.min.js"></script> 
	    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
	    <script src="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.min.js"></script>
	    <script src="${urlPublic}/js/ui.datepicker-es.js"></script>
	</body>
</html>
