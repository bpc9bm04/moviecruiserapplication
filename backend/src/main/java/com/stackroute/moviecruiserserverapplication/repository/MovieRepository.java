/**
 * 
 */
package com.stackroute.moviecruiserserverapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

/**
 * @author ubuntu
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
List<Movie> findByUserId(String userId);
Movie findByUserIdAndMovieId(String userId, int movieId);
@Transactional
public void deleteByUserIdAndMovieId(String userId, int movieId);
}
