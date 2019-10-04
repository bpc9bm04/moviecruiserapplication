package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path = "/api/v1/movieservice")
@CrossOrigin
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * This method is used to Save a new Movie in DB
	 * 
	 * @param movie
	 * @return
	 */
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MoviewAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * This method is used to update the existing Movie comments in DB
	 * @param id
	 * @param movie
	 * @return
	 */
	@PutMapping(path = "/movie/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable("movieId") final Integer movieId, @RequestBody final Movie movie,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			movie.setUserId(userId);
			movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@DeleteMapping(value = "/movie/{movieId}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("movieId") final int movieId,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			movieService.deleteMovieById(userId,movieId);
			responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	/**
	 * This method is used to fetch an existing Movie from DB based on id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{movieId}")
	public ResponseEntity<?> getMovieById(@PathVariable("movieId") final int movieId) {
		ResponseEntity<?> responseEntity;
		Movie movieFromDB=null;
		try {
			movieFromDB=movieService.getMovieById(movieId);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Movie>(movieFromDB, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * This method is used to fetch all my existing Movie from DB
	 * @return List<Movie>
	 */
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> getMyMovies(HttpServletRequest request, HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId), HttpStatus.OK);
	}
}
