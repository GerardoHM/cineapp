package applinet.eglobal.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Banner;
import applinet.eglobal.app.repository.BannersRepository;

@Service
public class BannerServiceJPA implements IBannersService {
	
	@Autowired
	BannersRepository bannersRepo;

	@Override
	public void insertar(Banner banner) {
		bannersRepo.save(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		return bannersRepo.findAll();
	}
	
	@Override
	public List<Banner> buscarPorEstatus(String estatus) {
		return bannersRepo.findByEstatus(estatus);
	}
	
	@Override
	public Optional<Banner> buscarPorId(int idbanner) {
		return bannersRepo.findById(idbanner);
	}

	@Override
	public void eliminar(int idbanner) {
		bannersRepo.deleteById(idbanner);		
	}

}
