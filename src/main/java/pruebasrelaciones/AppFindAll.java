package pruebasrelaciones;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Pelicula;
import applinet.eglobal.app.repository.PeliculasRepository;

public class AppFindAll {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		// Recuperar todos las entidades de tipo Pelicula
		PeliculasRepository repo = context.getBean("peliculasRepository", PeliculasRepository.class);
		List<Pelicula> listaPeliculas = repo.findAll();
		
		for(Pelicula pelicula: listaPeliculas) {
			System.out.println(pelicula);
		}
		
		context.close();
	}

}
