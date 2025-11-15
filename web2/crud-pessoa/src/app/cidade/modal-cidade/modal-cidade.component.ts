import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Cidade } from '../../shared';

@Component({
  selector: 'app-modal-cidade',
  standalone: false,
  templateUrl: './modal-cidade.component.html',
  styleUrl: './modal-cidade.component.css'
})
export class ModalCidadeComponent {
  @Input() cidade!: Cidade;

  constructor(public activeModal: NgbActiveModal) { }
}
