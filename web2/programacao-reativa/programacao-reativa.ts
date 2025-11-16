import { from, of, filter, map, take, count, max, concat } from "rxjs";

console.log("Exemplo 1");

let obs = of("Curitiba", 200, 4.5, true);
console.log(obs);


/********************************************************/
console.log("Exemplo 2");

let arr = ["Ibicaré", "Joaçaba", "Treze Tílias", "Luzerna"]
from(arr).subscribe({
    next: valor => {
        let x = 'Cidade: ' + valor;
        console.log(x)
    },
    error: valor => console.log('Erro: ' + valor),
    complete: () => console.log('acabou')
});

/********************************************************/
console.log("Exemplo 3");

let arr1 = ["Ibicaré", "Joaçaba", "Treze Tílias", "Luzerna"]
from(arr1).subscribe({
    next(valor) {
        let x = 'Cidade: ' + valor;
        console.log(x);
    },
    error(valor) {
        console.log('Erro: ' + valor);
    },
    complete() {
        console.log('acabou');
    }
});

/********************************************************/
console.log("Exemplo 4");

of("Curitiba", 200, 4.5, true).subscribe({
    next: valor => {
        let x = 'Valor: ' + valor;
        console.log(x)
    },
    error: valor => console.log('Erro: ' + valor),
    complete: () => console.log('acabou')
}); 

/********************************************************/
console.log("Exemplo 5");

let valores = [5, 10, 15, 20, 25, 30, 35, 40];

const obs1 = from(valores).pipe(
    filter(valor => valor % 2 == 0)
);

obs1.subscribe(
    valor => console.log(valor)
);

/********************************************************/
console.log("Exemplo 6");

let valores1 = [5, 10, 15, 20, 25, 30, 35, 40];

const obs2 = from(valores1).pipe(
    filter(valor => valor % 2 == 0),
    map(valor => valor / 10)
);

obs2.subscribe({
    next(valor) {
        console.log(valor);
    }
});

/********************************************************/
console.log("Exemplo 7");

let valores2 = [5, 10, 15, 20, 25, 30, 35, 40];
const obs3 = from(valores2).pipe(
    filter(valor => valor % 2 == 0),
    map(valor => valor / 10),
    take(2)
);
obs3.subscribe({
    next(valor) {
        console.log(valor);
    }
});

/********************************************************/
console.log("Exemplo 8");

let valores3 = [5, 10, 15, 20, 25, 30, 35, 40];

const obs4 = from(valores3).pipe(
    filter(valor => valor % 2 == 0),
    count()
);

obs4.subscribe({
    next(valor) {
        console.log(valor);
    }
});

/********************************************************/
console.log("Exemplo 9");

let valores4 = [5, 10, 15, 20, 25, 30, 35, 40];
const obs5 = from(valores4).pipe(
    count(valor => valor % 2 == 0)
);

obs5.subscribe({
    next(valor) {
        console.log(valor);
    }
});

/********************************************************/
console.log("Exemplo 10");

let valores5 = [5, 10, 15, 20, 25, 30, 35, 40];

const obs6 = from(valores5).pipe(
    max()
);

obs6.subscribe({
    next(valor) {
        console.log(valor);
    }
});

/********************************************************/
console.log("Exemplo 11");

const obs7 = concat(obs2, obs3, obs4);

obs7.subscribe({
    next(valor) {
        console.log(valor);
    }
});