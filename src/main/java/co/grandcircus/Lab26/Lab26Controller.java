package co.grandcircus.Lab26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.grandcircus.Lab26.dao.MovieDao;
import co.grandcircus.Lab26.entities.Movie;

@RestController
public class Lab26Controller {

	@Autowired
	private MovieDao dao;

	@GetMapping("/movies")
	public List<Movie> movies(@RequestParam(value = "title", required = false) String title) {
		if (title == null || title.isEmpty()) {
			return dao.findAll();
		} else {
			return dao.findByTitleContainsIgnoreCase(title);
		}
	}

	@GetMapping("/movies/{category}")
	public List<Movie> getCategory(@PathVariable("category") String category) {
		return dao.findByCategoryContainsIgnoreCase(category);
	}

	@GetMapping("/movies/random")
	public Movie randomMovie(
			@RequestParam(value = "category", required = false) String cat) {
		if (cat == null || cat.isEmpty()) {
			List<Movie> movies = dao.findAll();
			double random = (Math.random() * (movies.size()));
			int index = (int) random;
			return movies.get(index);
		} else {
			List<Movie> movies = dao.findByCategoryContainsIgnoreCase(cat);
			double random = (Math.random() * (movies.size()));
			int index = (int) random;
			return movies.get(index);			
		}	
	}

	@GetMapping("/movies/random{number}")
	public List<Movie> getManyRandoms(@PathVariable("number") int number) {
		List<Movie> movies = dao.findAll();
		List<Movie> randMovies = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			double random = (Math.random() * (movies.size()));
			int index = (int) random;
			randMovies.add(movies.get(index));
		}
		return randMovies;
	}
	
	@GetMapping("/movies/all/categories")
	public List<String> getCategorySet() {
		List<Movie> movies = dao.findAll();
		List<String> catSet = new ArrayList<>();
		Set<String> cats = new HashSet<>();
		for (Movie each : movies) {
			cats.add(each.getCategory());
		}
		catSet.addAll(cats);
		return catSet;
	}
	
}