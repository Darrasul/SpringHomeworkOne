import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceComponent} from "../product-service/product-service.component";

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.scss']
})
export class AllProductsComponent implements OnInit {

  products : Product[] = [];

  constructor(private productService: ProductServiceComponent) { }

  ngOnInit(): void {
    this.productService.showAll()
      .subscribe(response => {
        this.products = response.content
        }, error => {
        console.log(error)
      })
  }

  delete(id: number | null){
    this.productService.delete(id)
      .subscribe(error => {
        console.log(error);
      })
  }

}
