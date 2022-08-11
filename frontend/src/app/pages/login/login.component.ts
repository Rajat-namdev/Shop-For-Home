import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Role } from "../../enum/Role";
import Swal from "sweetalert2";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  isInvalid: boolean;
  isLogout: boolean;
  submitted = false;
  model: any = {
    username: "",
    password: "",
    remembered: false,
  };

  returnUrl = "/";

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    let params = this.route.snapshot.queryParamMap;
    this.isLogout = params.has("logout");
    this.returnUrl = params.get("returnUrl");
  }

  onSubmit() {
    this.submitted = true;
    
    this.userService.login(this.model).subscribe((user) => {
      if (user) {
        if (user.role == Role.Manager) {
          Swal.fire('Successfully done !!', 'You logged in as ' +user.name,'success');
          this.returnUrl = "/seller";
        }

        this.router.navigateByUrl(this.returnUrl);
        Swal.fire('Login Successfull !!', 'You logged in as ' +user.name,'success');
      } else {
        this.isLogout = false;
        this.isInvalid = true;
      }
    });
  }

  fillLoginFields(u, p) {
    this.model.username = u;
    this.model.password = p;
    this.onSubmit();
  }
}
