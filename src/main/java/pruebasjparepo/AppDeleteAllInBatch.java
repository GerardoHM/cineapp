package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.repository.NoticiasRepository;

public class AppDeleteAllInBatch {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		/* Método deleteAllInBatch de la interfaz JpaRepository -> (es mas eficiente)
		   delete from Noticias
		*/
		
		/* Método deleteAll de la interfaz CrudRepository -> (no muy eficiente)
		   delete from Noticias where id=?
		   delete from Noticias where id=?
		   delete from Noticias where id=?
		*/		
		repo.deleteAllInBatch();
		
		context.close();		
	}

}
