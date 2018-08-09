package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

@Service
public class NoticiasServiceJPA implements INoticiasService {

	@Autowired
	private NoticiasRepository noticiasRepo;
	
	public NoticiasServiceJPA() {
		System.out.println("Creando una instancia de la clase NoticiasServiceJPA");
	}

	@Override
	public void guardar(Noticia noticia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Noticia> buscarPorEstatusYFecha(String estatus, Date fecha) {
		return noticiasRepo.findFirst3ByEstatusAndFecha(estatus, fecha);
	}
}
