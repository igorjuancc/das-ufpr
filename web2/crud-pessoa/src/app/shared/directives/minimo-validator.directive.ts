import { Directive } from '@angular/core';
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

  constructor() { }

  validate(c: FormControl): ValidationErrors | null {
    let v: number = +c.value;
    if (isNaN(v)) {
      return { 'minimo': true, 'requiredValue': 18 }
    }
    else if (v < 18) {
      return { 'minimo': true, 'requiredValue': 18 }
    }
    return null;
  }
}
