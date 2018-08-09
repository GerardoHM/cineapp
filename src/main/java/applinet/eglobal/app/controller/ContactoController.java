package applinet.eglobal.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import applinet.eglobal.app.model.Contacto;
import applinet.eglobal.app.service.IPeliculasService;

@Controller
public class ContactoController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@GetMapping("/contacto")
	public String mostrarFormulario(@ModelAttribute("instanciaContacto") Contacto contacto, Model model) {
		model.addAttribute("generos", servicePeliculas.buscarGeneros());
		model.addAttribute("tipos", tipoNotificaciones());
		
		return "formContacto";
	}
	
	@PostMapping("/contacto")
	public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto) {
		// Este objeto ya se podría almacenar
		System.out.println(contacto);
		
		return "redirect:/contacto";
	}
	
	private List<String> tipoNotificaciones(){
		// Nota: La lista se podría generar apartir de una BD
		List<String> tipos = new LinkedList<String>();
		tipos.add("Estrenos");
		tipos.add("Promociones");
		tipos.add("Noticias");
		tipos.add("Preventas");
		
		return tipos;
	}
	
}
