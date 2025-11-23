export class Pessoa {
    constructor(
        public id?: number,
        public nome?: string,
        public idade: number = 0,
        public dataDeNascimento?: string,
        public motorista?: string
    ) { }

    // Converter de dd/mm/aaaa para aaaa-mm-dd
    dataParaRest() {
        if (this.dataDeNascimento) {
            let dia, mes, ano;

            if (this.dataDeNascimento.indexOf("/") == -1) {
                dia = this.dataDeNascimento.substring(0, 2);
                mes = this.dataDeNascimento.substring(2, 4);
                ano = this.dataDeNascimento.substring(4);
            } else {
                [dia, mes, ano] = this.dataDeNascimento.split("/");
            }
            this.dataDeNascimento = `${ano}-${mes}-${dia}`;
        }
    }

    // Converter de aaaa-mm-dd para dd/mm/aaaa
    dataDoRest() {
        if (this.dataDeNascimento) {
            const [dia, mes, ano] = this.dataDeNascimento.split("-");
            this.dataDeNascimento = `${dia}/${mes}/${ano}`;

        }
    }
}