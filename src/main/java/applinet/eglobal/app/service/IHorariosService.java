package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import applinet.eglobal.app.model.Horario;

public interface IHorariosService {
	List<Horario> buscarTodos();
	Optional<Horario> buscarPorIdHorario(int idHorario);
	List<Horario> buscarPorIdPeliculaYFecha(int idPelicula, Date date);
	void insertar(Horario horario);
	void eliminar(int idHorario);
}
