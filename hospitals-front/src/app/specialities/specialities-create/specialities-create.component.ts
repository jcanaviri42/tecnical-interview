import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { SpecialitiesService } from '../specialities.service';
import { Speciality } from '../Speciality';

@Component({
  selector: 'app-specialities-create',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './specialities-create.component.html',
  styleUrl: './specialities-create.component.scss',
})
export class SpecialitiesCreateComponent {
  specialityForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
  });
  errorMessage: string | null = null;

  constructor(private service: SpecialitiesService, private router: Router) {}

  onSubmit() {
    if (this.specialityForm.valid) {
      const newSpeciality: Speciality | any = this.specialityForm.value;
      this.service.createSpeciality(newSpeciality).subscribe({
        next: () => {
          this.router.navigate(['/doctors']);
        },
        error: (error) => {
          console.error('Error creating doctor:', error);
          this.errorMessage = error.error || 'An error occurred';
        }
      });
    }
  }
}
