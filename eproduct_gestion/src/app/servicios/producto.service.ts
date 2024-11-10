import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { eproduct } from '../listar-productos/eproduct';
import { catchError, Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private urlEndPoint: string = 'http://localhost:5000/api/eproducts';
  constructor(private http: HttpClient) { }

  getProducts() : Observable<eproduct[]>{
    console.log('Listando productos desde el servicio rest');
    return this.http.get<eproduct[]>(this.urlEndPoint);
  }

  getProductById(id: number) : Observable<eproduct>{
    console.log(`Obteniendo producto con id ${id} desde el servicio REST`);
    return this.http.get<eproduct>(`${this.urlEndPoint}/${id}`);
  }

  create(eproduct: eproduct) : Observable<eproduct>{
    console.log('Creando producto desde el servicio rest');
    return this.http.post<eproduct>(this.urlEndPoint, eproduct, {headers: this.httpHeaders}).pipe(
      catchError(this.handleError)
    );
  }

  update(eproduct: eproduct) : Observable<eproduct>{
    console.log('Actualizando producto desde el servicio rest', eproduct);
    return this.http.put<eproduct>(`${this.urlEndPoint}?id=${eproduct.id}`, eproduct, {headers: this.httpHeaders}).pipe(
      catchError(this.handleError)
    );
  }

  delete(id: number) : Observable<eproduct>{
    console.log(`Eliminando producto con id ${id} desde el servicio REST`);
    return this.http.delete<eproduct>(`${this.urlEndPoint}?id=${id}`, { headers: this.httpHeaders }).pipe(
      catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 400 || error.status ===404 || error.status === 406) { 
      const codigoError = error.error.codigoError;
      const mensajeError = error.error.mensaje;
      const codigoHttp = error.error.codigoHttp;
      const url = error.error.url;
      const metodo = error.error.metodo

      const cadenaError = `(Código: ${codigoError}) - ${mensajeError} `;

      console.error(`Error ${codigoHttp} en ${metodo} ${url}: ${mensajeError} (Código: ${codigoError})`);
      
      Swal.fire({
        icon: 'error',
        title: '¡Error!',
        text: cadenaError, 
        confirmButtonText: 'Cerrar'
      });

      return throwError(() => new Error(mensajeError));
    } else {      
      return throwError(() => new Error('Ocurrió un error inesperado.'));
    }
  }
}
