package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;

import applinet.eglobal.app.model.Noticia;

public interface INoticiasService {
	
	void guardar(Noticia noticia);
	List<Noticia> buscarPorEstatusYFecha(String estatus, Date fecha);
}
