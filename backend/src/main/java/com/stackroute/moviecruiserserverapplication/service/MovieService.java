package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;

public interface MovieService {
	
/**Method declaration for saving the movie
 * @param movie
 * @return boolean
 * @throws MoviewAlreadyExistException
 */
boolean saveMovie(Movie movie) throws MoviewAlreadyExistException;
/**Method declaration for updating the movie(updating the comments)
 * @param movie
 * @return movie
 * @throws MovieNotFoundException
 */
Movie updateMovie(Movie movie) throws MovieNotFoundException;

/**Method declaration for deleting the movie
 * @param userId
 * @param movieId
 * @return boolean
 * @throws MovieNotFoundException
 */
boolean deleteMovieById(String userId, int movieId) throws MovieNotFoundException;
/**Method declaration for getting the movie
 * @param id
 * @return boolean
 * @throws MovieNotFoundException
 */
Movie getMovieById(int id) throws MovieNotFoundException;

/**Method declaration for getting all the movies from DB based on User Id
 * @param userId
 * @return List<Movie>
 */
List<Movie> getMyMovies(String userId);
}
