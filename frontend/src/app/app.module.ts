import { FooterComponent } from "./parts/footer/footer.component";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { NavigationComponent } from "./parts/navigation/navigation.component";
import { CardComponent } from "./pages/card/card.component";
import { PaginationComponent } from "./parts/pagination/pagination.component";
import { AppRoutingModule } from "./app-routing.module";
import { LoginComponent } from "./pages/login/login.component";
import { SignupComponent } from "./pages/signup/signup.component";
import { DetailComponent } from "./pages/product-detail/detail.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { CartComponent } from "./pages/cart/cart.component";
import { CookieService } from "ngx-cookie-service";
import { ErrorInterceptor } from "./_interceptors/error-interceptor.service";
import { JwtInterceptor } from "./_interceptors/jwt-interceptor.service";
import { OrderComponent } from "./pages/order/order.component";
import { OrderDetailComponent } from "./pages/order-detail/order-detail.component";
import { ProductListComponent } from "./pages/product-list/product.list.component";
import { UserDetailComponent } from "./pages/user-edit/user-detail.component";
import { ProductEditComponent } from "./pages/product-edit/product-edit.component";
import { WishListComponent } from "./pages/wish-list/wish-list.component";
import { ExcelService } from "./services/ExcelService";
import { AngularFileUploaderModule } from "angular-file-uploader";
import { Ng2SearchPipeModule } from "ng2-search-filter";
import { EmailComponent } from "./pages/email/email.component";
import { AdminuserComponent } from "./pages/adminuser/adminuser.component";
import { DiscountComponent } from "./pages/discount/discount.component";
import { OrderByPipe } from "./pipe/order-by.pipe";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AboutComponent } from './pages/about/about.component';
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    CardComponent,
    PaginationComponent,
    LoginComponent,
    SignupComponent,
    DetailComponent,
    CartComponent,
    OrderComponent,
    OrderDetailComponent,
    ProductListComponent,
    UserDetailComponent,
    ProductEditComponent,
    WishListComponent,
    EmailComponent,
    AdminuserComponent,
    DiscountComponent,
    FooterComponent,
    OrderByPipe,
    AboutComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AngularFileUploaderModule,
    Ng2SearchPipeModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatCardModule,
    MatIconModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,

  ],
  providers: [
    CookieService,
    ExcelService,
    AdminuserComponent,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
