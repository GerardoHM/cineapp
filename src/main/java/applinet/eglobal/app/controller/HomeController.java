package applinet.eglobal.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import applinet.eglobal.app.model.Banner;
import applinet.eglobal.app.model.Horario;
import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.model.Pelicula;
import applinet.eglobal.app.service.IBannersService;
import applinet.eglobal.app.service.IHorariosService;
import applinet.eglobal.app.service.INoticiasService;
import applinet.eglobal.app.service.IPeliculasService;
import applinet.eglobal.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IBannersService serviceBanners;
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam("fecha") String fecha, Model model) {
		System.out.println("Buscando todas las películas en exhibición para la fecha: " + fecha);
		
		String fechaGuiones = fecha.replace("/", "-");
		Date fechaActual = null;
		try {
			fechaActual = dateFormat.parse(fechaGuiones);
			System.out.println("Fecha formateada: " + dateFormat.format(fechaActual));
		} catch(ParseException pe) {
			System.err.println(pe);
		}
		
		List<String> listFechas = Utileria.getNextDays(10);
		List<Pelicula> peliculas = servicePeliculas.buscarPorEstatusYHorariosActivos("Activa", fechaActual);
		List<Banner> banners = serviceBanners.buscarTodos();		
		List<Noticia> noticias = serviceNoticias.buscarPorEstatusYFecha("Activa", fechaActual);
		
		model.addAttribute("fechas", listFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("banners", banners);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("noticias", noticias);
		
		return "home";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) throws ParseException {
		Date fecha = dateFormat.parse(dateFormat.format(new Date()));
		List<String> listFechas = Utileria.getNextDays(10);		
		List<Pelicula> peliculas = servicePeliculas.buscarPorEstatusYHorariosActivos("Activa", fecha);
		List<Banner> banners = serviceBanners.buscarTodos();
		List<Noticia> noticias = serviceNoticias.buscarPorEstatusYFecha("Activa", fecha);
		
		model.addAttribute("fechas", listFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("banners", banners);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("noticias", noticias);
		
		return "home";
	}
	
	//@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	@GetMapping(value="/detail/{id}/{fecha}")
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
		System.out.println(fecha);
		
		Date fechaActual = null;
		try {
			fechaActual = dateFormat.parse(dateFormat.format(fecha));
			System.out.println("Fecha formateada: " + dateFormat.format(fechaActual));
		} catch(ParseException pe) {
			System.err.println(pe);
		}
		
		List<Horario> horarios = serviceHorarios.buscarPorIdPeliculaYFecha(idPelicula, fechaActual);
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		return "detalle";
	}
	
	@RequestMapping(value="/acercade", method=RequestMethod.GET)
	public String goAcercaDe() {
		return "acerca";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
