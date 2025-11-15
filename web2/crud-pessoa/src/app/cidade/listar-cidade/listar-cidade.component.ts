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

  constructor(private cidadeService: CidadeService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.cidades = this.listarTodas();
  }

  listarTodas(): Cidade[] {
    /*
    return [
      new Cidade(1, "Curitiba", "Paraná"),
      new Cidade(1, "Pinhais", "Paraná"),
      new Cidade(1, "Florianópolis", "Santa Catarina")
    ];
    */

    return this.cidadeService.listarTodas();
  }

  remover($event: any, cidade: Cidade): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a cidade ${cidade.nome} - ${cidade.estado}?`)) {
      this.cidadeService.remover(cidade.id!);
      this.cidades = this.listarTodas();
    }
  }

  abrirModalCidade(cidade: Cidade) {
    const modalRef = this.modalService.open(ModalCidadeComponent);
    modalRef.componentInstance.cidade = cidade;
  }
}
