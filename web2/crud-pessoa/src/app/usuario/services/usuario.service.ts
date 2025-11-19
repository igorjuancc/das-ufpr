import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { Usuario } from '../../shared';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  BASE_URL = "http://localhost:8080/usuarios";

  httpOptions = {
    observe: "response" as "response",
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  listarTodos(): Observable<Usuario[] | null> {
    return this.httpClient.get<Usuario[]>(
      this.BASE_URL,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Usuario[]>) => {
          if (resp.status != 200) {
            return [];
          } else {
            return resp.body;
          }
        }),
        catchError((e, c) => {
          if (e.status == 404) {
            return of([]);
          } else {
            return throwError(() => e);
          }
        })
      );
  }

  buscarPorId(id: number): Observable<Usuario | null> {
    return this.httpClient.get<Usuario>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Usuario>) => {
          if (resp.status != 200) {
            return null;
          } else {
            return resp.body;
          }
        }),
        catchError((e, c) => {
          if (e.status == 404) {
            return of(null);
          } else {
            return throwError(() => e);
          }
        })
      );
  }

  inserir(usuario: Usuario): Observable<Usuario | null> {
    return this.httpClient.post<Usuario>(
      this.BASE_URL,
      JSON.stringify(usuario),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Usuario>) => {
          if (resp.status != 201) {
            return null;
          } else {
            return resp.body;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }

  remover(id: number): Observable<Usuario | null> {
    return this.httpClient.delete<Usuario>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Usuario>) => {
          if (resp.status != 200) {
            return null;
          } else {
            return resp.body;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }

  alterar(usuario: Usuario): Observable<Usuario | null> {
    return this.httpClient.put<Usuario>(
      this.BASE_URL + "/" + usuario.id,
      JSON.stringify(usuario),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Usuario>) => {
          if (resp.status != 200) {
            return null;
          } else {
            return resp.body;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }
}
