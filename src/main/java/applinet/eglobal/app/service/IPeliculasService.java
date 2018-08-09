package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import applinet.eglobal.app.model.Pelicula;

public interface IPeliculasService {
	void insertar(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	List<Pelicula> buscarActivas();
	List<Pelicula> buscarPorEstatusYHorariosActivos(String estatus, Date fecha);
	Page<Pelicula> buscarTodas(Pageable page);
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();
	void eliminar(int idPelicula);
}
