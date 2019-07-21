import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { News } from '../../news';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  news: News;
  @Input()
  useWatchlistApi: boolean;
  @Output()
  addNews = new EventEmitter();
  @Output()
  deleteNews = new EventEmitter();

  articleUrl: string;

  constructor() { }

  ngOnInit() {
    console.log(this.news);
  }

  addToWatchlist() {
    this.addNews.emit(this.news);
  }

  deleteFromWatchlist() {
    this.deleteNews.emit(this.news);
  }

}
