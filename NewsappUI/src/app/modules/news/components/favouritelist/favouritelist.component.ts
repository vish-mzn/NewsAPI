import { Component, OnInit } from '@angular/core';
import { News } from '../../news';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-favouritelist',
  templateUrl: './favouritelist.component.html',
  styleUrls: ['./favouritelist.component.css']
})
export class FavouritelistComponent implements OnInit {
  newsList: Array<News>;
  useWatchlistApi = true;

  constructor(private newsService: NewsService, private matSnackBar: MatSnackBar) {
    this.newsList = [];
  }

  ngOnInit() {
    let message = 'Favourite List is Empty';
    
    this.newsService.getMyWatchList().subscribe(news => {
      this.newsList.push(...news);
    },
    error =>{
      this.matSnackBar.open(message, '', { duration: 1000 });
    });
  }

}
