import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-fitnesstracker',
  standalone: true,
  imports: [CommonModule , FormsModule ],
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
  caloriesBurned: number = 0;
  quotes: string[] = ['Motivational Quote 1', 'Motivational Quote 2', 'Motivational Quote 3'];
  showDetails: boolean = false;

  submitExercises() {
    // Logic to submit the exercises
    this.showDetails = true;
  }
  
}

