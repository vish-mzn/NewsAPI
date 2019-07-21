import { Component, OnInit } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  newsList: Array<News>;
  useWatchlistApi = false;
  
  constructor(private newsService: NewsService, private matSnackBar: MatSnackBar) {
    this.newsList = [];
  }

  ngOnInit() {
    this.newsService.getTopNews().subscribe(object =>{
      this.newsList = object['articles'];
    },
    error=>{
      window.alert('error occured' + error);
    });
  }

}
