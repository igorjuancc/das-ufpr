import { Component, OnInit } from '@angular/core';
import { Cidade } from '../../shared';
import { CidadeService } from '../services';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalCidadeComponent } from '../modal-cidade';

@Component({
  selector: 'app-listar-cidade',
  standalone: false,
  templateUrl: './listar-cidade.component.html',
  styleUrl: './listar-cidade.component.css'
})
export class ListarCidadeComponent implements OnInit {
  cidades: Cidade[] = [];
  mensagem: string = "";
  mensagem_detalhes: string = "";

  constructor(private cidadeService: CidadeService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.cidades = this.listarTodas();
  }

  /*
  listarTodas(): Cidade[] {
    
    return [
      new Cidade(1, "Curitiba", "Paraná"),
      new Cidade(1, "Pinhais", "Paraná"),
      new Cidade(1, "Florianópolis", "Santa Catarina")
    ];
    

    return this.cidadeService.listarTodas();
  }
  */

  listarTodas(): Cidade[] {
    this.cidadeService.listarTodas().subscribe({
      next: (data: Cidade[] | null) => {
        if (data == null) {
          this.cidades = [];
        }
        else {
          this.cidades = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de cidades";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.cidades;
  }

  /*
  remover($event: any, cidade: Cidade): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a cidade ${cidade.nome} - ${cidade.estado}?`)) {
      this.cidadeService.remover(cidade.id!);
      this.cidades = this.listarTodas();
    }
  }
  */

  remover($event: any, cidade: Cidade): void {
    $event.preventDefault();
    this.mensagem = "";
    this.mensagem_detalhes = "";
    if (confirm(`Deseja realmente remover a cidade ${cidade.nome} - ${cidade.estado?.sigla}?`)) {
      this.cidadeService.remover(+cidade.id!).
        subscribe({
          complete: () => { this.listarTodas(); },
          error: (err) => {
            this.mensagem = `Erro removendo cidade ${cidade.nome} - ${cidade.estado?.sigla}`;
            this.mensagem_detalhes = `[${err.status}] ${err.message}`;
          }
        });
    }
  }

  abrirModalCidade(cidade: Cidade) {
    const modalRef = this.modalService.open(ModalCidadeComponent);
    modalRef.componentInstance.cidade = cidade;
  }
}
