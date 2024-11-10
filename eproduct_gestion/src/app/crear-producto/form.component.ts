import { ProductoService } from '../servicios/producto.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { eproduct } from '../listar-productos/eproduct';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Console } from 'console';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, FormsModule, SweetAlert2Module, HttpClientModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  public producto: eproduct = new eproduct();
  public titulo: string = 'Crear Producto';

  constructor(private productoService: ProductoService, private router: Router) {}

  ngOnInit(): void {
  }

  public marcas: string[] = [
    'SONY', 'SAMSUNG', 'LG', 'APPLE', 'HUAWEI', 'XIAOMI', 
    'MOTOROLA', 'NOKIA', 'ALCATEL', 'ZTE', 'BLU', 
    'LENOVO', 'HP', 'DELL', 'ACER', 'ASUS', 'TOSHIBA'
  ];

  public categorias: string[] = [
    'TELEFONIA', 'COMPUTO', 'LINEA_BLANCA', 'AUDIO_VIDEO', 
    'GAMING', 'ACCESORIOS', 'IMPRESION', 'SOFTWARE', 
    'REDES', 'SEGURIDAD', 'ENERGIA', 'ALMACENAMIENTO', 
    'COMPONENTES', 'SERVICIOS'
  ];
  
  public crearProducto(): void {
    if (!this.producto.name || !this.producto.marca || !this.producto.categoria || this.producto.precioBase == null) {
        Swal.fire('Error', 'Por favor, complete todos los campos obligatorios.', 'error');
        return;
    }

    console.log('Creando producto');
    this.productoService.create(this.producto).subscribe(
      {
        next: (response) => {
            console.log('Producto creado', response);
            console.log(this.producto);
            this.router.navigate(['productos/listar-productos']);
            Swal.fire('Nuevo producto', `Producto ${response.name} creado con éxito`, 'success');
        },
        error: (error) => {
            console.error('Error al crear el producto', error.message);
        }
      }
    );
  }
}
