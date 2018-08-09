package applinet.eglobal.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Pelicula;
import applinet.eglobal.app.repository.PeliculasRepository;

@Service
public class PeliculaServiceJPA implements IPeliculasService {

	@Autowired
	private PeliculasRepository peliculasRepo;
	
	public PeliculaServiceJPA() {
		System.out.println("Creando una instancia de la clase PeliculaServiceJPA");
	}
	
	@Override
	public void insertar(Pelicula pelicula) {
		peliculasRepo.save(pelicula);
	}

	@Override
	public List<Pelicula> buscarTodas() {
		List<Pelicula> listaPelicula = peliculasRepo.findAll();
		System.out.println("listaPelicula: " + listaPelicula);
		return listaPelicula;
	}
	
	@Override
	public List<Pelicula> buscarActivas() {
		List<Pelicula> listaPelicula = peliculasRepo.findByEstatus("Activa");
		System.out.println("listaPelicula: " + listaPelicula);
		return listaPelicula;
	}
	
	@Override
	public List<Pelicula> buscarPorEstatusYHorariosActivos(String estatus, Date fecha) {
		return peliculasRepo.findDistinctHorarios_IdPeliculaByEstatusAndHorarios_Fecha(estatus, fecha);
	}
	
	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		return peliculasRepo.findAll(page);
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		Optional<Pelicula> optional = peliculasRepo.findById(idPelicula);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<String> buscarGeneros() {
		//Nota: Esta lista podría ser obtenida de una BD
		List<String> generos = new LinkedList<>();
		generos.add("Acción");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Thriller");
		generos.add("Acción y Aventura");
		generos.add("Acción y Fantasia");
		generos.add("Romantica");
		generos.add("Ciencia Ficción");
		generos.add("Infantil");

		return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		peliculasRepo.deleteById(idPelicula);
	}

}
