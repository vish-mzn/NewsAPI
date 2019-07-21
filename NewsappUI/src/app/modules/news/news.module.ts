import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsRoutingModule } from './news-routing.module';
import { SearchComponent } from './components/search/search.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
import { FavouritelistComponent } from './components/favouritelist/favouritelist.component';
import { SharedModule } from '../shared/shared.module';
import { NewsService } from './news.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { TokenInterceptorService } from './token-interceptor.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';

@NgModule({
  declarations: [
    SearchComponent,
    ThumbnailComponent,
    ContainerComponent,
    FavouritelistComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    NewsRoutingModule,
    SharedModule,
    FormsModule
  ],
  exports: [
    NewsRoutingModule,
    SearchComponent,
    ThumbnailComponent,
    ContainerComponent,
    FavouritelistComponent,
    DashboardComponent
  ],
  providers: [
    NewsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]
})
export class NewsModule { }
