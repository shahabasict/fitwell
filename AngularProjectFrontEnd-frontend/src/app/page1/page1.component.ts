import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-page1',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.css']
})
export class Page1Component {
  height: number = 0;
  weight: number = 0;
  age: number = 0;
  bmi: number | null = null;
  showLogin: boolean = false;
  email: string = '';
  password: string = '';

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
  }

  onSubmit() {
    // Handle login logic here
    console.log('Login submitted:', this.email, this.password);
  }
}
