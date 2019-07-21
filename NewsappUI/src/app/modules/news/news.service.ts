import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { News } from './news';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  apiKey: string;
  newsEndpoint: string;
  springEndpoint: string;

  constructor(private httpService: HttpClient) {
    this.apiKey = '&apiKey=94ce95a61c00434194a7faa9f10f183a';
    this.newsEndpoint = 'https://newsapi.org/v2/everything?q=';
    this.springEndpoint = 'http://localhost:8085/api/newsservice/news';
  }

  searchNews(query: string) {
      //window.alert('inside service');
      const url = `${this.newsEndpoint}${query}${this.apiKey}`;
      console.log(url);
      //window.alert(url);
      return this.httpService.get(url);
      // .pipe(
      //   retry(10),
      //   map(this.pickNewsResults)
      // );
  }

  // pickNewsResults(response) {
  //   //window.alert(response['articles']);
  //   return response['articles'];
  // }

  getTopNews() {
    const url = `${this.newsEndpoint}bbc${this.apiKey}`;
    console.log(url);
    return this.httpService.get(url);
  }

  addNewsToWatchList(news) {
    return this.httpService.post(this.springEndpoint, news);
  }

  deleteFromMyWatchlist(news) {
    const url = `${this.springEndpoint}/${news.id}`;
    return this.httpService.delete(url, {responseType: 'text'});
  }

  getMyWatchList(): Observable<Array<News>> {
    return this.httpService.get<Array<News>>(this.springEndpoint);
  }


}
