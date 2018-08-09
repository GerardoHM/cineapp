package applinet.eglobal.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import applinet.eglobal.app.model.Horario;
import applinet.eglobal.app.repository.HorariosRepository;

@Service
public class HorariosServiceJPA implements IHorariosService {
	
	@Autowired
	private HorariosRepository horariosRepo;
	
	public HorariosServiceJPA() {
		System.out.println("Creando una instancia de la clase HorariosServiceJPA");
	}
	
	@Override
	public List<Horario> buscarTodos() {
		return horariosRepo.findAll();
	}
	
	@Override
	public Optional<Horario> buscarPorIdHorario(int idHorario) {
		return horariosRepo.findById(idHorario);
	}

	@Override
	public List<Horario> buscarPorIdPeliculaYFecha(int idPelicula, Date date) {
		return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, date);
	}
	
	@Override
	public void insertar(Horario horario) {
		horariosRepo.save(horario);
	}

	@Override
	public void eliminar(int idHorario) {
		horariosRepo.deleteById(idHorario);
	}


}
