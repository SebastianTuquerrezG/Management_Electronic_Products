import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  public proyecto: any = {anio: '2024', nombreProyecto: 'Tienda Electronica'};
  public tecnologia: any = {leyenda:'WebApp desarrollado con ', tec1: 'Angular ', tec2: 'Spring-Boot' };
  public autor:  string = 'Desarrollado por Joan Sebastian Tuquerrez Gomez';
}
