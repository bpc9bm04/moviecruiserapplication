import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from '../../movie.service';
@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})

export class ContainerComponent implements OnInit {
  @Input()
  movies: Array<Movie>;
  @Input()
  watchlistApi: boolean;
  constructor(private movieService: MovieService, private matSnackBar: MatSnackBar) {

  }

  ngOnInit() {

  }

  addToWatchlist(movie) {
    let message = `${movie.title} added to Watchlist.`;
    this.movieService.addMovieToWatchlist(movie).subscribe((movie) => {
      this.matSnackBar.open(message, '', {
        duration: 2000
      });
    });
  }
  deleteFromWatchlist(movie) {
    let message = `${movie.title} deleted from Watchlist.`;
    for(var i=0; i< this.movies.length;i++){
      if(movie.movieId===this.movies[i].movieId){
        this.movies.splice(i,1);
      }
    }
    this.movieService.deleteMovieFromWatchlist(movie).subscribe((movie) => {
      this.matSnackBar.open(message, '', {
        duration: 2000
      });
    });
  }
}
