import { Injectable } from '@angular/core';
import { Cidade } from '../../shared';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, Observable, of, throwError } from 'rxjs';

//const LS_CHAVE = "cidades";

@Injectable({
  providedIn: 'root'
})
export class CidadeService {
  BASE_URL = "http://localhost:8080/cidades";

  httpOptions = {
    observe: "response" as "response",
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  /*
  listarTodas(): Cidade[] {
    const cidades = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return cidades ? JSON.parse(cidades) : [];
  }
  */

  listarTodas(): Observable<Cidade[] | null> {
    return this.httpClient.get<Cidade[]>(
      this.BASE_URL,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Cidade[]>) => {
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
  inserir(cidade: Cidade): void {
    // Obtém a lista completa de cidades
    const cidades = this.listarTodas();
    // Seta um ID único
    // Usamos o Timestamp, quantidade de segundos desde 1970
    cidade.id = new Date().getTime();
    // Adiciona no final da lista
    cidades.push(cidade);
    // Armazena no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(cidades);
  }
  */

  inserir(cidade: Cidade): Observable<Cidade | null> {
    return this.httpClient.post<Cidade>(
      this.BASE_URL,
      JSON.stringify(cidade),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Cidade>) => {
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
  buscarPorId(id: number): Cidade | undefined {
    // Obtém a lista completa de cidades
    const cidades = this.listarTodas();
    // Efetua a busca
    // find() : retorna o primeiro elemento da lista que
    // satisfaz a condição, caso contrário, undefined
    return cidades.find(cidade => cidade.id === id);
  }
  */

  buscarPorId(id: number): Observable<Cidade | null> {
    return this.httpClient.get<Cidade>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Cidade>) => {
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
  atualizar(cidade: Cidade): void {
    // Obtem a lista completa de cidades
    const cidades = this.listarTodas();
    // Varre a lista de cidades
    // Quando encontra cidades com mesmo id, altera a lista
    cidades.forEach((obj, index, objs) => {
      if (cidade.id === obj.id) {
        objs[index] = cidade
      }
    });
    // Armazena a nova lista no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(cidades);
  }
  */

  atualizar(cidade: Cidade): Observable<Cidade | null> {
    return this.httpClient.put<Cidade>(
      this.BASE_URL + "/" + cidade.id,
      JSON.stringify(cidade),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Cidade>) => {
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
    // Obtem a lista completa de cidades
    let cidades = this.listarTodas();
    // filter() : retorna a mesma lista contendo todos
    // os registros que satisfazem a condição
    cidades = cidades.filter(cidade => cidade.id !== id);
    // Atualiza a lista de enderecos
    localStorage[LS_CHAVE] = JSON.stringify(cidades);
  }
  */

  remover(id: number): Observable<Cidade | null> {
    return this.httpClient.delete<Cidade>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Cidade>) => {
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
