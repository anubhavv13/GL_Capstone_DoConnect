// import { Component, OnInit } from '@angular/core';
// import { UserService } from './../user.service';
// import { Question } from './../question';
// import { Router } from '@angular/router';
// import { User } from '../user';
// import { AdminService } from './../admin.service';
// import { Admin } from '../admin';

// @Component({
//   selector: 'app-nav-bar',
//   templateUrl: './nav-bar.component.html',
//   styleUrls: ['./nav-bar.component.css']
// })
// export class NavBarComponent implements OnInit {

//   constructor(private userService:UserService, private router:Router
//     , private adminService:AdminService) { }

//   ngOnInit(): void {
//     this.user=this.userService.giveUserData()
//     this.admin=this.adminService.giveAdminData()

//   }
//   isSearched:boolean=false

//   questions:Question[] | undefined

//   user=new User()
//   admin = new Admin()
//     getValue(values:string){
//     if(values !=='')
//     this.userService.searchQuestion(values).subscribe((data)=>{
//       console.log(data)
//       this.questions=data
//       if(data.length==0){
//         alert("No Question Found")
//       }else{
//       this.isSearched=true}
//     })

//   }

//   sendQuestionToGetAnswer(id:number){
//     console.log(id)
//     this.userService.getQuestionId(id)
//     this.router.navigate(['/get-answer'])
//     this.isSearched=false

//   }


// }

import { Component, OnInit } from '@angular/core';
import { UserService } from './../user.service';
import { Question } from './../question';
import { Router } from '@angular/router';
import { User } from '../user';
import { AdminService } from './../admin.service';
import { Admin } from '../admin';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  public isLogged: any;
  public userName: any;
  constructor(private userService: UserService, private router: Router
    , private adminService: AdminService) { }

  ngOnInit(): void {
    this.isLogged = localStorage.getItem("isLogged");
    this.userName = localStorage.getItem("userName");
    this.user = this.userService.giveUserData()
    this.admin = this.adminService.giveAdminData()

  }
  public isSearched: boolean = false

  questions: Question[] | undefined

  user = new User()
  admin = new Admin()


  getValue(values: string) {

    if (this.isLogged == '1') {
     
      if (values !== '') {
        this.userService.searchValue = values;
        this.userService.searchQuestion(values).subscribe((data) => {
          console.log(data)
          this.questions = data
          if (data.length == 0) {
            Swal.fire(
              'No Question Found!',
              '',
              'error'
            )
           
          }
          else{
            this.isSearched=true;
         }
        })
      }
      else {
        Swal.fire(
          'Please enter a value!',
          '',
          'error'
        )
     
      }
    }
    else {
      Swal.fire(
        'Please Login to search',
        '',
        'error'
      )
     
      this.router.navigate(["/login"])
    }
  }

  userLogout() {
    localStorage.clear();
    Swal.fire("You have been logout !!","","warning");
    this.router.navigate([""])

  }

  sendQuestionToGetAnswer(id: number) {
    console.log(id)
    this.userService.getQuestionId(id)
    this.router.navigate(['/get-answer'])
    this.isSearched = false

  }


}
