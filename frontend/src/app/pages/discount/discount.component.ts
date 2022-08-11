import { Component, OnInit } from "@angular/core";
import { OrderService } from "src/app/services/order.service";
import { Subscription } from "rxjs";
import { Discount } from "src/app/models/discount";
import { ActivatedRoute, Router } from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: "app-discount",
  templateUrl: "./discount.component.html",
  styleUrls: ["./discount.component.css"],
})
export class DiscountComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService
  ) {}

  page: any;
  querySub: Subscription;
  discountDetail: Discount;
  coupon: any;

  ngOnInit() {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.getCouponList();
    });
  }

  getCouponList() {
    let nextPage = 1;
    let size = 10;
    if (this.route.snapshot.queryParamMap.get("page")) {
      nextPage = +this.route.snapshot.queryParamMap.get("page");
      size = +this.route.snapshot.queryParamMap.get("size");
    }
    this.orderService.getCouponPage(nextPage, size).subscribe(
      (page) => (this.page = page),
      (_) => {
        console.log("Get User Failed");
      }
    );
  }

  addCoupon(code: string) {
    this.orderService
      .addCoupon(code)
      .subscribe((response) => this.getCouponList());
      Swal.fire('Successfully done !!', 'Coupon ' + code +' Created !!','success');
  }

  deleteCoupon(code: string) {
    this.orderService
      .deleteCoupon(code)
      .subscribe((response) => this.getCouponList());
      Swal.fire('Deleted !!',' Coupon has been deleted.','info');
  }
}
