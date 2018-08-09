package pruebasrelaciones;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Detalle;
import applinet.eglobal.app.repository.DetallesRepository;

public class AppRepoDetalles {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		DetallesRepository repo = context.getBean("detallesRepository", DetallesRepository.class);
		
		List<Detalle> listaDetalles = repo.findAll();
		for(Detalle d: listaDetalles) {
			System.out.println(d);
		}
		
		context.close();
	}

}
