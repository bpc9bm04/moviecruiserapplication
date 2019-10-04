import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies: Array<Movie>;
  watchlistApi: boolean;
  constructor(private movieService: MovieService) {
    this.movies = [];
    this.watchlistApi=true;
  }

  ngOnInit() {
    this.movieService.getWatchListedMovies().subscribe((movie) => {
      this.movies.push(...movie);
    });
  }

}
