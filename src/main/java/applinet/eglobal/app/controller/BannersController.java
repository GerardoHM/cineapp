package applinet.eglobal.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import applinet.eglobal.app.model.Banner;
import applinet.eglobal.app.service.IBannersService;
import applinet.eglobal.app.util.Utileria;

@Controller
@RequestMapping("/banners/")
public class BannersController {

	@Autowired
	private IBannersService serviceBanners;
		
	/**
	 * Metodo para mostrar el listado de banners
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Banner> listaBanners = serviceBanners.buscarTodos();
		model.addAttribute("banners", listaBanners);
		
		return "banners/listBanners";
	}
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo Banner
	 * @return
	 */
	@GetMapping("/create")
	public String crear() {
		return "banners/formBanner";		
	}
	
	/**
	 * Metodo para guardar el objeto de modelo de tipo Banner
	 * @param banner
	 * @param result
	 * @param attributes
	 * @param multiPart
	 * @param request
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multipart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Existieron errores.");
			return "peliculas/formPelicula";
		}
		
		if(!multipart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multipart, request);
			banner.setArchivo(nombreImagen);
		}
		
		serviceBanners.insertar(banner);
		attributes.addFlashAttribute("mensaje", "El registro fue guardado con éxito.");
		
		return "redirect:/banners/index";
	}
	
	/**
	 * Metodo para borrar el registro por id desde ajax
	 * @param idHorario
	 * @param attributes para enviar mensaje satisfactorio
	 * @return
	 */
	@GetMapping(value="/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String eliminar(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
		System.out.println("idHorario: " + idHorario);
		// Primero eliminar la pelicula
		serviceBanners.eliminar(idHorario);
		String mensaje = "El banner a sido eliminado.";
		
		HashMap<String, String> list = new HashMap<String,String>();
		list.put("mensaje", mensaje);
		list.put("id", Integer.toString(idHorario));
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
