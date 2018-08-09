package applinet.eglobal.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import applinet.eglobal.app.model.Horario;
import applinet.eglobal.app.model.Pelicula;
import applinet.eglobal.app.service.IHorariosService;
import applinet.eglobal.app.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {
	
	@Autowired
	IHorariosService serviceHorarios;
	
	@Autowired
	IPeliculasService servicePeliculas;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, HttpServletRequest request) {
		List<Horario> lista = serviceHorarios.buscarTodos();
		model.addAttribute("horarios", lista);
		String mensaje = request.getParameter("mensaje");
		model.addAttribute("mensaje", mensaje);
		
		return "horarios/listHorarios";
	}
		
	/**
	 * Metodo para mostrar el formulario para crear un nuevo horario
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		List<Pelicula> listaPeliculas = servicePeliculas.buscarActivas();
		model.addAttribute("peliculas", listaPeliculas);
		return "horarios/formHorario";
	}
		
	/**
	 * Metodo para guardar el registro del Horario
	 * @param horario
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()){
			List<Pelicula> listaPeliculas = servicePeliculas.buscarActivas();
			model.addAttribute("peliculas", listaPeliculas);
			return "horarios/formHorario";
		}

		serviceHorarios.insertar(horario);
		attributes.addFlashAttribute("mensaje", "El horario fue guardado!");
		
		List<Horario> lista = serviceHorarios.buscarTodos();
		model.addAttribute("horarios", lista);
		model.addAttribute("mensaje", "El registro fue guardado con éxito.");
		
		return "horarios/listHorarios";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idHorario, Model model) {
		List<Pelicula> listaPeliculas = servicePeliculas.buscarActivas();
		Optional<Horario> horario = serviceHorarios.buscarPorIdHorario(idHorario);
		model.addAttribute("peliculas", listaPeliculas);
		model.addAttribute("horario", horario);
		
		return "horarios/formHorario";
	}
	
	/**
	 * Metodo para borrar el registro por id
	 * @param idHorario
	 * @param attributes para enviar mensaje satisfactorio
	 * @return
	 */
	/*@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
	System.out.println("idHorario: " + idHorario);
		Optional<Horario> horario = serviceHorarios.buscarPorIdHorario(idHorario);
		// Primero eliminar la pelicula
		//serviceHorarios.eliminar(idHorario);
		attributes.addFlashAttribute("mensaje", "El horario de la pelicula " + horario.get().getPelicula().getTitulo() + " fue eliminado con éxito.");
		
		return "redirect:/horarios/index";
	}*/
	
	/**
	 * Metodo para borrar el registro por id desde ajax
	 * @param idHorario
	 * @param attributes para enviar mensaje satisfactorio
	 * @return
	 */
	@GetMapping(value="/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String eliminar(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
	System.out.println("idHorario: " + idHorario);
		Optional<Horario> horario = serviceHorarios.buscarPorIdHorario(idHorario);
		// Primero eliminar la pelicula
		serviceHorarios.eliminar(idHorario);
		String mensaje = "El horario de la pelicula " + horario.get().getPelicula().getTitulo() + " fue eliminado con éxito.";
		
		HashMap<String, String> list = new HashMap<String,String>();
		list.put("mensaje", mensaje);
		list.put("id", Integer.toString(idHorario));
		System.out.println(list);
		Gson gson = new Gson(); 
		
		return gson.toJson(list);
	}
	
	// Ejercicio: Crear metodo para personalizar el Data Binding para las todas las propiedades de tipo Date
	@InitBinder("horario")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
