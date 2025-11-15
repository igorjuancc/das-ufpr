import { Directive, Input } from '@angular/core';
import { FormControl, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';

@Directive({
  selector: '[minimo]',
  standalone: false,
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: MinimoValidatorDirective,
    multi: true
  }]
})
export class MinimoValidatorDirective implements Validator {

  @Input("valorMinimo") valorMinimo: string = "0";

  constructor() { }

  validate(c: FormControl): ValidationErrors | null {
    let v: number = +c.value;
    let min: number = +this.valorMinimo;

    if (isNaN(min)) {
      min = 0;
    }
    if (isNaN(v)) {
      return { 'minimo': true, 'requiredValue': min }
    }
    else if (v < min) {
      return { 'minimo': true, 'requiredValue': min }
    }
    return null;
  }
}
