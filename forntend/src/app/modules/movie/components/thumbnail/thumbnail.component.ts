import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieDailogComponent } from '../../components/movie-dailog/movie-dailog.component';
@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input() movie: Movie;
  @Input() watchlistApi: boolean;
  @Output() addMovie = new EventEmitter();
  @Output() deleteMovie = new EventEmitter();

  constructor(private matSnackBar: MatSnackBar, public dialog: MatDialog) {

  }

  ngOnInit() {

  }
  addToWatchlist() {
    this.addMovie.emit(this.movie);
  }
  deleteFromWatchlist() {
    this.deleteMovie.emit(this.movie);
  }
  updateFromWatchlist(actionType) {
    console.log("Movie is getting updated.");
    let dailogRef = this.dialog.open(MovieDailogComponent, {
      width: '400px',
      data: { obj: this.movie, actionType: actionType }
    });
    console.log('Open dailog');
    dailogRef.afterClosed().subscribe((results)=>{
      console.log('The dailog was closed.');
    });
  }

}
