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
  mensagem: string = "";
  mensagem_detalhes: string = "";

  constructor(private enderecoService: EnderecoService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.enderecos = this.listarTodos();
  }

  /*
  listarTodos(): Endereco[] {
    
    return [
      new Endereco(1, "Rua das Flores", 10, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
      new Endereco(2, "Rua das Flores", 100, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
      new Endereco(3, "Rua das Flores", 200, "Proximo ao Poema", "Xaxim", "88888-888", "Curitiba", "PR"),
    ];
    

    return this.enderecoService.listarTodos();
  }
  */
  listarTodos(): Endereco[] {
    this.enderecoService.listarTodos().subscribe({
      next: (data: Endereco[] | null) => {
        if (data == null) {
          this.enderecos = [];
        }
        else {
          this.enderecos = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de enderecos";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.enderecos;
  }

  remover($event: any, endereco: Endereco): void {
    $event.preventDefault();
    this.mensagem = "";
    this.mensagem_detalhes = "";
    if (confirm(`Deseja realmente remover o endereço ${endereco.rua}, ${endereco.numero}, ${endereco.bairro}?`)) {
      this.enderecoService.remover(+endereco.id!).
        subscribe({
          complete: () => { this.listarTodos(); },
          error: (err) => {
            this.mensagem = `Erro removendo endereço ${endereco.rua}, ${endereco.numero}, ${endereco.bairro}`;
            this.mensagem_detalhes = `[${err.status}] ${err.message}`;
          }
        });
    }
  }

  abrirModalEndereco(endereco: Endereco) {
    const modalRef = this.modalService.open(ModalEnderecoComponent);
    modalRef.componentInstance.endereco = endereco;
  }
}
