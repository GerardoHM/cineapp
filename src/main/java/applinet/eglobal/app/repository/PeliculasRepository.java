package applinet.eglobal.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applinet.eglobal.app.model.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
	List<Pelicula> findByEstatus(String estatus);
	List<Pelicula> findDistinctHorarios_IdPeliculaByEstatusAndHorarios_Fecha(String estatus, Date fecha);
}
