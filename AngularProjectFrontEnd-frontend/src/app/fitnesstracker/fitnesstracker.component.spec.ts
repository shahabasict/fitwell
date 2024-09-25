import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FitnesstrackerComponent } from './fitnesstracker.component';

describe('FitnesstrackerComponent', () => {
  let component: FitnesstrackerComponent;
  let fixture: ComponentFixture<FitnesstrackerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FitnesstrackerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FitnesstrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
