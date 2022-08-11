import { Component, OnDestroy, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { Subscription } from "rxjs";
import { JwtResponse } from "../../response/JwtResponse";
import { Router } from "@angular/router";
import { Role } from "../../enum/Role";
import { User } from "src/app/models/User";
import Swal from "sweetalert2";

@Component({
  selector: "app-navigation",
  templateUrl: "./navigation.component.html",
  styleUrls: ["./navigation.component.css"],
})
export class NavigationComponent implements OnInit, OnDestroy {
  currentUserSubscription: Subscription;
  name$;
  name: string;
  currentUser: JwtResponse;
  root = "/";
  Role = Role;
  userList: User[];

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.name$ = this.userService.name$.subscribe(
      (aName) => (this.name = aName)
    );
    this.currentUserSubscription = this.userService.currentUser.subscribe(
      (user) => {
        this.currentUser = user;
        if (!user || user.role == Role.Customer) {
          this.root = "/";
        } else {
          this.root = "/seller";
        }
      }
    );
  }

  ngOnDestroy(): void {
    this.currentUserSubscription.unsubscribe();
  }

  logout() {
    this.userService.logout();
    Swal.fire('Logout Successfull !!', 'You have been logged out','warning');

  }

  getUsers() {
    this.userService
      .getUsers()
      .subscribe((response) => (this.userList = response));
  }
}
