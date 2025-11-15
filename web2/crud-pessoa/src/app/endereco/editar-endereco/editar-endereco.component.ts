import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco } from '../../shared/models/endereco.model';
import { EnderecoService } from '../services/endereco.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-endereco',
  standalone: false,
  templateUrl: './editar-endereco.component.html',
  styleUrl: './editar-endereco.component.css'
})
export class EditarEnderecoComponent implements OnInit {
  // Recebe uma referência do formulário aqui no componente
  // 'formEndereco' deve ser o nome do formulário no HTML
  @ViewChild('formEndereco') formEndereco!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  endereco: Endereco = new Endereco();

  constructor(
    private enderecoService: EnderecoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém o endereço
    const res = this.enderecoService.buscarPorId(id);
    if (res !== undefined)
      this.endereco = res;
    else
      throw new Error("Endereço não encontrado: id = " + id);
  }

  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formEndereco.form.valid) {
      // Efetivamente atualiza o endereco
      this.enderecoService.atualizar(this.endereco);
      // Redireciona para /enderecos/listar
      this.router.navigate(['/enderecos']);
    }
  }

}
