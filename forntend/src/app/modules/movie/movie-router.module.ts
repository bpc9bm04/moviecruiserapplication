import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { TmdbContainerComponent} from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { CanActivateRouteGuard } from '../../can-activate-route.guard';
const movieRoutes: Routes = [
    { path: 'movies',
        children:[
            { path: '', redirectTo: '/movies/popular', pathMatch: 'full' , canActivate: [CanActivateRouteGuard]},
            { path: 'popular', component: TmdbContainerComponent , data:{movieType:'popular'}, canActivate: [CanActivateRouteGuard]},
            { path: 'top_rated', component: TmdbContainerComponent , data:{movieType:'top_rated'}, canActivate: [CanActivateRouteGuard]},
            { path: 'watchlist', component: WatchlistComponent, canActivate: [CanActivateRouteGuard]},
            { path: 'search', component: SearchComponent, canActivate: [CanActivateRouteGuard]},
        ]
}
];

@NgModule({
  imports: [
    RouterModule.forChild( movieRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MovieRouterModule {}