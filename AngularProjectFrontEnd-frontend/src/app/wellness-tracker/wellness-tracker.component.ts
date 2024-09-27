// wellness-tracker.component.ts
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { GatewayService } from '../services/gateway.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-wellness-tracker',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './wellness-tracker.component.html',
  styleUrls: ['./wellness-tracker.component.css']
})
export class WellnessTrackerComponent implements OnInit {
  height: number = 0;
  weight: number = 0;
  age: number = 0;
  bmi: number | null = null;
  showLogin: boolean = false;
  showSignup: boolean = false;
  email: string = '';
  password: string = '';
  name: string='';

  loginError: boolean = false; // New variable to track login errors
  

  signupData = {
    name: '',
    gender: '',
    age: 0,
    username: '',
    password: '',
    email: '',
    height: 0,
    weight: 0,
    activityLevel: ''
  };

  constructor(private router: Router, private gateway: GatewayService,private http:HttpClient) {}

  ngOnInit(): void {
    console.log('Initialized');
  }

  calculateBMI() {
    if (this.height > 0 && this.weight > 0) {
      const heightInMeters = this.height / 100;
      this.bmi = this.weight / (heightInMeters * heightInMeters);
    } else {
      this.bmi = null;
    }
  }

  getBMICategory(): string {
    if (this.bmi === null) return '';
    if (this.bmi < 18.5) return 'Underweight';
    if (this.bmi < 25) return 'Normal weight';
    if (this.bmi < 30) return 'Overweight';
    return 'Obese';
  }

  toggleLogin() {
    this.showLogin = !this.showLogin;
    this.showSignup = false;
  }

  toggleSignup() {
    this.showSignup = !this.showSignup;
    this.showLogin = false;
  }

  // onSubmit() {
  //   console.log("SUBMITTED");

  //   this.gateway.Login(this.email, this.password).subscribe(
  //     (response: string) => {
  //       console.log('User logged in successfully!', response);
  //       this.router.navigate(['/dashboard']);
  //     },
  //     (error) => {
  //       console.error('Error logging in user', error);
  //     }
  //   );
  // }
  onSubmit() {
    this.gateway.Login(this.email, this.password).subscribe(
      (response: any) => {
        console.log('User logged in successfully!', response);
       
       let returnToken = response;
       const [token, userIdString] = returnToken.split('UID:');

      // Trim any whitespace
      const trimmedToken = token.trim();
      const userId = userIdString.trim(); // Convert to number

      console.log('Token:', trimmedToken);
      console.log('User ID:', userId);




       console.log('Token : ',token);
        localStorage.setItem('authToken',token);
        console.log("token from local Storage",localStorage.getItem("authToken"));

        localStorage.setItem('userId',userId);
        console.log("ID from local Storage",localStorage.getItem("userId"));



       
        
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.error('Error logging in user', error);
        this.loginError = true; 
      }
    );
  }
  

  onSignupSubmit() {
    console.log('Signup submitted:', this.signupData);
  
    this.http.post('http://localhost:8099/user-service/api/users/register',this.signupData).subscribe({
          next: (response) => console.log('User registered successfully', response),
          error: (error) => console.error('Error occurred while registering user', error)
        });
        alert("registered succesfull!! you can login now.")
  }

}

