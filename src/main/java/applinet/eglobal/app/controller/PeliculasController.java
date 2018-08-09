package applinet.eglobal.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import applinet.eglobal.app.model.Pelicula;
import applinet.eglobal.app.service.IDetalleService;
import applinet.eglobal.app.service.IPeliculasService;
import applinet.eglobal.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IDetalleService serviceDetalle;
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, HttpServletRequest request) {
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		String mensaje = request.getParameter("mensaje");
		model.addAttribute("mensaje", mensaje);
		
		return "peliculas/listPeliculas";
	}
	
	@GetMapping(value="indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> listaPeliculasPag = servicePeliculas.buscarTodas(page);
		model.addAttribute("peliculas", listaPeliculasPag);
		
		return "peliculas/listPeliculasPagination";
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		return "peliculas/formPelicula";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, Model model,
			@RequestParam("archivoImagen") MultipartFile multipart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Existieron errores.");
			return "peliculas/formPelicula";
		}
		
		if(!multipart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multipart, request);
			pelicula.setImagen(nombreImagen);
		}
		
		serviceDetalle.insertar(pelicula.getDetalle());
		servicePeliculas.insertar(pelicula);

		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		model.addAttribute("mensaje", "El registro fue guardado con éxito.");
		
		return "peliculas/listPeliculas";
	}
	
	@PostMapping("/savePag")
	public String guardarPagination(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multipart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Existieron errores.");
			return "peliculas/formPelicula";
		}
		
		if(!multipart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multipart, request);
			pelicula.setImagen(nombreImagen);
		}
		
		System.out.println("Antes: " + pelicula.getDetalle());
		serviceDetalle.insertar(pelicula.getDetalle());
		System.out.println("Después: " + pelicula.getDetalle());
		servicePeliculas.insertar(pelicula);
		attributes.addFlashAttribute("mensaje", "El registro fue guardado con éxito.");
		
		return "redirect:/peliculas/indexPaginate";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		model.addAttribute("pelicula", pelicula);
		
		return "peliculas/formPelicula";
	}
	
	/*@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		// Primero eliminar la pelicula
		servicePeliculas.eliminar(idPelicula);
		// Después, eliminar los detalles.
		serviceDetalle.eliminar(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada con éxito.");
		
		return "redirect:/peliculas/indexPaginate";
	}*/
	
	/**
	 * Metodo para borrar el registro por id desde ajax
	 * @param idHorario
	 * @param attributes para enviar mensaje satisfactorio
	 * @return
	 */
	@GetMapping(value="/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		// Primero eliminar la pelicula
		servicePeliculas.eliminar(idPelicula);
		// Después, eliminar los detalles.
		serviceDetalle.eliminar(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada con éxito.");
		String mensaje = "La película " + pelicula.getTitulo() + " fue eliminada con éxito";
		
		HashMap<String, String> list = new HashMap<String,String>();
		list.put("mensaje", mensaje);
		list.put("id", Integer.toString(idPelicula));
		System.out.println(list);
		Gson gson = new Gson(); 
		
		return gson.toJson(list);
	}
	
	// Cuando se utiliza la anotación @ModelAttribute a nivel de un método, va a estar disponible lo que retorna el método para todos los métodos del controlador
	@ModelAttribute("generos")
	public List<String> getGeneros(){
		return servicePeliculas.buscarGeneros();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
