package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import applinet.eglobal.app.model.Noticia;
import applinet.eglobal.app.repository.NoticiasRepository;

public class AppKeywordAnd {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Ejemplo Keyword and
		
		//List<Noticia> lista = repo.findByEstatus("Activa");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Noticia> lista = null;
		/*try {
			lista = repo.findByEstatusAndFecha("Inactiva", format.parse("2017-09-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		for(Noticia n: lista) {
			System.out.println(n);
		}
		
		context.close();
	}
	
}
