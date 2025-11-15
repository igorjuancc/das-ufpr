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

  constructor(private estadoService: EstadoService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.estados = this.listarTodos();
  }

  listarTodos(): Estado[] {
    /*
    return [
      new Estado(1, "Paraná", "PR"),
      new Estado(1, "São Paulo", "SP"),
      new Estado(1, "Rio de Janeiro", "RJ"),
    ];
    */

    return this.estadoService.listarTodos();
  }

  remover($event: any, estado: Estado): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o estado ${estado.nome} - ${estado.sigla}?`)) {
      this.estadoService.remover(estado.id!);
      this.estados = this.listarTodos();
    }
  }

  abrirModalEstado(estado: Estado) {
    const modalRef = this.modalService.open(ModalEstadoComponent);
    modalRef.componentInstance.estado = estado;
  }

}
