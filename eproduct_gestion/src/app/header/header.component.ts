import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  nombres: String = 'E-Commerce de Productos Electronicos';
  servicio1: String = 'Celulares';
  servicio2: String = 'Computadores';
  servicio3: String = 'Televisores';
  servicio4: String = 'Electrodomesticos';
  servicio5: String = 'Descuentos';
  descripcion: String = 'Somos una empresa dedicada a la venta de productos electronicos de ultima generacion';
}
