import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'caixaAlta',
  standalone: false
})
export class CaixaAltaPipe implements PipeTransform {

  transform(value: string | undefined): string {
    if (value)
      return value.toUpperCase();
    else
      return "";
  }
}
