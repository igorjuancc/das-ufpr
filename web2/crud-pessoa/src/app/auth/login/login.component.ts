import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Login } from '../../shared';
import { LoginService } from '../services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  @ViewChild('formLogin') formLogin!: NgForm;
  login: Login = new Login();
  loading: boolean = false;
  message!: string;
  constructor(
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute) {
    if (this.loginService.usuarioLogado) {
      this.router.navigate(["/home"]);
    }
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.message = params['error'];
      });
  }

  logar(): void {
    this.loading = true;
    if (this.formLogin.form.valid) {
      this.loginService.login(this.login).subscribe({
        next: (usu) => {
          if (usu != null) {
            this.loginService.usuarioLogado = usu;
            this.loading = false;
            this.router.navigate(["/home"]);
          }
          else {
            this.message = "Usuário/Senha inválidos.";
          }
        },
        error: (err) => {
          this.message = `Erro ao efetuar login ${err.message}`;          
        }
      });
    }
    this.loading = false;
  }
}
