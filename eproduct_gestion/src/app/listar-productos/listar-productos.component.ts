import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { eproduct } from './eproduct';
import { ProductoService } from '../servicios/producto.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { VerProductoComponent } from "../ver-producto/ver-producto.component";

@Component({
  selector: 'app-listar-productos',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, SweetAlert2Module, DatePipe, VerProductoComponent],
  templateUrl: './listar-productos.component.html',
  styleUrls: ['./listar-productos.component.css']
})
export class ListarProductosComponent {
  productos: eproduct[] = [];
  constructor(private objProductoService: ProductoService, private router: Router) { }

  ngOnInit(): void {
    this.objProductoService.getProducts().subscribe(
      productos => {
        console.log('Listando productos desde el componente');
        this.productos = productos
      }
    );
  }

  delete(id : number): void {
    Swal.fire(
      {
        title: '¿Está seguro?',
        text: 'No podrá recuperar el producto',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminarlo',
        cancelButtonText: 'Cancelar'
      }
    ).then((result) => {
      if (result.isConfirmed) {
        this.objProductoService.delete(id).subscribe(
          (response) => {
            this.productos = this.productos.filter(producto => producto.id !== id)
            Swal.fire(
              'Producto eliminado',
              `Producto ${response.name} eliminado con éxito`,
              'success'
            );
          }
        );
      } else {
        Swal.fire(
          'Cancelado',
          'Operación cancelada por el usuario',
          'error'
        );
        console.log('Operación cancelada por el usuario');
      }
    });
  }

  update(id: number): void {
    this.router.navigate(['/productos/editar-producto', id]);
  }

  view(producto: eproduct, modal: VerProductoComponent): void {
    modal.producto = producto;
    modal.open();
  }
}
