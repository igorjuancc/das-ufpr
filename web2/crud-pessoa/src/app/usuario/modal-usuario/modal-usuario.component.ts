import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '../../shared';

@Component({
  selector: 'app-modal-usuario',
  standalone: false,
  templateUrl: './modal-usuario.component.html',
  styleUrl: './modal-usuario.component.css'
})
export class ModalUsuarioComponent {

  @Input() usuario: Usuario = new Usuario();

  constructor(public activeModal: NgbActiveModal) { }
}
