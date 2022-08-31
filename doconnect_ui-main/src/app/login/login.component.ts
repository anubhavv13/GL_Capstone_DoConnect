import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public isLogged:any;
  constructor( private userService:UserService
    , private router:Router) { }

  ngOnInit(): void {
    this.isLogged=localStorage.getItem("isLogged");
    if (this.isLogged=='1') {
      this.router.navigate(["/user"])
    }
  }

 user=new User()
  email:string=''
  password:string=''

  sendUserData(user:User){
    this.userService.sendUserData(user)
  }

 userLogin(data:any) {

  this.email=data.email
  this.password=data.password

	this.userService.userLogin(this.email,this.password).
  subscribe((data)=>{
    this.user=data
    // 
    localStorage.setItem("isLogged",'1');
    localStorage.setItem("userName",data.name);
    Swal.fire(
      'Logged in Successfully',
      data.name,
      'success'
    )
    this.sendUserData(this.user)
    this.router.navigate(['/user'])
  },err =>{
    alert("UserName or Password Wrong")
  })
	}
}
