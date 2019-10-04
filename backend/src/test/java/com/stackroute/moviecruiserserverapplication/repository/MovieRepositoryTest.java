package com.stackroute.moviecruiserserverapplication.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	private transient MovieRepository movieRepo;

	@Autowired
	public void setMovieRepo(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	@Test
	public void testSaveMovie() {
		movieRepo.save(new Movie(1, 101, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019", "userId"));
		List<Movie> movies = movieRepo.findByUserId("userId");
		assertEquals(101, movies.get(0).getMovieId());
	}

	@Test
	public void testUpdateMovie() {
		movieRepo.save(new Movie(1, 101, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019", "userId"));
		List<Movie> movies = movieRepo.findByUserId("userId");
		Movie movie= movies.get(0);
		movie.setComments("Awesome Movie");
		movieRepo.save(movie);
		Movie updatedMovie = movieRepo.findByUserId("userId").get(0);
		assertEquals("Awesome Movie", updatedMovie.getComments());
	}

	@Test
	public void testDeleteMovie() {
		movieRepo.save(new Movie(1,101, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019", "userId"));
		Movie movie = movieRepo.findByUserId("userId").get(0);
		movieRepo.delete(movie);
		assertFalse(movieRepo.existsById(1));
	}

	/*@Test
	public void testGetMovieById() {
		movieRepo.save(new Movie(1, 101, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019", "userId"));
		assertTrue(movieRepo.existsById(1));
	}*/

	@Test
	public void testGetAllMovies() {
		movieRepo.save(new Movie(1,101, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019", "userId2"));
		movieRepo.save(new Movie(2, 202, "Sholey", "Super Hit Movie", "www.movieworld.com", "18-07-2019", "userId2"));
		List<Movie> movies = movieRepo.findByUserId("userId2");
		//The above findByUserId() method will pick Avengers,Sholey and all the saved records in DB for the same user
		assertTrue(movies.size()>=2);
	}
}
