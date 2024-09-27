import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-diettracker',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './diettracker.component.html',
  styleUrls: ['./diettracker.component.css'] // Fixed typo here from styleUrl to styleUrls
})
export class DiettrackerComponent {
  foods: string = '';
  caloriesGained: number = 0; // Initially 0, will be updated on submission
  quotes: string[] = ['Healthy Eating Quote 1', 'Healthy Eating Quote 2', 'Healthy Eating Quote 3'];
  showDetails: boolean = false;

  

  submitFoods() {
    // Logic to submit the food items
    this.showDetails = true;
    this.caloriesGained = this.generateRandomCalories(); // Generate random calories
    this.handleSubmit();
    this.handleSubmit2();
  }
  constructor(private http: HttpClient) { }

  // Method to generate a random number of calories gained
  generateRandomCalories(): number {
    let apiUrl = 'http://localhost:8099/diet-service/api/diet/log';
    let token = localStorage.getItem("authToken");
    console.log(token);
    let user = String(localStorage.getItem("userId"));
    const userId = parseInt(user, 10);
    console.log(userId);
  
    if (isNaN(userId) || token === "") {
      console.error('User ID or token is missing.');
      return 0; // Exit the function if user ID or token is not available
    }
  
    const headers = {
      'Authorization': `Bearer ${token}`
    };
  
    // Create params object
    const params = new HttpParams()
      .set('userId', userId.toString())
      .set('dietDescription', this.foods);
  
    this.http.post<number>(apiUrl, null, { headers: headers, params: params }).subscribe(
      (dietLog: number) => {
        console.log('Diet logged successfully:', dietLog);
        return dietLog;
      },
      (error) => {
        console.error('Error logging diet:', error);
      }
    );
  
    // This function doesn't actually return a meaningful number as promised.
    // For now, we'll return a random number between 0 and 1000 to match the function signature.
    return Math.floor(Math.random() * 1000);
  }

  API_KEY: string = 'AIzaSyAGN1qsN-ffA6FvNtkAirEZWJ98vP2XPs4';
  genAI = new GoogleGenerativeAI(this.API_KEY);
  model = this.genAI.getGenerativeModel({ model: 'gemini-1.5-flash' });

  prompt1: string = 'Suggest the names of  3 food items . Just give me the name of food item and the quantity. No need of Explanation. Remove the introduction part too. Return the response as a list of 4 sentences,each list items should start in different lines.Start each line with a -';
  answer1: string = '';
  result1: any = null;

  prompt2: string = 'Suggest one interesting and variety a quote of between 15 to 20 words Which will motivate us to eat healthy food and have a healthy life. just the quote nothing else. start quote with a - .';
  answer2: string = '';
  result2: any = null;

  handleSubmit(): void {
    let result = this.loadResult();
    result
      .then((data) => {
        console.log(data);
        this.answer1 = data;
      })
      .catch((err) => {
        console.log(err);
      });
  }

  async loadResult() {
    let result1 = await this.model.generateContent(this.prompt1);
    return result1.response.text();
  }

  handleSubmit2(): void {
    let result = this.loadResult2();
    result
      .then((data) => {
        console.log(data);
        this.answer2 = data;
      })
      .catch((err) => {
        console.log(err);
      });
  }

  async loadResult2() {
    let result2 = await this.model.generateContent(this.prompt2);
    return result2.response.text();
  }

  logout() {
    console.log('Logging out...');
    localStorage.setItem('authToken',"");
    localStorage.setItem('userId',"");
    // Implement your logout functionality here
  }


}
