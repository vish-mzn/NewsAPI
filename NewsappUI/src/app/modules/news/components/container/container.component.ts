import { Component, OnInit, Input } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  newsList: Array<News>;

  @Input()
  useWatchlistApi: boolean;

  constructor(private newsService: NewsService, private matSnackBar: MatSnackBar) {
  
   }

  ngOnInit() {
  }

  addNewsToWatchlist(news) {
    console.log("News Title", news.title);
    let message = `Article added to watchlist`;

    this.newsService.addNewsToWatchList(news).subscribe(news =>{
      this.matSnackBar.open(message, '', { duration: 1000 });
    },
    error =>{
      this.matSnackBar.open(error['error'], '', { duration: 1000 });
    });
  }

  deleteFromWatchlist(news) {
    let message = `Article deleted from your watchlist`;

    // for(var i=0; i<this.movies.length; i++) {
    //   if(this.movies[i].title === movie.title) {
    //     this.movies.splice(i,1);
    //   }
    // }

    this.newsService.deleteFromMyWatchlist(news).subscribe(result =>{
      this.matSnackBar.open(message, '', { duration: 1000 });

      const index = this.newsList.indexOf(news);
      this.newsList.splice(index, 1);

      if(this.newsList.length == 0) {
        let message = 'Favourite List has become Empty';
        this.matSnackBar.open(message, '', { duration: 2500 });
      }
    });
  }

}
