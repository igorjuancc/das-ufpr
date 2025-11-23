import { Injectable } from '@angular/core';
import { Pessoa } from '../../shared';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, Observable, of, throwError } from 'rxjs';

const LS_CHAVE = "pessoas";

@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  BASE_URL = "http://localhost:8080/pessoas";

  httpOptions = {
    observe: "response" as "response",
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  /*
  listarTodos(): Pessoa[] {
    const pessoas = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return pessoas ? JSON.parse(pessoas) : [];
  }
  */

  listarTodos(): Observable<Pessoa[] | null> {
    return this.httpClient.get<Pessoa[]>(
      this.BASE_URL,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Pessoa[]>) => {
          if (resp.status != 200) {
            return [];
          } else {
            let lista: Pessoa[] = [];
            if (resp.body && resp.body.length > 0) {
              let pes: Pessoa = new Pessoa();
              // acertar as datas
              resp.body.forEach(p => {
                pes = new Pessoa(p.id, p.nome, p.idade, p.dataDeNascimento, p.motorista);
                pes.dataDoRest();
                lista.push(pes);
              });
            }
            return lista;
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
  inserir(pessoa: Pessoa): void {
    // Obtém a lista completa de pessoas
    const pessoas = this.listarTodos();
    // Seta um ID único
    // Usamos o Timestamp, quantidade de segundos desde 1970
    pessoa.id = new Date().getTime();
    // Adiciona no final da lista
    pessoas.push(pessoa);
    // Armazena no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }*/

  inserir(pessoa: Pessoa): Observable<Pessoa | null> {
    console.log(pessoa);
    return this.httpClient.post<Pessoa>(
      this.BASE_URL,
      JSON.stringify(pessoa),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Pessoa>) => {
          if (resp.status != 201) {
            return null;
          } else {
            // Acertar a data
            let pes: Pessoa = new Pessoa(
              resp.body?.id,
              resp.body?.nome,
              resp.body?.idade,
              resp.body?.dataDeNascimento,
              resp.body?.motorista);

            pes.dataParaRest();
            return pes;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }

  /*
  buscarPorId(id: number): Pessoa | undefined {
    // Obtém a lista completa de pessoas
    const pessoas = this.listarTodos();
    // Efetua a busca
    // find() : retorna o primeiro elemento da lista que
    // satisfaz a condição, caso contrário, undefined
    return pessoas.find(pessoa => pessoa.id === id);
  }
  */

  buscarPorId(id: number): Observable<Pessoa | null> {
    return this.httpClient.get<Pessoa>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Pessoa>) => {
          if (resp.status != 200) {
            return null;
          } else {
            // Acertar a data
            let pes: Pessoa = new Pessoa(
              resp.body?.id,
              resp.body?.nome,
              resp.body?.idade,
              resp.body?.dataDeNascimento,
              resp.body?.motorista);

            pes.dataDoRest();
            return pes;
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
  atualizar(pessoa: Pessoa): void {
    // Obtem a lista completa de pessoas
    const pessoas = this.listarTodos();
    // Varre a lista de pessoas
    // Quando encontra pessoa com mesmo id, altera a lista
    pessoas.forEach((obj, index, objs) => {
      if (pessoa.id === obj.id) {
        objs[index] = pessoa
      }
    });
    // Armazena a nova lista no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }
  */

  atualizar(pessoa: Pessoa): Observable<Pessoa | null> {
    return this.httpClient.put<Pessoa>(
      this.BASE_URL + "/" + pessoa.id,
      JSON.stringify(pessoa),
      this.httpOptions).pipe(
        map((resp: HttpResponse<Pessoa>) => {
          if (resp.status != 200) {
            return null;
          } else {
            // Acertar a data
            let pes: Pessoa = new Pessoa(
              resp.body?.id,
              resp.body?.nome,
              resp.body?.idade,
              resp.body?.dataDeNascimento,
              resp.body?.motorista);

              pes.dataParaRest();
              return pes;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }

  /*
  remover(id: number): void {
    // Obtem a lista completa de pessoas
    let pessoas = this.listarTodos();
    // filter() : retorna a mesma lista contendo todos
    // os registros que satisfazem a condição
    pessoas = pessoas.filter(pessoa => pessoa.id !== id);
    // Atualiza a lista de pessoas
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }
  */

  remover(id: number): Observable<Pessoa | null> {
    return this.httpClient.delete<Pessoa>(
      this.BASE_URL + "/" + id,
      this.httpOptions).pipe(
        map((resp: HttpResponse<Pessoa>) => {
          if (resp.status != 200) {
            return null;
          } else {
            // Acertar a data
            let pes: Pessoa = new Pessoa(
              resp.body?.id,
              resp.body?.nome,
              resp.body?.idade,
              resp.body?.dataDeNascimento,
              resp.body?.motorista);

              pes.dataParaRest();
              return pes;
          }
        }),
        catchError((e, c) => {
          return throwError(() => e);
        })
      );
  }
}
