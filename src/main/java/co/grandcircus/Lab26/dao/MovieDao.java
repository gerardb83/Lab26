package co.grandcircus.Lab26.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import co.grandcircus.Lab26.entities.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {

	List<Movie> findByTitleContainsIgnoreCase(String name); 
	
	List<Movie> findByCategoryContainsIgnoreCase(String name);

}
