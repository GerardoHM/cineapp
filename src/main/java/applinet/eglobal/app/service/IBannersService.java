package applinet.eglobal.app.service;

import java.util.List;

import applinet.eglobal.app.model.Banner;

public interface IBannersService {

	void insertar(Banner banner); 
	List<Banner> buscarTodos();
	void eliminar(int idbanner);
	
}
