
package com.stackroute.moviecruiserserverapplication.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

//@RunWith(SpringRunner.class)
//@WebMvcTest(MovieController.class)
public class MovieControllerTest {/*

	*//**
	 * Reference to MockMvc
	 *//*
	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient MovieService movieService;
	//private transient Movie movie;
	static List<Movie> movies;

	@Before
	public void setUp() {
		movies = new ArrayList<>();
		movie = new Movie(1,101, "Border" , "Nice movie", "www.movies.com", "01/01/1997", "userId");
		movies.add(movie);
		movie = new Movie(2,102, "LoC Kargil" ,"Based on Kargil", "www.movies.com", "01/01/2000", "userId");
		movies.add(movie);
	}

	*//**
	 * to test save movie
	 * @throws Exception
	 *//*
	@Test
	public void testSaveNewMovie_success() throws Exception {
		Movie movie = new Movie(1,101, "Border" , "Nice movie", "www.movies.com", "01/01/1997", "106");
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDYiLCJpYXQiOjE1NjgyODAwNzh9.qwcxa1CDAHEzFbI7QYrjeeVm_h_3Fc4QdmRY2mcCBbg";
		when(movieService.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer " +  token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	*//**
	 * to test update movie
	 * @throws Exception
	 *//*
	@Test
	public void testUpdateMovie_success() throws Exception {
		Movie movie = new Movie(1,101, "Border" , "Nice movie", "www.movies.com", "01/01/1997", "106");
		movie.setComments("very good movie");
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDYiLCJpYXQiOjE1NjgyODAwNzh9.qwcxa1CDAHEzFbI7QYrjeeVm_h_3Fc4QdmRY2mcCBbg";
		when(movieService.updateMovie(movie)).thenReturn(movie);
		mvc.perform(put("/api/v1/movieservice/{id}",1).header("authorization", "Bearer " +  token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	*//**
	 * to test delete movie
	 * @throws Exception
	 *//*
	@Test
	public void testDeleteMovieById_success() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDYiLCJpYXQiOjE1NjgyODAwNzh9.qwcxa1CDAHEzFbI7QYrjeeVm_h_3Fc4QdmRY2mcCBbg";
		when(movieService.deleteMovieById("106", 101)).thenReturn(true);
		mvc.perform(delete("/api/v1/movieservice/{id}", 101).header("authorization", "Bearer " +  token)).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById("106", 101);
		verifyNoMoreInteractions(movieService);
	}

	*//**
	 * to test find movie by id
	 * @throws Exception
	 *//*
	@Test
	public void testGetMovieById_success() throws Exception {
		when(movieService.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/v1/movieservice/{id}", 1)).andExpect(status().isOk());
		verify(movieService, times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	*//**
	 * to test list all movies
	 * @throws Exception
	 *//*
	@Test
	public void testGetAllMovies_success() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDYiLCJpYXQiOjE1NjgyODAwNzh9.qwcxa1CDAHEzFbI7QYrjeeVm_h_3Fc4QdmRY2mcCBbg";
		when(movieService.getMyMovies("106")).thenReturn(null);
		mvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer " +  token)).andExpect(status().isOk());
		verify(movieService, times(1)).getMyMovies("106");
		verifyNoMoreInteractions(movieService);
	}

	*//**
	 * Parse string format object to json format
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 *//*
	private static String jsonToString(final Object object) throws JsonProcessingException {

		final ObjectMapper objMapper = new ObjectMapper();
		String stringValue;
		try {
			final String jsonValue = objMapper.writeValueAsString(object);
			stringValue = jsonValue;
		} catch (JsonProcessingException e) {
			stringValue = "Json processing exception";
		}
		return stringValue;
	}
*/}
