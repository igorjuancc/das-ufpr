export class Pessoa {
    constructor(
        public id?: number,
        public nome?: string,
        public idade: number = 0,
        public dataDeNascimento?: string,
        public motorista?: string
    ) { }
}