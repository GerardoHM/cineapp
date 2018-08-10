package applinet.eglobal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applinet.eglobal.app.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer>  {

}
