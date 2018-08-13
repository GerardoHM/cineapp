package applinet.eglobal.app.service;

import java.util.List;
import java.util.Optional;

import applinet.eglobal.app.model.Banner;

public interface IBannersService {

	void insertar(Banner banner); 
	List<Banner> buscarTodos();
	List<Banner> buscarPorEstatus(String estatus);
	Optional<Banner> buscarPorId(int idbanner);
	void eliminar(int idbanner);
	
}
