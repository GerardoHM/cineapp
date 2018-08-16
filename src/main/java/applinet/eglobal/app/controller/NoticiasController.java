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

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	/**
	 * Metodo para mostrar el listado de banners
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model, HttpServletRequest request) {
		List<Noticia> listNoticias = serviceNoticias.buscarTodas();
		model.addAttribute("noticias", listNoticias);
		String mensaje = request.getParameter("mensaje");
		model.addAttribute("mensaje", mensaje);
		
		return "noticias/listNoticias";
	}
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save")
	//public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, 
			//@RequestParam("detalle") String detalle) {
	// Con data binding
	public String guardar(Noticia noticia, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println("Existieron errores.");
			System.out.println(result.getFieldErrors());
			return "banners/formBanner";
		}
		
		serviceNoticias.guardar(noticia);
		List<Noticia> listNoticias = serviceNoticias.buscarTodas();
		model.addAttribute("noticias", listNoticias);
		model.addAttribute("mensaje", "El registro fue guardado con éxito.");
		
		return "noticias/listNoticias";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idnoticia, Model model) {
		Optional<Noticia> noticia = serviceNoticias.buscarPorId(idnoticia);
		model.addAttribute("noticia", noticia);
		
		return "noticias/formNoticia";
	}
	
	/**
	 * Metodo para borrar el registro por id desde ajax
	 * @param idnoticia
	 * @param attributes para enviar mensaje satisfactorio
	 * @return
	 */
	@GetMapping(value="/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String eliminar(@PathVariable("id") int idnoticia, RedirectAttributes attributes) {
		System.out.println("idbanner: " + idnoticia);
		serviceNoticias.eliminar(idnoticia);
		String mensaje = "La noticia a sido eliminada.";
		
		HashMap<String, String> list = new HashMap<String,String>();
		list.put("mensaje", mensaje);
		list.put("id", Integer.toString(idnoticia));
		System.out.println(list);
		Gson gson = new Gson(); 
		
		return gson.toJson(list);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
