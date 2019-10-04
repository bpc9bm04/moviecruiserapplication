import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Movie } from './movie';
import { Observable } from 'rxjs/Observable';
import { retry } from 'rxjs/operators';
@Injectable()
export class MovieService {

  tmdbEndPoint: string;
  imagePrifix: string;
  apiKey: string;
  watchlistEndPoint: string;
  search: string;

  constructor(private http: HttpClient) {
    this.tmdbEndPoint = 'https://api.themoviedb.org/3/movie';
    this.search = 'https://api.themoviedb.org/3/search/movie?';
    this.apiKey = 'api_key=b976fce09e09b8cc2833c04a6f0d7003';
    this.watchlistEndPoint = "http://localhost:8082/api/v1/movieservice/movie";
    //this.imagePrifix='https://image.tmdb.org/t/p/w500';
  }
  addHttpHeaders(){
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Authorization', 'Bearer '+localStorage.getItem('bearerToken'));
    return headers;
  }
  addMovieToWatchlist(movie: Movie) {
    let headers: HttpHeaders =this.addHttpHeaders();
    return this.http.post(this.watchlistEndPoint, movie,{headers});
  }
  deleteMovieFromWatchlist(movie: Movie) {
    let options = {
      headers: this.addHttpHeaders()
    };
    const url = `${this.watchlistEndPoint}/${movie.movieId}`;
    return this.http.delete(url,options);
  }
  updateMovieComments(movie: Movie) {
    let headers: HttpHeaders =this.addHttpHeaders();
    const url = `${this.watchlistEndPoint}/${movie.movieId}`;
    return this.http.put(url, movie, {headers});
  }
  getWatchListedMovies(): Observable<Array<Movie>> {
    let headers: HttpHeaders =this.addHttpHeaders();
    return this.http.get<Array<Movie>>(this.watchlistEndPoint, {headers});
  }
  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const moviesEndPoints = `${this.tmdbEndPoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(moviesEndPoints).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath).bind(this)
    );
  }
  searchMovies(searchKey: string): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const searchMoviesEndPoints = `${this.search}${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
      return this.http.get(searchMoviesEndPoints).pipe(
        retry(3),
        map(this.pickMovieResults),
        map(this.transformPosterPath).bind(this)
      );
    }
  }
  transformPosterPath(movies): Array<Movie> {
    this.imagePrifix = 'https://image.tmdb.org/t/p/w500';
    return movies.map((movie) => {
      movie.poster_path = `${this.imagePrifix}${movie.poster_path}`;
      movie.movieId=`${movie.id}`;
      return movie;
    });
  }
  pickMovieResults(response) {
    return response['results'];
  }
}
