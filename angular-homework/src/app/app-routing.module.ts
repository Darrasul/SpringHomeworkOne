import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AllProductsComponent} from "./all-products/all-products.component";
import {ProductComponent} from "./product/product.component";

const routes: Routes = [
  {path: "", pathMatch:"full", redirectTo:"product"},
  {path: "product", component: AllProductsComponent},
  {path: "product/:id", component: ProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
