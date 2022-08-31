// import { Component, OnInit } from '@angular/core';
// import { AdminService } from './../admin.service';

// import { Router } from '@angular/router';
// import { Admin } from '../admin';
// import Swal from "sweetalert2";


// @Component({
//   selector: 'app-admin-login',
//   templateUrl: './admin-login.component.html',
//   styleUrls: ['./admin-login.component.css']
// })
// export class AdminLoginComponent implements OnInit {

//   constructor(private adminService:AdminService, private router:Router) { }

//   ngOnInit(): void {
//   }

//   admin= new Admin()

// email:string=''
// password:string=''

// sendAdminData(admin:Admin){
//   this.adminService.sendAdminData(admin)
// }
//   adminLogin(data:any){
//     this.email=data.email;
//    this.password=data.password;

//      this.adminService.adminLogin(this.email,this.password).subscribe((data)=>{
//       this.admin=data
//           this.sendAdminData(this.admin)
//           Swal.fire('Login Successfull !!', 'You logged in as ' +this.admin.name,'success');
//           this.router.navigate(['/admin'])
//      },err =>{
//       Swal.fire('Error !!', 'UserName or Password Wrong','error');
      
//       }
//      )




//    }

// }

import { Component, OnInit } from '@angular/core';
import { AdminService } from './../admin.service';

import { Router } from '@angular/router';
import { Admin } from '../admin';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  public isLogged:any
  constructor(private adminService:AdminService, private router:Router) { }

  ngOnInit(): void {
    this.isLogged=localStorage.getItem("isLogged")
    if (this.isLogged=='1') {
      this.router.navigate(["/admin"])
    }
  }

  admin= new Admin()

email:string=''
password:string=''

sendAdminData(admin:Admin){
  this.adminService.sendAdminData(admin)
}
  adminLogin(data:any){
    this.email=data.email;
    this.password=data.password;

     this.adminService.adminLogin(this.email,this.password).subscribe((data)=>{
      this.admin=data
          this.sendAdminData(this.admin)
          localStorage.setItem("isLogged",'1')
          localStorage.setItem('userName',data.name)
          
          this.router.navigate(['/admin'])
     },err =>{
      Swal.fire(
        'Error!',
          'Wrong Username or password',
          'error'
      )
      }
     )




   }

}

