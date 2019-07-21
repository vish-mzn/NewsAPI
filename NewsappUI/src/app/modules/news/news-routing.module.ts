import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from 'src/app/auth-guard.service';
import { SearchComponent } from './components/search/search.component';
import { FavouritelistComponent } from './components/favouritelist/favouritelist.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: 'news',
    children: [
      {
        path: '', redirectTo: '/news/dashboard', pathMatch: 'full', canActivate: [AuthGuardService]
      },
      {
        path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService]
      },
      {
        path: 'search', component: SearchComponent, canActivate: [AuthGuardService]
      },
      {
        path: 'favouritelist', component: FavouritelistComponent, canActivate: [AuthGuardService]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NewsRoutingModule { }
