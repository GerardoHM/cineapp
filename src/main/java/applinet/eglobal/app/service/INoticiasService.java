package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import applinet.eglobal.app.model.Noticia;

public interface INoticiasService {
	
	List<Noticia> buscarTodas();
	void guardar(Noticia noticia);
	Optional<Noticia> buscarPorId(int idnoticia);
	List<Noticia> buscarPorEstatusYFecha(String estatus, Date fecha);
	void eliminar(int idnoticia);
}
