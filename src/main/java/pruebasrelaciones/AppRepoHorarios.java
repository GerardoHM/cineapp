package pruebasrelaciones;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Horario;
import applinet.eglobal.app.repository.HorariosRepository;

public class AppRepoHorarios {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		HorariosRepository repo = context.getBean("horariosRepository", HorariosRepository.class);
		
		List<Horario> listaHorarios = repo.findAll();
		
		System.out.println("No. entidades: " + listaHorarios.size());
		
		for(Horario h: listaHorarios) {
			System.out.println(h);
		}
		
		context.close();
	}

}
