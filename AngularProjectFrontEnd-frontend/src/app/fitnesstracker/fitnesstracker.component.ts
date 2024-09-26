import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GoogleGenerativeAI } from '@google/generative-ai';

@Component({
  selector: 'app-fitnesstracker',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './fitnesstracker.component.html',
  styleUrl: './fitnesstracker.component.css'
})
export class FitnesstrackerComponent {
  exerciseOptions: string[] = [
    'Walking', 'Football', 'Running', 'Swimming', 'Cycling',
    'Yoga', 'Jumping Rope', 'Basketball', 'Tennis', 'Boxing',
    'Dancing', 'Martial Arts', 'Golf', 'Aerobics',
    'Pushups', 'Volleyball', 'Badminton', 'Table Tennis',
    'Gymnastics', 'Cricket', 'Body Building'
  ];

  exercises: { name: string; count: number }[] = [
    { name: '', count: 0 }
  ];
  caloriesBurned: number = 0; // Initialize caloriesBurned
  quotes: string[] = ['Motivational Quote 1', 'Motivational Quote 2', 'Motivational Quote 3'];
  showDetails: boolean = false;

  submitExercises() {
    // Generate a random number for calories burned between 50 and 500 (adjust as needed)
    this.caloriesBurned = Math.floor(Math.random() * (500 - 50 + 1)) + 50;
    this.showDetails = true;
    this.handleSubmit();
    this.handleSubmit2();
  }

  // AI related code remains the same...
  API_KEY: string = 'AIzaSyAGN1qsN-ffA6FvNtkAirEZWJ98vP2XPs4';
  genAI = new GoogleGenerativeAI(this.API_KEY);
  model = this.genAI.getGenerativeModel({ model: 'gemini-1.5-flash' });

  prompt1: string = 'Suggest the name 3 exercise activity and how much time I should do it. Just give me the activity to do and the duration. No need of Explanation. Remove the introduction part too. Return the response as a list of 4 sentences, each list items should start in different lines. Start each line with a -';
  
  answer1: string = '';

  result1: any = null;

  prompt2: string = 'Suggest one interesting and variety a quote of between 15 to 20 words Which will motivate us to do exercise and have a healthy life. just the quote nothing else. start quote with a - .';

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
}
