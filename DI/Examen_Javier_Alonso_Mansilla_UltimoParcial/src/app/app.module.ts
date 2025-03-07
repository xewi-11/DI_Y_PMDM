import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListadoComponent } from './components/listado/listado.component';
import { ApiService } from './services/apiService';
import { HttpClientModule } from '@angular/common/http';
import { CarritoComponent } from './components/carrito/carrito.component';
import { ImagenesPipe } from './ImagenesPipe';
import { MostrarPedidosComponent } from './components/mostrar-pedidos/mostrar-pedidos.component';
import { DetalleComponent } from './components/detalle/detalle.component';

@NgModule({
  declarations: [
    AppComponent,
    ListadoComponent,
    CarritoComponent,
    ImagenesPipe,
    MostrarPedidosComponent,
    DetalleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(withEventReplay()),ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
