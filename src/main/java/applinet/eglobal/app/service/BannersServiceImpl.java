package applinet.eglobal.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import applinet.eglobal.app.model.Banner;

@Service
public class BannersServiceImpl implements IBannersService {

	private List<Banner> lista = null; 
	/**
	 * En el constructor creamos una lista enlazada con objetos de tipo Banner
	 */
	public BannersServiceImpl() {
		//System.out.println("Creando una instancia de la clase BannersServiceImpl");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			lista = new LinkedList<>();
			
			//Banner 1
			Banner banner1 = new Banner();
			banner1.setId(1);
			banner1.setTitulo("En cartelera: Kong La isla calavera y Logan");
			banner1.setFecha(formatter.parse("03-05-2018"));
			banner1.setArchivo("slide1.jpg");
			
			//Banner 2
			Banner banner2 = new Banner();
			banner2.setId(2);
			banner2.setTitulo("La bella y la bestia");
			banner2.setFecha(formatter.parse("17-05-2018"));
			banner2.setArchivo("slide2.jpg");
			
			//Banner 3
			Banner banner3 = new Banner();
			banner3.setId(3);
			banner3.setTitulo("Spider man: De regreso a casa");
			banner3.setFecha(formatter.parse("09-05-2018"));
			banner3.setArchivo("slide3.jpg");
			
			//Banner 4
			Banner banner4 = new Banner();
			banner4.setId(4);
			banner4.setTitulo("Cars 3");
			banner4.setFecha(formatter.parse("28-06-2018"));
			banner4.setArchivo("slide4.jpg");
			
			//Banner 5
			Banner banner5 = new Banner();
			banner5.setId(5);
			banner5.setTitulo("Un golpe con estilo");
			banner5.setFecha(formatter.parse("24-05-2018"));
			banner5.setArchivo("slide5.jpg");
			banner5.setEstatus("Inactivo");
			
			//Agregar los objetos Banner a la lista
			lista.add(banner1);
			lista.add(banner2);
			lista.add(banner3);
			lista.add(banner4);
			lista.add(banner5);
		} catch(ParseException e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Insertamos un objeto de tipo Banner a la lista
	 */
	@Override
	public void insertar(Banner banner) {
		lista.add(banner);		
	}

	/**
	 * Regresamos la lista de objetos Banner
	 */
	@Override
	public List<Banner> buscarTodos() {
		return lista;		
	}

	@Override
	public void eliminar(int idbanner) {
		// TODO Auto-generated method stub
		
	}

}
