import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco } from '../../shared';
import { EnderecoService } from '../services';
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

  mensagem: string = "";
  mensagem_detalhes: string = "";
  botaoDesabilitado = false;

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
    /*
    const res = this.enderecoService.buscarPorId(id);
    if (res !== undefined)
      this.endereco = res;
    else
      throw new Error("Endereço não encontrado: id = " + id);
    */

    this.endereco.residencial = false;

    this.enderecoService.buscarPorId(+id).subscribe({
      next: (endereco) => {
        if (endereco == null) {
          this.mensagem = `Erro buscando endereco ${this.endereco.rua}, ${this.endereco.numero}, ${this.endereco.bairro}`;
          this.mensagem_detalhes = `Endereco não encontrada ${this.endereco.rua}, ${this.endereco.numero}, ${this.endereco.bairro}`;
          this.botaoDesabilitado = true;
        } else {
          this.endereco = endereco;
          this.botaoDesabilitado = false;
        }
      },
      error: (err) => {
        this.mensagem = `Erro buscando endereco ${this.endereco.rua}, ${this.endereco.numero}, ${this.endereco.bairro}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        this.botaoDesabilitado = true;
      }
    });
  }

  /*
  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formEndereco.form.valid) {
      // Efetivamente atualiza o endereco
      this.enderecoService.atualizar(this.endereco);
      // Redireciona para /enderecos/listar
      this.router.navigate(['/enderecos']);
    }
  }
  */

  atualizar(): void {
    this.enderecoService.atualizar(this.endereco).subscribe({
      next: (endereco) => {
        this.router.navigate(["/enderecos"]);
      },
      error: (err) => {
        this.mensagem = `Erro alterando endereço ${this.endereco.rua}, ${this.endereco.numero}, ${this.endereco.bairro}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
  }
}
