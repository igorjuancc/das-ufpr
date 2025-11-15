import { Component, ViewChild } from '@angular/core';
import { CidadeService } from '../services';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Cidade } from '../../shared';

@Component({
  selector: 'app-editar-cidade',
  standalone: false,
  templateUrl: './editar-cidade.component.html',
  styleUrl: './editar-cidade.component.css'
})
export class EditarCidadeComponent {
  // Recebe uma referência do formulário aqui no componente
  // 'formCidade' deve ser o nome do formulário no HTML
  @ViewChild('formCidade') formCidade!: NgForm;
  // Atributo de binding, os dados digitados no formulário
  // vêm para este atributo
  cidade: Cidade = new Cidade();

  constructor(
    private cidadeService: CidadeService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém a cidade
    const res = this.cidadeService.buscarPorId(id);
    if (res !== undefined)
      this.cidade = res;
    else
      throw new Error("Cidade não encontrada: id = " + id);
  }

  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formCidade.form.valid) {
      // Efetivamente atualiza a cidade
      this.cidadeService.atualizar(this.cidade);
      // Redireciona para /cidades/listar
      this.router.navigate(['/cidades']);
    }
  }
}
