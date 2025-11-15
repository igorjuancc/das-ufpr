import { Injectable } from '@angular/core';
import { Estado } from '../../shared/models/estado.model';

const LS_CHAVE = "estados";

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  constructor() { }

  listarTodos(): Estado[] {
    const estados = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return estados ? JSON.parse(estados) : [];
  }

  inserir(estado: Estado): void {
    // Obtém a lista completa de estados
    const estados = this.listarTodos();
    // Seta um ID único
    // Usamos o Timestamp, quantidade de segundos desde 1970
    estado.id = new Date().getTime();
    // Adiciona no final da lista
    estados.push(estado);
    // Armazena no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }

  buscarPorId(id: number): Estado | undefined {
    // Obtém a lista completa de estados
    const estados = this.listarTodos();
    // Efetua a busca
    // find() : retorna o primeiro elemento da lista que
    // satisfaz a condição, caso contrário, undefined
    return estados.find(estado => estado.id === id);
  }

  atualizar(estado: Estado): void {
    // Obtem a lista completa de estados
    const estados = this.listarTodos();
    // Varre a lista de estados
    // Quando encontra estados com mesmo id, altera a lista
    estados.forEach((obj, index, objs) => {
      if (estado.id === obj.id) {
        objs[index] = estado
      }
    });
    // Armazena a nova lista no LocalStorage
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }

  remover(id: number): void {
    // Obtem a lista completa de estados
    let estados = this.listarTodos();
    // filter() : retorna a mesma lista contendo todos
    // os registros que satisfazem a condição
    estados = estados.filter(estado => estado.id !== id);
    // Atualiza a lista de estados
    localStorage[LS_CHAVE] = JSON.stringify(estados);
  }
}
