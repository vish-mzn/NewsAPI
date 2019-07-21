import { Component, OnInit } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  newsList: Array<News>;

  constructor(private newsService: NewsService, private snackbar: MatSnackBar) {
    this.newsList = [];
  }

  ngOnInit() {
  }

  onEnter(searchKey) {

    if(searchKey==null ||searchKey.length==0) {
      this.snackbar.open('Search a valid Data', '', {
        duration: 3000,
        verticalPosition:'top'
      });
    }

    console.log('Search Key', searchKey);
    this.newsService.searchNews(searchKey).subscribe(object =>{
      this.newsList = object['articles'];
    },
    error=>{
      window.alert('error occured' + error);
    });
  }

}
