import { Component, OnInit, ViewChild } from '@angular/core';
import { CidadeService } from '../services';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cidade, Estado } from '../../shared';
import { EstadoService } from '../../estado/services';

@Component({
  selector: 'app-editar-cidade',
  standalone: false,
  templateUrl: './editar-cidade.component.html',
  styleUrl: './editar-cidade.component.css'
})
export class EditarCidadeComponent implements OnInit {
  // Recebe uma referência do formulário aqui no componente
  // 'formCidade' deve ser o nome do formulário no HTML
  @ViewChild('formCidade') formCidade!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  cidade: Cidade = new Cidade();
  estados: Estado[] = [];

  mensagem: string = "";
  mensagem_detalhes: string = "";
  botaoDesabilitado = false;

  constructor(
    private cidadeService: CidadeService,
    private estadoService: EstadoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.estados = this.listarTodosEstados();
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém a cidade
    /*
    const res = this.cidadeService.buscarPorId(id);
    if (res !== undefined)
      this.cidade = res;
    else
      throw new Error("Cidade não encontrada: id = " + id);
    */

    this.cidadeService.buscarPorId(+id).subscribe({
      next: (cidade) => {
        if (cidade == null) {
          this.mensagem = `Erro buscando cidade ${this.cidade.nome} - ${this.cidade.estado}`;
          this.mensagem_detalhes = `Cidade não encontrada ${this.cidade.nome} - ${this.cidade.estado}`;
          this.botaoDesabilitado = true;
        } else {
          this.cidade = cidade;
          this.botaoDesabilitado = false;
        }
      },
      error: (err) => {
        this.mensagem = `Erro buscando cidade ${this.cidade.nome} - ${this.cidade.estado}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        this.botaoDesabilitado = true;
      }
    });
  }

  /*
  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formCidade.form.valid) {
      // Efetivamente atualiza a cidade
      this.cidadeService.atualizar(this.cidade);
      // Redireciona para /cidades/listar
      this.router.navigate(['/cidades']);
    }
  }
  */

  atualizar(): void {
    this.cidadeService.atualizar(this.cidade).subscribe({
      next: (estado) => {
        this.router.navigate(["/cidades"]);
      },
      error: (err) => {
        this.mensagem = `Erro alterando cidade ${this.cidade.nome} - ${this.cidade.estado}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
  }

  listarTodosEstados(): Estado[] {
    this.estadoService.listarTodos().subscribe({
      next: (data: Estado[] | null) => {
        if (data == null) {
          this.estados = [];
        }
        else {
          this.estados = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de estados";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.estados;
  }
}
