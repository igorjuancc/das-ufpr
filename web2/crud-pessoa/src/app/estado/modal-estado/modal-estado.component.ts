import { Component, Input } from '@angular/core';
import { Estado } from '../../shared';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-estado',
  standalone: false,
  templateUrl: './modal-estado.component.html',
  styleUrl: './modal-estado.component.css'
})
export class ModalEstadoComponent {
  @Input() estado!: Estado;

  constructor(public activeModal: NgbActiveModal) { }
}
