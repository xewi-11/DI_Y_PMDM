import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { pedido, Producto } from '../../model/Producto';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
carrito: any[] = [];
pedidos: any[] = [];
  constructor(private httpClient:HttpClient ) { }

  getAllProducts():Observable<any>{
    return this.httpClient.get('https://run.mocky.io/v3/8d5a3a0d-c789-4168-8df8-bebdf8ae0d68?authuser=0') 
  }
  agregarCarrito(producto: Producto) {
    this.carrito.push(producto);
  }
  getCarrito() {
    return this.carrito;
  }
  agregarPedido(pedido: pedido) {
    console.log(pedido);
    this.pedidos.push(pedido);
  }
  getPedidos() {
    return this.pedidos;
  }

}
