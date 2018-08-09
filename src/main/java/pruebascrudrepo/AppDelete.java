package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.repository.NoticiasRepository;

public class AppDelete {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Operación CRUD - Delete [metodo deleteById del repositorio]
		int idNoticia = 2;
		//repo.deleteById(idNoticia);
		if(repo.existsById(idNoticia)){
			repo.deleteById(idNoticia);
			System.out.println("Se elimino el registro con: " + idNoticia);
		} else {
			System.out.println("No existe algún registro con el id: " + idNoticia);
		}
		
		context.close();
	}

}
