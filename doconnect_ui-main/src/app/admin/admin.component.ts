// import { Component, OnInit } from '@angular/core';
// import { AdminService } from './../admin.service';
// import { Question } from './../question';
// import { Answer } from './../answer';
// import { User } from './../user';
// import { Admin } from '../admin';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-admin',
//   templateUrl: './admin.component.html',
//   styleUrls: ['./admin.component.css']
// })
// export class AdminComponent implements OnInit {

//   constructor(private adminService:AdminService,private router:Router) { }

//   ngOnInit(): void {
//    this.admin= this.adminService.giveAdminData()
//    console.log("admin data is"+this.admin.id)
//    if(this.admin.id==0){
//     alert("Login Required")
//     this.router.navigate(['/admin-login'])
//    }
//   }

//   response:any
//   admin = new Admin()
//   answer:Answer | undefined
//   question = new Question()
//   questions:Question[] | undefined
//   answers:Answer[] | undefined
//   user = new User()
//   users: User[] | undefined
//   answerThere:boolean=false
//   questionThere:boolean=false
//   userThere:boolean=false


//   adminLogout(adminId:number){

//      this.adminService.adminLogout(adminId).subscribe((data)=>{
//       this.response=(data)
//      },err =>{
//       console.log("error called"+err)
//       this.admin=new Admin()
//       this.adminService.sendAdminData(this.admin)
//       this.router.navigate(['/admin-login'])
//      }

//      )

//    }

//    getUnApprovedQuestions(){
//      this.adminService.getUnApprovedQuestions().subscribe((data)=>{
//       console.log(data)
//       this.questions=data
//       if(data.length!==0){
//         this.questionThere=true
//       }
//       else
//       alert("No Questions to approve")
//      })

//   }
//   getUnApprovedAnswers(){
//      this.adminService.getUnApprovedAnswers().subscribe((data)=>{
//       console.log(data)
//       this.answers=data
//       if(data.length!==0){
//         this.answerThere=true
//       }
//       else
//       alert("No answers to approve")
//      })

//   }
//   approveQuestion(questionId:number){
//      this.adminService.approveQuestion(questionId).subscribe((data)=>{
//       this.question=data
//       alert("Question Approved")
//       this.router.navigate(['/admin'])
//      })

//   }

//   approveAnswer(answerId:number){
//      this.adminService.approveAnswer(answerId).subscribe((data)=>{
//       this.answer=data
//       alert("Answer Approved")
//       this.router.navigate(['/admin'])
//      })
//   }
//   deleteQuestion(questionId:number){
//     this.adminService.deleteQuestion(questionId).subscribe((data)=>{
//       this.response=data
//       alert("Question Removed")
//       this.router.navigate(['/admin'])
//     })

//   }
//   deleteAnswer(answerId:number){
//     this.adminService.deleteAnswer(answerId).subscribe((data)=>{
//       this.response=data
//       alert("Answer Removed")
//       this.router.navigate(['/admin'])
//     })

//   }
//   getUser(email:string) {
//     this.adminService.getUser(email).subscribe((data)=>{
//       this.user=data
//     })
//   }

//   addAdmin(){
//     this.router.navigate(['/admin-register'])
//   }

//   getAllUsers(){
//     this.adminService.getAllUsers().subscribe((data)=>{
//       console.log(data)
//       this.users=data
//       if(data.length!==0){
//         this.userThere=true
//       }
//       else
//       alert("No users present")
//     })
//   }

// }

import { Component, OnInit } from '@angular/core';
import { AdminService } from './../admin.service';
import { Question } from './../question';
import { Answer } from './../answer';
import { User } from './../user';
import { Admin } from '../admin';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  response: any
  admin = new Admin()
  answer: Answer | undefined
  question = new Question()
  questions: Question[] | undefined
  answers: Answer[] | undefined
  user = new User()
  users: User[] | undefined
  public isLogged:any;
  public userName:any;
  answerThere: boolean = false
  questionThere: boolean = false
  userThere: boolean = false

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.isLogged=localStorage.getItem("isLogged");
    this.userName=localStorage.getItem("userName");
    if (this.isLogged != '1') {
      this.router.navigate(["/admin-login"])
    }
  }

  adminLogout(adminId: number) {

    this.adminService.adminLogout(adminId).subscribe((data) => {
      this.response = (data)
    }, err => {
      console.log("error called" + err)
      this.admin = new Admin()
      this.adminService.sendAdminData(this.admin)
      localStorage.removeItem("isLogged")
      Swal.fire(
        'Good job!',
        'You Logged Out Successfully',
        'success'
      )
      this.router.navigate(['/admin-login'])
    }

    )

  }

  getUnApprovedQuestions() {
    this.adminService.getUnApprovedQuestions().subscribe((data) => {
      console.log(data)
      this.questions = data
      if (data.length !== 0) {
        this.questionThere = true
      }
      else{
        Swal.fire(
          'No Questions to approve',
          '',
          'error'
        )
      }
        // alert("No Questions to approve")
    })

  }
  getUnApprovedAnswers() {
    this.adminService.getUnApprovedAnswers().subscribe((data) => {
      console.log(data)
      this.answers = data
      if (data.length !== 0) {
        this.answerThere = true
      }
      else{
        Swal.fire(
          'No answers to approve',
          '',
          'error'
        )
      }
      
        // alert("No answers to approve")
    })

  }
  approveQuestion(questionId: number) {
    this.adminService.approveQuestion(questionId).subscribe((data) => {
      this.question = data
      Swal.fire(
        'Good job!',
        'Question Approved',
        'success'
      )
      // alert("Question Approved")
      this.router.navigate(['/admin'])
    })

  }

  approveAnswer(answerId: number) {
    this.adminService.approveAnswer(answerId).subscribe((data) => {
      this.answer = data
      Swal.fire(
        'Good job!',
        'Answer Approved',
        'success'
      )
      
      this.router.navigate(['/admin'])
    })
  }
  deleteQuestion(questionId: number) {
    this.adminService.deleteQuestion(questionId).subscribe((data) => {
      this.response = data
      Swal.fire(
        'Good job!',
        'Question Removed',
        'success'
      )
      // alert("Question Removed")
      this.router.navigate(['/admin'])
    })

  }
  deleteAnswer(answerId: number) {
    this.adminService.deleteAnswer(answerId).subscribe((data) => {
      this.response = data
      Swal.fire(
        'Good job!',
        'Answer Removed',
        'success'
      )
      // alert
      // alert("Answer Removed")
      this.router.navigate(['/admin'])
    })

  }
  getUser(email: string) {
    this.adminService.getUser(email).subscribe((data) => {
      this.user = data
    })
  }

  addAdmin() {
    this.router.navigate(['/admin-register'])
  }

  getAllUsers() {
    this.adminService.getAllUsers().subscribe((data) => {
      console.log(data)
      this.users = data
      if (data.length !== 0) {
        this.userThere = true
      }
      else
      Swal.fire(
        'Error',
        '"No users present',
        'error'
      )
        // alert("No users present")
    })
  }

}
