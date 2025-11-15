import { Component, OnInit } from '@angular/core';
import { Endereco } from '../../shared';
import { EnderecoService } from '../services';
import { ModalEnderecoComponent } from '../modal-endereco';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-listar-endereco',
  standalone: false,
  templateUrl: './listar-endereco.component.html',
  styleUrl: './listar-endereco.component.css'
})
export class ListarEnderecoComponent implements OnInit {
  enderecos: Endereco[] = [];

  constructor(private enderecoService: EnderecoService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.enderecos = this.listarTodos();
  }

  listarTodos(): Endereco[] {
    /*
    return [
      new Endereco(1, "Rua das Flores", 10, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
      new Endereco(2, "Rua das Flores", 100, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
      new Endereco(3, "Rua das Flores", 200, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
    ];
    */

    return this.enderecoService.listarTodos();
  }

  remover($event: any, endereco: Endereco): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o endereço ${endereco.rua}, ${endereco.numero}, ${endereco.bairro}?`)) {
      this.enderecoService.remover(endereco.id!);
      this.enderecos = this.listarTodos();
    }
  }

  abrirModalEndereco(endereco: Endereco) {
    const modalRef = this.modalService.open(ModalEnderecoComponent);
    modalRef.componentInstance.endereco = endereco;
  }
}
