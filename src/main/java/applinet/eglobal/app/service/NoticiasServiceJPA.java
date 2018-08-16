package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Banner;
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
	public List<Noticia> buscarTodas() {
		return noticiasRepo.findAll();
	}

	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}

	@Override
	public List<Noticia> buscarPorEstatusYFecha(String estatus, Date fecha) {
		return noticiasRepo.findFirst3ByEstatusAndFecha(estatus, fecha);
	}

	@Override
	public Optional<Noticia> buscarPorId(int idnoticia) {
		return noticiasRepo.findById(idnoticia);
	}

	@Override
	public void eliminar(int idnoticia) {
		noticiasRepo.deleteById(idnoticia);
	}
}
