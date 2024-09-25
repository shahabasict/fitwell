import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WellnessTrackerComponent } from './wellness-tracker.component';

describe('WellnessTrackerComponent', () => {
  let component: WellnessTrackerComponent;
  let fixture: ComponentFixture<WellnessTrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WellnessTrackerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WellnessTrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
