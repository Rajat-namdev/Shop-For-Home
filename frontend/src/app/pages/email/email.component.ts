import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import Swal from 'sweetalert2';

@Component({
  selector: "app-email",
  templateUrl: "./email.component.html",
  styleUrls: ["./email.component.css"],
})
export class EmailComponent implements OnInit {
  constructor(private https: HttpClient) {}

  ngOnInit() {}

  title = "EmailTemplate";
  dataset: Details = {
    recipient: "",
    subject: "",
    msgBody: "",
  };

  dataset1: Details;

  onSubmit() {
    Swal.fire('Successfully done !!', 'Email sent Successfully !!','success');
    console.log(this.dataset);
    this.https
      .post<Details>("http://localhost:8082/api/sendMail", this.dataset)
      .subscribe((res) => {
        this.dataset = res;
        console.log(this.dataset);
        // alert("Email Sent successfully");
        

        this.dataset.recipient = "";
        this.dataset.subject = "";
        this.dataset.msgBody = "";
        
      });
  }
}
interface Details {
  recipient: string;
  subject: string;
  msgBody: string;
}
