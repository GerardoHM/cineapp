package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

public class AppRead {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Operacion CRUD - Read(método findById del respositorio)
		Optional<Noticia> noticia = repo.findById(1);
		System.out.println(noticia);
		
		context.close();
	}

}
