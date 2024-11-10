import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { eproduct } from '../listar-productos/eproduct';
import { ProductoService } from '../servicios/producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-actualizar',
  standalone: true,
  imports: [CommonModule, FormsModule, SweetAlert2Module, HttpClientModule],
  templateUrl: './form-actualizar.component.html',
  styleUrl: './form-actualizar.component.css'
})
export class FormActualizarComponent {
  public producto: eproduct = new eproduct();
  public titulo: string = 'Actualizar Producto';

  constructor(private productoService: ProductoService, private router: Router, private route: ActivatedRoute) {}

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

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
        this.productoService.getProductById(+id).subscribe(
            response => {
                console.log('Obteniendo Producto', response);
                this.producto = response;
            },
            error => {
                console.log('Error al buscar producto', error);
            }
        );
    }
  }

  public actualizarProducto(): void {
    if (!this.producto.name || !this.producto.marca || !this.producto.categoria || this.producto.precioBase == null) {
        Swal.fire('Error', 'Por favor, complete todos los campos obligatorios.', 'error');
        return;
    }

    console.log('Actualizando producto', this.producto);
    this.productoService.update(this.producto).subscribe(
        response => {
            console.log('Producto actualizado', response);
            console.log(this.producto);
            this.router.navigate(['productos/listar-productos']);
            Swal.fire('Producto actualizado', `Producto ${response.name} actualizado con éxito`, 'success');
        },
        error => {
            console.error('Error al actualizar producto', error);
        }
    );
  }
}
