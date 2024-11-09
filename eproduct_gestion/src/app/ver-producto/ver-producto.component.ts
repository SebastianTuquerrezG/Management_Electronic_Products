import { Component, Input } from '@angular/core';
import { eproduct } from '../listar-productos/eproduct';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-ver-producto',
  standalone: true,
  imports: [DatePipe, CommonModule],
  templateUrl: './ver-producto.component.html',
  styleUrls: ['./ver-producto.component.css']
})
export class VerProductoComponent {
  @Input() producto: eproduct | null = null;
  visible = false;

  open() {
    this.visible = true;
  }

  close() {
    this.visible = false;
  }
}
