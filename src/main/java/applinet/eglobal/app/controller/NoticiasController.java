package applinet.eglobal.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	@GetMapping(value="/create")
	public String crear() {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save")
	//public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, 
			//@RequestParam("detalle") String detalle) {
	// Con data binding
	public String guardar(Noticia noticia) {
		
		System.out.println("Titulo: " + noticia.getTitulo());
		System.out.println("Estatus: " + noticia.getEstatus());
		System.out.println("Detalle: " + noticia.getDetalle());
		
		serviceNoticias.guardar(noticia);
		
		return "noticias/formNoticia";
	}
	
}
