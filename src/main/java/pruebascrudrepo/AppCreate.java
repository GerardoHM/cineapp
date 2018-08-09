package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

// Aplicaci�n para persistir (Crear) en la tabla Noticias un objeto de tipo Noticia
public class AppCreate {

	public static void main(String[] args) {
		Noticia noticia =  new Noticia();
		noticia.setTitulo("Pr�ximo Estreno: La mansi�n wintchester");
		noticia.setDetalle("El mes de junio se estrena la terrorifica pel�cula La mansi�n wintchester.");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		repo.save(noticia);
		
		context.close();
	}

}
