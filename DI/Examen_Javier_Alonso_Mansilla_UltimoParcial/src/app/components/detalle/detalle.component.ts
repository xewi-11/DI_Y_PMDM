import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { pedido } from '../../../model/Producto';

@Component({
  selector: 'app-detalle',
  standalone: false,
  templateUrl: './detalle.component.html',
  styleUrl: './detalle.component.css'
})
export class DetalleComponent implements OnInit {
pedido!:pedido;
  constructor(private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {
    this.pedido=this.rutaActiva.snapshot.data["pedido"];
  }

  
}
