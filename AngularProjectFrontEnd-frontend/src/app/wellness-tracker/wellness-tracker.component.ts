// wellness-tracker.component.ts
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { GatewayService } from '../services/gateway.service';


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

  signupData = {
    name: '',
    gender: '',
    age: 0,
    username: '',
    password: '',
    retypePassword: '',
    email: '',
    height: 0,
    weight: 0,
    activityLevel: ''
  };

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
  //   // Handle login logic here
  //   console.log('Login submitted:', this.email, this.password);
  // }

  // onSignupSubmit() {
  //   // Handle signup logic here
  //   console.log('Signup submitted:', this.signupData);
  // }


  //edited code


  constructor(private router: Router , private gateway:GatewayService) {}
  ngOnInit(): void {
      console.log('Intializrd')
      this.gateway.Login();
  }

  onSubmit() {

    console.log("SBMITTED")
    this.gateway.Login().subscribe(
      (response:string) => {
          console.log('Loging function')
          console.log('User registered successfully!', response);
      },
      (error) => {
          console.error('Error registering user', error);
      }
  );


    // Dummy authentication logic
    if (this.email === 'user@example.com' && this.password === 'password') {
      console.log('Login successful');
      this.router.navigate(['/dashboard']);
    } else {
      console.log('Login failed');
      // Handle login failure (e.g., show error message)
    }
  }

  onSignupSubmit() {
    // Dummy signup logic
    console.log('Signup submitted:', this.signupData);
    // Assuming signup is always successful for this example
    this.router.navigate(['/dashboard']);
  }
  
}