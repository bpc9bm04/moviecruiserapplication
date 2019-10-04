package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
@Service
public class MovieServiceImpl implements MovieService {
	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MoviewAlreadyExistException {
		Movie movieFromDB=movieRepository.findByUserIdAndMovieId(movie.getUserId(), movie.getMovieId());
		if(Objects.nonNull(movieFromDB)) {
			throw new MoviewAlreadyExistException("Movie already present in DB, can't save it!");
		}
		movieRepository.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		Movie movieFromDB=movieRepository.findByUserIdAndMovieId(movie.getUserId(), movie.getMovieId());
		if(Objects.isNull(movieFromDB)) {
			throw new MovieNotFoundException("Movie does not exist in DB, can't update it's comments!");
		}
		movieFromDB.setComments(movie.getComments());
		return movieRepository.save(movieFromDB);
	}

	@Override
	public boolean deleteMovieById(String userId, int movieId) throws MovieNotFoundException {
		Movie movieFromDB=movieRepository.findByUserIdAndMovieId(userId, movieId);
		if(Objects.isNull(movieFromDB)) {
			throw new MovieNotFoundException("Movie does not exist in DB, can't delete it!");
		}
		movieRepository.deleteByUserIdAndMovieId(userId, movieId);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> movieIsPresent=movieRepository.findById(id);
		if(!movieIsPresent.isPresent()) {
			throw new MovieNotFoundException("Movie does not exist in DB!");
		}
		return movieIsPresent.get();
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepository.findByUserId(userId);
	}

}
