import { FormActualizarComponent } from './actualizar-producto/form-actualizar.component';
import { Routes } from '@angular/router';
import { ListarProductosComponent } from './listar-productos/listar-productos.component';
import { FormComponent } from './crear-producto/form.component';

export const routes: Routes = [
    {path: '', redirectTo: '/productos/listar-productos', pathMatch: 'full'},
    {path: 'productos/listar-productos', component: ListarProductosComponent},
    {path: 'productos/crear-producto', component: FormComponent},
    {path: 'productos/editar-producto/:id', component: FormActualizarComponent}
];
