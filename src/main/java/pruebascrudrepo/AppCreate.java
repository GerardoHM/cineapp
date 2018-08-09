package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

// Aplicación para persistir (Crear) en la tabla Noticias un objeto de tipo Noticia
public class AppCreate {

	public static void main(String[] args) {
		Noticia noticia =  new Noticia();
		noticia.setTitulo("Próximo Estreno: La mansión wintchester");
		noticia.setDetalle("El mes de junio se estrena la terrorifica película La mansión wintchester.");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		repo.save(noticia);
		
		context.close();
	}

}
