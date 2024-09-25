import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page3',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './page3.component.html',
  styleUrls: ['./page3.component.css']  
})
export class Page3Component {
  healthPercentage: number | null = null; // To store health percentage

  constructor(private router: Router) {}

  navigateToFitnessTracker() {
    this.router.navigate(['/fitness-tracker']);
  }

  navigateToBMICalculator() {
    this.router.navigate(['/bmi-calculator']);
  }

  navigateToDietTracker() {
    this.router.navigate(['/diet-tracker']);
  }

  navigateToMentalHealthTracker() {
    this.router.navigate(['/mental-health-tracker']);
  }

  generateReport() {
    // Simulating fetching health percentage from backend
    this.healthPercentage = Math.floor(Math.random() * 100); // Example percentage
    console.log('Generating health report...');
  }
}
