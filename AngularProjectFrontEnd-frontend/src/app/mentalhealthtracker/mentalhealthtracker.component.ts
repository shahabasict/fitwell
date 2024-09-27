import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { AuthInterceptor } from '../interceptors/intercept.interceptor';

@Component({
  selector: 'app-mentalhealthtracker',
  standalone: true,
  imports: [FormsModule, CommonModule ],
  templateUrl: './mentalhealthtracker.component.html',
  styleUrls: ['./mentalhealthtracker.component.css']
})
export class MentalhealthtrackerComponent {
  userInput: string = '';
  showFeedback: boolean = false;

  API_KEY: string = 'AIzaSyAGN1qsN-ffA6FvNtkAirEZWJ98vP2XPs4';
  genAI = new GoogleGenerativeAI(this.API_KEY);
  model = this.genAI.getGenerativeModel({ model: 'gemini-1.5-flash' });

  prompt1: string = '';
  answer1: string = '';

  prompt2: string = '';
  answer2: string = '';

  prompt3: string = '';
  answer3: string = '';

  submitDayEntry() {
    this.prompt1 = `Here is my Journal for today: ${this.userInput}. Analyze this journal and summarize how I am feeling today in 3-5 words. Only give the text answer as a result, nothing else.`;
    this.prompt2 = `Here is my Journal for today: ${this.userInput}. Analyze this journal and suggest one activity I can do to improve my mood. Only give the activity as a result, nothing else.`;
    this.prompt3 = `Here is my Journal for today: ${this.userInput}. Analyze this journal and rate my mental health on a score of 0-25. Only give the score as a result, nothing else.`;

    this.showFeedback = true;
    this.handleSubmit();
    this.handleSubmit2();
    this.handleSubmit3();
    backendCall();
    
  }

  handleSubmit(): void {
    this.loadResult(this.prompt1).then((data) => {
      console.log(data);
      this.answer1 = data;
    }).catch((err) => {
      console.log(err);
    });
  }

  async loadResult(prompt: string) {
    let result = await this.model.generateContent(prompt);
    return result.response.text();
  }

  handleSubmit2(): void {
    this.loadResult(this.prompt2).then((data) => {
      console.log(data);
      this.answer2 = data;
    }).catch((err) => {
      console.log(err);
    });
  }

  handleSubmit3(): void {
    this.loadResult(this.prompt3).then((data) => {
      console.log(data);
      this.answer3 = data;
    }).catch((err) => {
      console.log(err);
    });
  }

  logout() {
    console.log('Logging out...');
    localStorage.setItem('authToken',"");
    localStorage.setItem('userId',"");
    // Implement your logout functionality here

  }


}
function backendCall() {
  //  let apiUrl = 'http://localhost:8099/diet-service/api/diet/log';
  // // let cha:string = localStorage.getItem()

  // let userId = JSON["parse"](localStorage.getItem("user") || '{}');

  // console.log(userId);

  // // console.log('Signup submitted:', this.signupData);
  
  // //   this.http.post(apiUrl,this.signupData).subscribe({
  // //         next: (response) => console.log('User registered successfully', response),
  // //         error: (error) => console.error('Error occurred while registering user', error)
  // //       });
  // //       alert("registered succesfull!! you can login now.")

  }



