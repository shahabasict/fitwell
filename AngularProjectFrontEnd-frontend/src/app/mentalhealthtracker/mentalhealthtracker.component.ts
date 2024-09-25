import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mentalhealthtracker',
  standalone: true,
  imports: [FormsModule , CommonModule],
  templateUrl: './mentalhealthtracker.component.html',
  styleUrl: './mentalhealthtracker.component.css'
})
export class MentalhealthtrackerComponent {
  userInput: string = '';
  showFeedback: boolean = false;

  submitDayEntry() {
    this.showFeedback = true;
  }

}
