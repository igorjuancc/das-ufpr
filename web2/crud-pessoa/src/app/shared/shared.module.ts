import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MinimoValidatorDirective, NumericoDirective } from './directives';
import { CaixaAltaPipe } from './pipes';



@NgModule({
  declarations: [
    NumericoDirective,
    MinimoValidatorDirective,
    CaixaAltaPipe
  ],
  exports: [
    NumericoDirective,
    MinimoValidatorDirective,
    CaixaAltaPipe
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
