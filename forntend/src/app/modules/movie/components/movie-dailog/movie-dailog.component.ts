import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from '../../movie.service';
@Component({
  selector: 'movie-movie-dailog',
  templateUrl: './movie-dailog.component.html',
  styleUrls: ['./movie-dailog.component.css']
})
export class MovieDailogComponent implements OnInit {
  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackBar: MatSnackBar, public dailogRef: MatDialogRef<MovieDailogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService) {
    this.movie = data.obj;
    this.comments = data.obj.comments;
    this.actionType = data.actionType;
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dailogRef.close();
  }

  updateComments() {
    console.log("Comments", this.comments);
    this.movie.comments = this.comments;
    this.dailogRef.close();
    this.movieService.updateMovieComments(this.movie).subscribe((movie) => {
      this.snackBar.open('Movie updated to watchlist successfully', '', {
        duration: 2000
      });
    });
  }
}
