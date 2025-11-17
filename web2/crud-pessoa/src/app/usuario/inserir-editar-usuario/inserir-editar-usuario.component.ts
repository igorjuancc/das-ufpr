import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from '../../shared';
import { UsuarioService } from '../services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-inserir-editar-usuario',
  standalone: false,
  templateUrl: './inserir-editar-usuario.component.html',
  styleUrl: './inserir-editar-usuario.component.css'
})

export class InserirEditarUsuarioComponent implements OnInit {
  @ViewChild('formUsuario') formUsuario!: NgForm;
  novoUsuario: boolean = true;
  usuario: Usuario = new Usuario();
  id!: string;
  loading!: boolean;
  senhaAntiga: string = "";
  constructor(
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.usuario = new Usuario();
    this.loading = false;
    this.id = this.route.snapshot.params['id'];
    this.novoUsuario = !this.id;
    if (!this.novoUsuario) {
      this.usuarioService.buscarPorId(+this.id).subscribe(
        usuario => {
          this.usuario = usuario;
          this.senhaAntiga = usuario.senha ? usuario.senha : "";
          this.usuario.senha = "";
        }
      );
    }
  }
  
  salvar(): void {
    this.loading = true;
    if (this.formUsuario.form.valid) {
      if (this.novoUsuario) {
        this.usuarioService.inserir(this.usuario).subscribe(
          usuario => {
            this.loading = false;
            this.router.navigate(["/usuarios"]);
          });
      }
      else {
        if (this.usuario.senha === "") {
          this.usuario.senha = this.senhaAntiga;
        }
        this.usuarioService.alterar(this.usuario).subscribe(
          usuario => {
            this.loading = false;
            this.router.navigate(["/usuarios"]);
          });
      }
    }
    this.loading = false;
  }
}