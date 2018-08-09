package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

public class AppUpdate {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Operación CRUD - Update [método save del repositorio
		
		// 1. Primero buscamos la entidad que vamos a modificar (findById)
		Optional<Noticia> optional = repo.findById(1);
		
		// 2. Modificamos la entidad y la guardamos
		if(optional.isPresent()) {
			Noticia noticia = optional.get();
			System.out.println("Objeto anterior:");
			System.out.println(noticia);
			noticia.setEstatus("Inactiva");
			repo.save(noticia);
			System.out.println("Objeto actual:");
			System.out.println(noticia);
			
		} else {
			System.out.println("No se encontro alguna noticia con el id ingresado");
		}
		
		context.close();
	}
}
