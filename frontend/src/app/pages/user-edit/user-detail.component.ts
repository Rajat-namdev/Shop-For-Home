import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { User } from "../../models/User";
import { Router } from "@angular/router";
import { Role } from "../../enum/Role";
import Swal from "sweetalert2";

@Component({
  selector: "app-user-detail",
  templateUrl: "./user-detail.component.html",
  styleUrls: ["./user-detail.component.css"],
})
export class UserDetailComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

  user = new User();

  ngOnInit() {
    const account = this.userService.currentUserValue.account;

    this.userService.get(account).subscribe(
      (u) => {
        this.user = u;
        this.user.password = "";
      },
      (e) => {}
    );
  }

  onSubmit() {
    this.userService.update(this.user).subscribe(
      (u) => {
        Swal.fire('Update Successfull !!', 'You Profile was updated !!','success');
        this.userService.nameTerms.next(u.name);
        let url = "/";
        if (this.user.role != Role.Customer) {
          url = "/seller";
        }
        this.router.navigateByUrl(url);
      },
      (_) => {}
    );
  }
}
