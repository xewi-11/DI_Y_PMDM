import { Component } from '@angular/core';
import { Producto } from '../../../model/Producto';
import { ApiService } from '../../services/apiService';

@Component({
  selector: 'app-listado',
  standalone: false,
  templateUrl: './listado.component.html',
  styleUrl: './listado.component.css'
})
export class ListadoComponent {
  productos: Producto[] = [];
  
  constructor(private apiService: ApiService) { 
    this.apiService.getAllProducts().subscribe((data) => {
      this.productos = data;
    });
  }
  agregarCarrito(producto: Producto) {
    this.apiService.agregarCarrito(producto);
  }
    
   }

