import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bmicalculator',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './bmicalculator.component.html',
  styleUrl: './bmicalculator.component.css'
})
export class BmicalculatorComponent {

  weight: number = 0;
  height: number = 0; // Height in centimeters
  age: number = 0; // Age in years
  bmi: number | null = null;
  bmiCategory: string = '';

  calculateBMI() {
    if (this.height > 0 && this.weight > 0) {
      const heightInMeters = this.height / 100;
      this.bmi = this.weight / (heightInMeters * heightInMeters);
    } else {
      this.bmi = null;
    }
    this.bmiCategory = this.getBMICategory(); // Get the BMI category after calculating BMI
  }

  getBMICategory(): string {
    if (this.bmi === null) return '';
    if (this.bmi < 18.5) return 'Underweight';
    if (this.bmi < 25) return 'Normal weight';
    if (this.bmi < 30) return 'Overweight';
    return 'Obese';
  }












}


