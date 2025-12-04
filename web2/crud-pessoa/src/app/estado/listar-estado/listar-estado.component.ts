import { Component } from '@angular/core';
import { EstadoService } from '../services';
import { Estado } from '../../shared';
import { ModalEstadoComponent } from '../modal-estado';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-listar-estado',
  standalone: false,
  templateUrl: './listar-estado.component.html',
  styleUrl: './listar-estado.component.css'
})
export class ListarEstadoComponent {
  estados: Estado[] = [];
  mensagem: string = "";
  mensagem_detalhes: string = "";

  constructor(private estadoService: EstadoService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.estados = this.listarTodos();
  }

  /*
  listarTodos(): Estado[] {
    
    return [
      new Estado(1, "Paraná", "PR"),
      new Estado(1, "São Paulo", "SP"),
      new Estado(1, "Rio de Janeiro", "RJ"),
    ];
    

    return this.estadoService.listarTodos();
  }
  */

  listarTodos(): Estado[] {
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

  /*
  remover($event: any, estado: Estado): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o estado ${estado.nome} - ${estado.sigla}?`)) {
      this.estadoService.remover(estado.id!);
      this.estados = this.listarTodos();
    }
  }
  */

  remover($event: any, estado: Estado): void {
    $event.preventDefault();
    this.mensagem = "";
    this.mensagem_detalhes = "";
    if (confirm(`Deseja realmente remover o estado ${estado.nome} - ${estado.sigla}?`)) {
      this.estadoService.remover(+estado.id!).
        subscribe({
          complete: () => { this.listarTodos(); },
          error: (err) => {
            this.mensagem = `Erro removendo estado ${estado.nome} - ${estado.sigla}`;
            this.mensagem_detalhes = `[${err.status}] ${err.message}`;
          }
        });
    }
  }

  abrirModalEstado(estado: Estado) {
    const modalRef = this.modalService.open(ModalEstadoComponent);
    modalRef.componentInstance.estado = estado;
  }
}
