import { Component } from '@angular/core';
import { EstadoService } from '../services/estado.service';
import { Estado } from '../../shared/models/estado.model';

@Component({
  selector: 'app-listar-estado',
  standalone: false,
  templateUrl: './listar-estado.component.html',
  styleUrl: './listar-estado.component.css'
})
export class ListarEstadoComponent {
  estados: Estado[] = [];

  constructor(private estadoService: EstadoService) { }

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

}
