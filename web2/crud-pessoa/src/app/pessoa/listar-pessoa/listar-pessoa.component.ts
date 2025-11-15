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

  constructor(private pessoaService: PessoaService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.pessoas = this.listarTodos();
  }

  listarTodos(): Pessoa[] {
    /*
    return [
      new Pessoa(1, "Elson", 22),
      new Pessoa(1, "Ralf", 35),
      new Pessoa(1, "Elaine", 29),
      new Pessoa(1, "Joeliton", 30)
    ];
    */

    return this.pessoaService.listarTodos();
  }

  remover($event: any, pessoa: Pessoa): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a pessoa ${pessoa.nome}?`)) {
      this.pessoaService.remover(pessoa.id!);
      this.pessoas = this.listarTodos();
    }
  }

  abrirModalPessoa(pessoa: Pessoa) {
    const modalRef = this.modalService.open(ModalPessoaComponent);
    modalRef.componentInstance.pessoa = pessoa;
  }

}
