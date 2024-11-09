import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { eproduct } from '../listar-productos/eproduct';
import { Observable } from 'rxjs';

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
    return this.http.post<eproduct>(this.urlEndPoint, eproduct, {headers: this.httpHeaders});
  }

  update(eproduct: eproduct) : Observable<eproduct>{
    console.log('Actualizando producto desde el servicio rest', eproduct);
    return this.http.put<eproduct>(`${this.urlEndPoint}?id=${eproduct.id}`, eproduct, {headers: this.httpHeaders});
  }

  delete(id: number) : Observable<eproduct>{
    console.log(`Eliminando producto con id ${id} desde el servicio REST`);
    return this.http.delete<eproduct>(`${this.urlEndPoint}?id=${id}`, { headers: this.httpHeaders });
  }
}
