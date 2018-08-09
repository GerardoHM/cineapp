package pruebascrudrepo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

public class AppFindAllById {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Recuperar varios registros por id [método findAllById del repositorio]
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(4);
		ids.add(7);
		
		Iterable<Noticia> it = repo.findAllById(ids);
		for(Noticia n: it) {
			System.out.println(n);
		}
		
		context.close();
	}

}
