import { Component, OnInit } from "@angular/core";
import { Location } from "@angular/common";
import { User } from "../../models/User";
import { UserService } from "../../services/user.service";
import { Router } from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"],
})
export class SignupComponent implements OnInit {
  user: User;

  constructor(
    private location: Location,
    private userService: UserService,
    private router: Router
  ) {
    this.user = new User();
  }

  ngOnInit() {}
  onSubmit() {
    this.userService.signUp(this.user).subscribe(
      (u) => {
        Swal.fire('Congratulations !!', 'Your account has been successfully created !!','success');
        this.router.navigate(["/login"]);
        
      },
      (e) => {}
    );
  }
}
