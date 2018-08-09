package applinet.eglobal.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Detalle;
import applinet.eglobal.app.repository.DetallesRepository;

@Service
public class DetalleServiceJPA implements IDetalleService {

	@Autowired
	private DetallesRepository detalleRepo;
	
	public DetalleServiceJPA() {
		System.out.println("Creando una instancia de la clase DetalleServiceJPA");
	}
	
	@Override
	public void insertar(Detalle detalle) {
		detalleRepo.save(detalle);
	}

	@Override
	public void eliminar(int idDetalle) {
		detalleRepo.deleteById(idDetalle);
	}

}
