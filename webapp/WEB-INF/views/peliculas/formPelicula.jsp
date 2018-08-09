<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">   
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Creacion de Peliculas</title>
	<spring:url value="/resources" var="urlPublic"></spring:url>
	<spring:url value="/peliculas/save" var="urlForm"></spring:url>
    <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
    <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
    <link rel="stylesheet" href="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.min.css">
	<link rel="stylesheet" href="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.theme.min.css">
  </head>

  <body>

    <!-- Menu -->
    <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <div class="page-header">
      	<h3 class="blog-title"><span class="label label-success">Datos de la Pelicula</span></h3>
      </div>
      
      <spring:hasBindErrors name="pelicula">
      	<div class="alert alert-danger" role="alert">
      		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      		Por favor corrija los siguientes errores:
      		<ul>
      			<c:forEach items="${errors.allErrors}" var="error">
      				<li><spring:message message="${error}" /></li>
      			</c:forEach>
      		</ul>
      	</div>
      </spring:hasBindErrors>

      <form:form action="${urlForm}" method="post" enctype="multipart/form-data" modelAttribute="pelicula">
      	<div class="row">
          <div class="col-sm-3">
            <div class="form-group">
            	<img src="${urlPublic}/images/${pelicula.imagen}" title="Imagen actual de la película" width="150" height="200" >
            </div>  
          </div>
        </div>
      
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="titulo">Título</label>
              <form:hidden path="id"/>
              <form:hidden path="detalle.id"/>
              <form:input type="text" class="form-control" path="titulo" id="titulo" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="duracion">Duración</label>
              <form:input type="text" class="form-control" path="duracion" id="duracion" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="clasificacion" class="control-label">Clasificación</label>              
              <form:select id="clasificacion" path="clasificacion" class="form-control">
                <form:option value="A">Clasificación A</form:option>
                <form:option value="B">Clasificación B</form:option>
                <form:option value="C">Clasificación C</form:option>                  
              </form:select>             
            </div> 
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="genero" class="control-label">Género</label>
              <form:select id="genero" path="genero" class="form-control" items="${generos}" />    
              <!-- <form:select id="genero" path="genero" class="form-control">
                <form:option value="Accion">Acción</form:option>
                <form:option value="Aventura">Aventura </form:option>
                <form:option value="Clasicas">Clasicas</form:option>                  
                <form:option value="Comedia Romantica">Comedia Romántica</form:option>                  
                <form:option value="Drama">Dráma</form:option>                  
                <form:option value="Terror">Terror</form:option>                  
                <form:option value="Infantil">Infantil</form:option>                  
                <form:option value="Accion y Aventura">Acción y Aventura</form:option>                  
                <form:option value="Romantica">Romántica</form:option>                  
              </form:select> -->   
            </div> 
          </div>         
        </div>

        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus" class="control-label">Estatus</label>              
              <form:select id="genero" path="estatus" class="form-control">
                <form:option value="Activa">Activa</form:option>
                <form:option value="Inactiva">Inactiva</form:option>               
              </form:select>             
            </div> 
          </div>     
          <div class="col-sm-3">
            <div class="form-group">
              <label for="fechaEstreno">Fecha Estreno</label>             
              <form:input type="text" class="form-control" path="fechaEstreno" id="fecha" required="required" />
            </div>  
          </div>

          <div class="col-sm-3">
            <div class="form-group">
              <label for="imagen">Imagen</label>
              <form:hidden path="imagen"/>
              <input type="file" id="archivoImagen" name="archivoImagen" />
              <p class="help-block">Imagen de la pelicula</p>
            </div> 
          </div>
        </div>
  
        <div class="page-header">
            <h3 class="blog-title"><span class="label label-success">Detalles</span></h3>
        </div>

        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="director">Director</label>
              <form:input type="text" class="form-control" path="detalle.director" id="director" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="actores">Actores</label>
              <form:input type="text" class="form-control" path="detalle.actores" id="actores" required="required" />
            </div>  
          </div>

          <div class="col-sm-6">
            <div class="form-group">
              <label for="trailer">URL del Trailer (Youtube)</label>
              <form:input type="text" class="form-control" path="detalle.trailer" id="trailer" placeholder="URL completa del video de YOUTUBE" required="required" />
            </div>  
          </div> 
        </div> 

        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="sinopsis">Sinopsis</label>
              <form:textarea class="form-control" rows="5" path="detalle.sinopsis" id="sinopsis"></form:textarea>
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
    <script src="${urlPublic}/jquery/dist/jquery.min.js"></script> 
    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${urlPublic}/jquery/jquery-ui-1.12.1.custom.flick/jquery-ui.min.js"></script>
    <script src="${urlPublic}/js/ui.datepicker-es.js"></script>
  </body>
</html>
