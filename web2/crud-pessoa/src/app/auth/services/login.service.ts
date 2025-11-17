import { Injectable } from '@angular/core';
import { Login, Usuario } from '../../shared';
import { map, Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const LS_CHAVE: string = "usuarioLogado";

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  BASE_URL = "http://localhost:3000/usuarios/";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  public get usuarioLogado(): Usuario | null {
    let usu = localStorage[LS_CHAVE];
    return (usu ? JSON.parse(localStorage[LS_CHAVE]) : null);
  }

  public set usuarioLogado(usuario: Usuario) {
    localStorage[LS_CHAVE] = JSON.stringify(usuario);
  }

  logout() {
    delete localStorage[LS_CHAVE];
  }

  /*
  login(login: Login): Observable<Usuario | null> {
    let usu = new Usuario(1, login.login,
      login.login, login.senha, "FUNC");
    if (login.login == login.senha) {
      if (login.login == "admin") {
        usu.perfil = "ADMIN";
      }
      else if (login.login == "gerente") {
        usu.perfil = "GERENTE";
      }
      return of(usu);
    }
    else {
      return of(null);
    }
  }
  */

  login(login: Login): Observable<Usuario | null> {
    return this.httpClient.get<Usuario[]>(this.BASE_URL, this.httpOptions)
      .pipe(map(lista => {
        let usu = lista.find(u => u.login === login.login &&
          u.senha === login.senha)
        if (usu != undefined) {
          return usu;
        }
        else {
          return null;
        }
      }));
  }
}
