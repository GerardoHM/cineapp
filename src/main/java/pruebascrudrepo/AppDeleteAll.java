package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.repository.NoticiasRepository;

public class AppDeleteAll {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Borrar todos los registros [m�todo deleteAll del repositorio]
		// ALERTA ES UN M�TODO ��MUY PELIGROSO!!
		repo.deleteAll();
		
		
		context.close();
	}

}
