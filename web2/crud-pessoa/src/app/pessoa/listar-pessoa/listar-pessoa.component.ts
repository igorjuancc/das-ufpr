import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../services';
import { Pessoa } from '../../shared';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalPessoaComponent } from '../modal-pessoa';

@Component({
  selector: 'app-listar-pessoa',
  standalone: false,
  templateUrl: './listar-pessoa.component.html',
  styleUrl: './listar-pessoa.component.css'
})
export class ListarPessoaComponent implements OnInit {
  pessoas: Pessoa[] = [];
  mensagem: string = "";
  mensagem_detalhes: string = "";

  constructor(private pessoaService: PessoaService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.pessoas = this.listarTodos();
  }

  /*
  listarTodos(): Pessoa[] {
    
    return [
      new Pessoa(1, "Elson", 22),
      new Pessoa(1, "Ralf", 35),
      new Pessoa(1, "Elaine", 29),
      new Pessoa(1, "Joeliton", 30)
    ];
    

    return this.pessoaService.listarTodos();
  }
  */

  listarTodos(): Pessoa[] {
    this.pessoaService.listarTodos().subscribe({
      next: (data: Pessoa[] | null) => {
        if (data == null) {
          this.pessoas = [];
        }
        else {
          this.pessoas = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de pessoas";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.pessoas;
  }

  /*
  remover($event: any, pessoa: Pessoa): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a pessoa ${pessoa.nome}?`)) {
      this.pessoaService.remover(pessoa.id!);
      this.pessoas = this.listarTodos();
    }
  }
  */

  remover($event: any, pessoa: Pessoa): void {
    $event.preventDefault();
    this.mensagem = "";
    this.mensagem_detalhes = "";
    if (confirm(`Deseja realmente remover a pessoa ${pessoa.nome}?`)) {
      this.pessoaService.remover(+pessoa.id!).
        subscribe({
          complete: () => { this.listarTodos(); },
          error: (err) => {
            this.mensagem = `Erro removendo pessoa ${pessoa.id} - ${pessoa.nome}`;
            this.mensagem_detalhes = `[${err.status}] ${err.message}`;
          }
        });
    }
  }

  abrirModalPessoa(pessoa: Pessoa) {
    const modalRef = this.modalService.open(ModalPessoaComponent);
    modalRef.componentInstance.pessoa = pessoa;
  }

}
