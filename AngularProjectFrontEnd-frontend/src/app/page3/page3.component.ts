import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-page3',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './page3.component.html',
  styleUrls: ['./page3.component.css']  
})
export class Page3Component {
  healthPercentage: number | null = null; // To store health percentage

  constructor(private router: Router,private http: HttpClient) {}

  

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

  async generateReport(): Promise<number> {
    // this.healthPercentage=78;
    const apiUrl = "http://localhost:8099/user-service/api/overallscores";
    const userId = String(localStorage.getItem("userId"));
    
    if (!userId) {
      console.error('User ID not found in local storage');
      throw new Error('User ID not found');
    }

    const overallScore = {
      user: {
        id: parseInt(userId, 10)
      }
      // Note: We don't need to set other fields as the backend will calculate them
    };

    console.log("OverallScore initial:",overallScore);

    try {
      const response = await lastValueFrom(this.http.post<number>(apiUrl, overallScore));
      console.log('Overall score calculated successfully:', response);
      this.healthPercentage = response;
      return response;
    } catch (error) {
      console.error('Error calculating overall score:', error);
      throw error;
    }
  }

  logout() {
    console.log("page 3 logout is working");
    localStorage.setItem('authToken',"");
    // Implement your logout functionality here
  }




}
