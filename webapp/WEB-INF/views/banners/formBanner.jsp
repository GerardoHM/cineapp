<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	
	      <form action="${urlForm}" method="post" enctype="multipart/form-data">
            <div class="row">         
               <div class="col-sm-6">
                  <div class="form-group">
                     <label for="titulo">Titulo</label>             
                     <input type="text" class="form-control" name="titulo" id="titulo" required="required"/>
                  </div>
               </div>

               <div class="col-sm-4">
                  <div class="form-group">
                     <label for="imagen">Imagen</label>
                     <input type="file" id="archivoImagen" name="archivoImagen" required="required" />
                     <p class="help-block">Tamaño recomendado: 1140 x 250 </p>
                  </div> 
               </div> 

               <div class="col-sm-2">
                  <div class="form-group">
                     <label for="estatus">Estatus</label>             
                     <select id="estatus" name="estatus" class="form-control">
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>                
                     </select>  
                  </div>
               </div>
            </div>

            <button type="submit" class="btn btn-danger" >Guardar</button>
         </form> 

         <hr class="featurette-divider">

         <!-- FOOTER -->
         <footer>        
            <p class="pull-right"><a href="#">Back to top</a></p>
            <p>&copy; 2017 My CineSite, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
         </footer>

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