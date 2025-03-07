import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApiService } from './services/apiService';
import { ListadoComponent } from './components/listado/listado.component';
import { CarritoComponent } from './components/carrito/carrito.component';
import { MostrarPedidosComponent } from './components/mostrar-pedidos/mostrar-pedidos.component';
import { DetalleComponent } from './components/detalle/detalle.component';

const routes: Routes = [

  
  {path: 'carrito',component:CarritoComponent},
  {path: 'pedido',component:MostrarPedidosComponent},
  {path: 'detalle/:pepido',component:DetalleComponent},
  {
    path: '**',component:ListadoComponent
  },
  
    
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
