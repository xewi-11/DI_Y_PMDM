import { Component } from '@angular/core';
import { Producto } from '../../../model/Producto';
import { ApiService } from '../../services/apiService';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-carrito',
  standalone: false,
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css'
})
export class CarritoComponent {
carrito:Producto [] = [];
nombrePedido:string = '';
id=0;
precio:number = 0;
confirmacion!:boolean ;
constructor(private service:ApiService) {
  this.carrito = this.service.getCarrito();
}
hacerPedido(){
    Swal.fire({
      icon: 'warning',
      title: 'Confirmacion',
      text: 'Quiere confirmar este pedido',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.confirmacion = true;
        if(this.confirmacion){
          for (let i = 0; i < this.carrito.length; i++) {
            this.precio += this.carrito[i].precio;
          }
          this.service.agregarPedido({id:this.id,nombre:this.nombrePedido, productos:this.carrito,precio:this.precio});
          this.carrito = [];
          this.id++;
          this.nombrePedido = '';
        }
      }
    return;
    });
    
  
}
}
