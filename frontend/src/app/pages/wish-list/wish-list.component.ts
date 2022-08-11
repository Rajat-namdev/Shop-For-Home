import { Component, OnInit } from "@angular/core";
import { Subscription } from "rxjs";
import { UserService } from "../../services/user.service";
import { JwtResponse } from "../../response/JwtResponse";
import { ActivatedRoute } from "@angular/router";
import { ProductInfo } from "src/app/models/productInfo";
import { WishListService } from "src/app/services/wish-list.service";
import Swal from "sweetalert2";

@Component({
  selector: "app-wish-list",
  templateUrl: "./wish-list.component.html",
  styleUrls: ["./wish-list.component.css"],
})
export class WishListComponent implements OnInit {
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private wishListService: WishListService
  ) {
    this.userSubscription = this.userService.currentUser.subscribe(
      (user) => (this.currentUser = user)
    );
  }

  productInfo = [];
  total = 0;
  currentUser: JwtResponse;
  userSubscription: Subscription;
  addedToWishlist: boolean = false;

  page: any;
  productId: string;
  product = new ProductInfo();
  isEdit = false;
  querySub: Subscription;

  ngOnInit() {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });
  }

  update() {
    let nextPage = 1;
    let size = 10;
    if (this.route.snapshot.queryParamMap.get("page")) {
      nextPage = +this.route.snapshot.queryParamMap.get("page");
      size = +this.route.snapshot.queryParamMap.get("size");
    }
    this.wishListService.getPage(nextPage, size).subscribe(
      (page) => (this.page = page),
      (_) => {
        console.log("Get Orde Failed");
      }
    );
    console.log(this.page);
  }

  handleRemoveFromWishList(productId: string) {
    console.log(productId);
    this.wishListService.removeFromWishList(productId).subscribe(
      (success) => {
        this.update();
        Swal.fire('Removed !!', this.currentUser.name+ ', Product has been removed.','warning');
      },
      (_) => console.log("Remove Cart Failed")
    );
  }
}
