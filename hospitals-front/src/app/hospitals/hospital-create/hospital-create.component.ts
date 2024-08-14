import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';

@Component({
  selector: 'app-hospital-create',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './hospital-create.component.html',
  styleUrl: './hospital-create.component.scss',
})
export class HospitalCreateComponent {
  hospitalForm = new FormGroup({
    name: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
  });
  errorMessage: string | null = null;

  constructor(private service: HospitalsService, private router: Router) {}

  onSubmit() {
    if (this.hospitalForm.valid) {
      const newHospital: Hospital | any = this.hospitalForm.value;

      this.service.createHospital(newHospital).subscribe({
        next: () => {
          this.router.navigate(['/']);
        },
        error: (error) => {
          this.errorMessage = error.error || 'An error occurred';
        },
      });
    }
  }
}
