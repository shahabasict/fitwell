import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-diettracker',
  standalone: true,
  imports: [FormsModule , CommonModule],
  templateUrl: './diettracker.component.html',
  styleUrl: './diettracker.component.css'
})
export class DiettrackerComponent {
  foods: { name: string; quantity: number }[] = [
    { name: '', quantity: 0 }
  ];
  caloriesGained: number = 0;
  quotes: string[] = ['Healthy Eating Quote 1', 'Healthy Eating Quote 2', 'Healthy Eating Quote 3'];
  showDetails: boolean = false;

  addFood() {
    this.foods.push({ name: '', quantity: 0 });
  }

  submitFoods() {
    // Logic to submit the food items
    this.showDetails = true;
  }






  
}


