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
      <title>Creacion de imagenes del Banner</title>
      <spring:url value="/resources" var="urlPublic"></spring:url>
	  <spring:url value="/banners/save" var="urlForm"></spring:url>
	  <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
	  <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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
	
	      <form:form action="${urlForm}" method="post" modelAttribute="banner" enctype="multipart/form-data">
            <div class="row">         
               <div class="col-sm-6">
                  <div class="form-group">
                  	 <form:hidden path="id" id="idbanner"/>
                     <label for="titulo">Titulo</label>
                     <form:input type="text" class="form-control" path="titulo" id="titulo" required="required" />
                  </div>
               </div>

               <div class="col-sm-4">
                  <div class="form-group">
                  	 <label for="imagen">Imagen</label>
		             <form:hidden path="archivo"/>
              		 <input type="file" id="archivoImagen" name="archivoImagen" />
                     <p class="help-block">Tamaño recomendado: 1140 x 250 </p>
                  </div> 
               </div> 

               <div class="col-sm-2">
                  <div class="form-group">
                     <label for="estatus" class="control-label">Estatus</label>              
		             <form:select id="estatus" path="estatus" class="form-control">
		               <form:option value="Activo">Activo</form:option>
		               <form:option value="Inactivo">Inactivo</form:option>               
		             </form:select>
                  </div>
               </div>
            </div>

            <button type="submit" class="btn btn-primary" >Guardar</button>
         </form:form> 

         <!-- FOOTER -->
         <hr class="featurette-divider">

      	 <jsp:include page="../includes/footer.jsp"></jsp:include>	

      </div> <!-- /container -->

      <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script> 
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
      $(function () {
          $("#fechaEstreno").datepicker({dateFormat: 'dd-mm-yy'});
        }
      );
    </script>

   </body>
</html>