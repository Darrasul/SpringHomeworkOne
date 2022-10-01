import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {RouterTestingModule} from "@angular/router/testing";
import { FooterComponent } from './footer/footer.component';
import { AllProductsComponent } from './all-products/all-products.component';
import { ProductComponent } from './product/product.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { ProductServiceComponent } from './product-service/product-service.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AllProductsComponent,
    ProductComponent,
    ProductServiceComponent
  ],
    imports: [
        BrowserModule,
        RouterTestingModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [ProductServiceComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
