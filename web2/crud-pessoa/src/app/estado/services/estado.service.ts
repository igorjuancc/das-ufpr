import { Injectable } from '@angular/core';
import { Estado } from '../../shared';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, Observable, of, throwError } from 'rxjs';

const LS_CHAVE = "estados";

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  BASE_URL = "http://localhost:8080/estados";

  httpOptions = {
    observe: "response" as "response",
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  /*
  listarTodos(): Estado[] {
    const estados = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return estados ? JSON.parse(estados) : [];
  }
  */

  listarTodos(): Observable<Estado[] | null> {
    return this.httpClient.get<Estado[]>(
      this.BASE_URL,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Estado[]>) => {
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

  /*
  inserir(estado: Estado): void {
    // Obtém a lista completa de estados
    const estados = this.listarTodos();
    // Seta um ID único
    // Usamos o Timestamp, quantidade de segundos desde 1970
    estado.id = new Date().getTime();
    // Adiciona no final da lista
    estados.push(estado);
    // Armazena no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }
  */

  inserir(estado: Estado): Observable<Estado | null> {
    return this.httpClient.post<Estado>(
      this.BASE_URL,
      JSON.stringify(estado),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Estado>) => {
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

  /*
  buscarPorId(id: number): Estado | undefined {
    // Obtém a lista completa de estados
    const estados = this.listarTodos();
    // Efetua a busca
    // find() : retorna o primeiro elemento da lista que
    // satisfaz a condição, caso contrário, undefined
    return estados.find(estado => estado.id === id);
  }
  */

  buscarPorId(id: number): Observable<Estado | null> {
    return this.httpClient.get<Estado>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Estado>) => {
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

  /*
  atualizar(estado: Estado): void {
    // Obtem a lista completa de estados
    const estados = this.listarTodos();
    // Varre a lista de estados
    // Quando encontra estados com mesmo id, altera a lista
    estados.forEach((obj, index, objs) => {
      if (estado.id === obj.id) {
        objs[index] = estado
      }
    });
    // Armazena a nova lista no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }
  */

  atualizar(estado: Estado): Observable<Estado | null> {
    return this.httpClient.put<Estado>(
      this.BASE_URL + "/" + estado.id,
      JSON.stringify(estado),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Estado>) => {
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

  /*
  remover(id: number): void {
    // Obtem a lista completa de estados
    let estados = this.listarTodos();
    // filter() : retorna a mesma lista contendo todos
    // os registros que satisfazem a condição
    estados = estados.filter(estado => estado.id !== id);
    // Atualiza a lista de estados
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }
  */

  remover(id: number): Observable<Estado | null> {
    return this.httpClient.delete<Estado>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Estado>) => {
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
