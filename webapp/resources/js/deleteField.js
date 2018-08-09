/**
 * 
 */
$( document ).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	
	var $table = $("#dataTable");
	
	$("#dataTable").on("click", ".delete", function(e){
        var $id = this.id;
        $("#idFieldDelete").val($id);		
		$("#confirmModal").modal("show");
	});
	
	$("#confirmacion").click(function(){
		/* El evento trigger no funciona pues no replica exactamente el evento click de jquery
		 * La solución es disparar el evento con javascript puro (no jQuery). Primero obtenemos el elemento DOM (no jQuery) y luego utilizamos el método .click() nativo de los nodos del DOM:
		 * document.getElementById("home-link").click();
		 * También podemos utilizar el método .get() para obtener un elemento DOM a partir de un objeto jQuery:
		*/
		var idFieldToDelete = $("#idFieldDelete").val();

		$.ajax({
            url: $urlPageDelete + idFieldToDelete,
            type : "GET",
            dataType: 'json',
            success : function(response) {        	
	            var url = $urlPage + '/index?mensaje=' + encodeURIComponent(response.mensaje); 
	            $(location).attr('href', url);
            },
            error : function(xhr, status, error) {
            	alert(status);
            	alert(error);
            }
        });
	});
});