// import { Routes } from '@angular/router';
// import { WellnessTrackerComponent } from './wellness-tracker/wellness-tracker.component';
// import { Page3Component } from './page3/page3.component';
// import { FitnessTrackerComponent } from './fitness-tracker/fitness-tracker.component';


// export const routes: Routes = [

//     { path: '', component: WellnessTrackerComponent },
//     { path: 'fitness-tracker', component: FitnessTrackerComponent },
//   { path: 'dashboard', component: Page3Component },
//   { path: '**', redirectTo: '' }

// ];

import { Routes } from '@angular/router';
import { WellnessTrackerComponent } from './wellness-tracker/wellness-tracker.component';
import { Page3Component } from './page3/page3.component';
import { FitnesstrackerComponent } from './fitnesstracker/fitnesstracker.component';
import { BmicalculatorComponent } from './bmicalculator/bmicalculator.component';
import { DiettrackerComponent } from './diettracker/diettracker.component';
import { MentalhealthtrackerComponent } from './mentalhealthtracker/mentalhealthtracker.component';


export const routes: Routes = [
  { path: '', component: WellnessTrackerComponent },
  { path: 'dashboard', component: Page3Component },
  { path: 'fitness-tracker', component: FitnesstrackerComponent },
  { path: 'bmi-calculator', component: BmicalculatorComponent },
  { path: 'diet-tracker', component: DiettrackerComponent },
  { path: 'mental-health-tracker', component: MentalhealthtrackerComponent },


  { path: '**', redirectTo: '' }
];

