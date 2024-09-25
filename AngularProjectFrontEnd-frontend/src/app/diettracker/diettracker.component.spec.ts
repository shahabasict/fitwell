import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiettrackerComponent } from './diettracker.component';

describe('DiettrackerComponent', () => {
  let component: DiettrackerComponent;
  let fixture: ComponentFixture<DiettrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiettrackerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiettrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
