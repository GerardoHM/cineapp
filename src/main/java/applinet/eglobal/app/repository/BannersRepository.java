package applinet.eglobal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applinet.eglobal.app.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {
	List<Banner> findByEstatus(String estatus);
	// select * from Banners where estatus = ? order by id desc 
	public List<Banner> findByEstatusOrderByIdDesc(String estatus);
}
