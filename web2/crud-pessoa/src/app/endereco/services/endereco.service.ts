import { Injectable } from '@angular/core';
import { Endereco } from '../../shared';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, Observable, of, throwError } from 'rxjs';

const LS_CHAVE = "enderecos";

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {
  BASE_URL = "http://localhost:8080/enderecos";

  httpOptions = {
    observe: "response" as "response",
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  /*
  listarTodos(): Endereco[] {
    const enderecos = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return enderecos ? JSON.parse(enderecos) : [];
  }
  */

  listarTodos(): Observable<Endereco[] | null> {
    return this.httpClient.get<Endereco[]>(
      this.BASE_URL,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Endereco[]>) => {
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
  inserir(endereco: Endereco): void {
    // Obtém a lista completa de enderecos
    const enderecos = this.listarTodos();
    // Seta um ID único
    // Usamos o Timestamp, quantidade de segundos desde 1970
    endereco.id = new Date().getTime();
    // Adiciona no final da lista
    enderecos.push(endereco);
    // Armazena no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(enderecos);
  }
  */

  inserir(endereco: Endereco): Observable<Endereco | null> {
    return this.httpClient.post<Endereco>(
      this.BASE_URL,
      JSON.stringify(endereco),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Endereco>) => {
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
  buscarPorId(id: number): Endereco | undefined {
    // Obtém a lista completa de enderecos
    const enderecos = this.listarTodos();
    // Efetua a busca
    // find() : retorna o primeiro elemento da lista que
    // satisfaz a condição, caso contrário, undefined
    return enderecos.find(endereco => endereco.id === id);
  }
  */

  buscarPorId(id: number): Observable<Endereco | null> {
    return this.httpClient.get<Endereco>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Endereco>) => {
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
  atualizar(endereco: Endereco): void {
    // Obtem a lista completa de enderecos
    const enderecos = this.listarTodos();
    // Varre a lista de enderecos
    // Quando encontra enderecos com mesmo id, altera a lista
    enderecos.forEach((obj, index, objs) => {
      if (endereco.id === obj.id) {
        objs[index] = endereco
      }
    });
    // Armazena a nova lista no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(enderecos);
  }
  */

  atualizar(endereco: Endereco): Observable<Endereco | null> {
    return this.httpClient.put<Endereco>(
      this.BASE_URL + "/" + endereco.id,
      JSON.stringify(endereco),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Endereco>) => {
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
    // Obtem a lista completa de enderecos
    let enderecos = this.listarTodos();
    // filter() : retorna a mesma lista contendo todos
    // os registros que satisfazem a condição
    enderecos = enderecos.filter(endereco => endereco.id !== id);
    // Atualiza a lista de enderecos
    localStorage[LS_CHAVE] = JSON.stringify(enderecos);
  }
  */

  remover(id: number): Observable<Endereco | null> {
    return this.httpClient.delete<Endereco>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Endereco>) => {
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
