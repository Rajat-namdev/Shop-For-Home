import { Component, Injectable, OnDestroy, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { ProductService } from "../../services/product.service";
import { JwtResponse } from "../../response/JwtResponse";
import { Subscription } from "rxjs";
import { ActivatedRoute } from "@angular/router";
import { CategoryType } from "../../enum/CategoryType";
import { ProductStatus } from "../../enum/ProductStatus";
import { Role } from "../../enum/Role";
import { ExcelService } from "../../services/ExcelService";
import { Router } from "@angular/router";
import Swal from "sweetalert2";
@Injectable({
  providedIn: "root",
})
@Component({
  selector: "app-product.list",
  templateUrl: "./product.list.component.html",
  styleUrls: ["./product.list.component.css"],
})
export class ProductListComponent implements OnInit, OnDestroy {
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private excelService: ExcelService,
    private router: Router
  ) {}

  Role = Role;
  currentUser: JwtResponse;
  page: any;
  CategoryType = CategoryType;
  ProductStatus = ProductStatus;
  private querySub: Subscription;
  response: any;
  productInfo = [];
  // private router: Router;

  data: any[];
  // Variable to store shortLink from api response
  shortLink: string = "";
  loading: boolean = false; // Flag variable
  file: File = null; // Variable to store file

  ngOnInit() {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });
  }

  ngOnDestroy(): void {}

  update() {
    if (this.route.snapshot.queryParamMap.get("page")) {
      const currentPage = +this.route.snapshot.queryParamMap.get("page");
      const size = +this.route.snapshot.queryParamMap.get("size");
      this.getProds(currentPage, size);
    } else {
      this.getProds();
    }
  }

  getProds(page: number = 1, size: number = 10) {
    this.productService.getAllInPage(+page, +size).subscribe((page) => {
      this.page = page;
    });
  }

  remove(productId) {
    this.productService.delelte(productId).subscribe((_) => {
      // productInfos = productInfos.filter((e) => e.productId != productInfo);
      this.update();
      
      Swal.fire('Removed !!', ' Product has been removed.','warning');
    });
  }

  exportAsXLSX(): void {
    this.productService
      .getAll()
      .subscribe((response) => (this.productInfo = response));
    console.log(this.data);
    this.excelService.exportAsExcelFile(this.productInfo, "Report");
  }

  afuConfig = {
    uploadAPI: {
      url: "http://localhost:8081/api/csv/upload",
    },
  };

  // On file Select
  onChange(event) {
    this.file = event.target.files[0];
  }

  // OnClick of button Upload
  onUpload() {
    this.loading = !this.loading;
    console.log(this.file);
    Swal.fire('Uploaded Successfully !!', 'Your Produts has been uploaded.','success');
    this.productService.upload(this.file).subscribe((event: any) => {
      if (typeof event === "object") {
        // Short link via api response
        this.shortLink = event.link;

        this.loading = false; // Flag variable
        
      }
    });
  }

  onSubmit() {
    this.router.navigate(["/email"]);
  }
}
