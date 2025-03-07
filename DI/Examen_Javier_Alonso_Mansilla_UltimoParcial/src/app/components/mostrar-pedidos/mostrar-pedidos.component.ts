import { Component } from '@angular/core';
import { pedido } from '../../../model/Producto';
import { ApiService } from '../../services/apiService';
import {  Router } from '@angular/router';


@Component({
  selector: 'app-mostrar-pedidos',
  standalone: false,
  templateUrl: './mostrar-pedidos.component.html',
  styleUrl: './mostrar-pedidos.component.css'
})
export class MostrarPedidosComponent {
pedidos: pedido[] = [];

constructor(private service: ApiService, private route: Router) {
  this.pedidos = this.service.getPedidos();
}
verDetalle(pedido: pedido) {
  this.route.navigate(['/detalle'], { queryParams: { pedido: pedido} });
}}
