import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentalhealthtrackerComponent } from './mentalhealthtracker.component';

describe('MentalhealthtrackerComponent', () => {
  let component: MentalhealthtrackerComponent;
  let fixture: ComponentFixture<MentalhealthtrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MentalhealthtrackerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MentalhealthtrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
