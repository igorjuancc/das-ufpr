import { Injectable } from '@angular/core';
import { Endereco } from '../../shared/models/endereco.model';

const LS_CHAVE = "enderecos";

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {

  constructor() { }

  listarTodos(): Endereco[] {
    const enderecos = localStorage[LS_CHAVE];
    // Precisa do condicional, pois retornar undefined se
    // a chave não existe
    return enderecos ? JSON.parse(enderecos) : [];
  }

  inserir(endereco: Endereco): void {
      // Obtém a lista completa de enderecos
      const enderecos = this.listarTodos();
      // Seta um ID único
      // Usamos o Timestamp, quantidade de segundos desde 1970
      endereco.id = new Date().getTime();
      // Adiciona no final da lista
      enderecos.push(endereco);
      // Armazena no LocalStorage
      localStorage[LS_CHAVE] = JSON.stringify(enderecos);
    }
  
    buscarPorId(id: number): Endereco | undefined {
      // Obtém a lista completa de enderecos
      const enderecos = this.listarTodos();
      // Efetua a busca
      // find() : retorna o primeiro elemento da lista que
      // satisfaz a condição, caso contrário, undefined
      return enderecos.find(endereco => endereco.id === id);
    }
  
    atualizar(endereco: Endereco): void {
      // Obtem a lista completa de enderecos
      const enderecos = this.listarTodos();
      // Varre a lista de enderecos
      // Quando encontra enderecos com mesmo id, altera a lista
      enderecos.forEach((obj, index, objs) => {
        if (endereco.id === obj.id) {
          objs[index] = endereco
        }
      });
      // Armazena a nova lista no LocalStorage
      localStorage[LS_CHAVE] = JSON.stringify(enderecos);
    }
  
    remover(id: number): void {
      // Obtem a lista completa de enderecos
      let enderecos = this.listarTodos();
      // filter() : retorna a mesma lista contendo todos
      // os registros que satisfazem a condição
      enderecos = enderecos.filter(endereco => endereco.id !== id);
      // Atualiza a lista de enderecos
      localStorage[LS_CHAVE] = JSON.stringify(enderecos);
    }
}
