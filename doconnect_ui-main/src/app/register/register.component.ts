import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  constructor( private userService:UserService , private router:Router) { }

  ngOnInit(): void {
    this.user=this.userService.giveUserData()
    if(localStorage.getItem("isLogged")==="1"){
      alert("Already Logged")
      this.router.navigate(['/user'])
    }
  }

  user=new User()

  userRegister(data:any) {

    this.user.email=data.email
    this.user.name=data.fName+" "+data.lName
    this.user.password=data.password
    this.user.phoneNumber=data.mNumber
		 this.userService.userRegister(this.user).subscribe((data)=>{
      this.user=data
      Swal.fire("Register Successfully !!","","success");
      this.router.navigate(['/login'])
     },err =>{
      alert("User Already Registered")
      this.router.navigate(['/login'])
     }

     )
	}

}
