package applinet.eglobal.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import applinet.eglobal.app.model.Noticia;


@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	// select * from noticias;
	List<Noticia> findBy();
	
	// Select * from noticias where estatus = ?
	List<Noticia> findByEstatus(String estatus);
	
	// Select * from noticias where fecha = ?
	List<Noticia> findByFecha(Date fecha);
	
	// Select * from noticias where estatus=? and fecha=?
	List<Noticia> findFirst3ByEstatusAndFecha(String estatus, Date fecha);
	
	// Select * from noticias where estatus=? or fecha=?
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
	
	// Select * from noticias where fecha between ? and ?
	List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);
	
	// Select * from noticias where id between ? and ?
	List<Noticia> findByIdBetween(int id1, int id2);
	
	// select * from Noticias where estatus = ? order by id desc limit 3
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);
}
