import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Examen_Javier_Alonso_Mansilla_UltimoParcial';
    seleccion = 0;
     constructor(private router: Router) {}
  
    realizarNavegacion() {
      this.router.navigate(['/listado']);
    } 
  }

