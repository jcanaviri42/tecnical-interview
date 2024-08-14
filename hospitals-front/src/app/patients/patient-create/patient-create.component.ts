import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Hospital } from '../../hospitals/Hospital';
import { PatientsService } from '../patients.service';
import { HospitalsService } from '../../hospitals/hospitals.service';
import { Router } from '@angular/router';
import { Patient } from '../Patient';

@Component({
  selector: 'app-patient-create',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './patient-create.component.html',
  styleUrl: './patient-create.component.scss',
})
export class PatientCreateComponent {
  patientForm = new FormGroup({
    name: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    birthDate: new FormControl('', Validators.required),
    hospitalId: new FormControl('', Validators.required),
  });

  hospitals: Hospital[] = [];
  errorMessage: string | null = null;

  constructor(
    private service: PatientsService,
    private hospitalService: HospitalsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.hospitalService.getAllHospitals().subscribe((result) => {
      this.hospitals = result;
    });
  }

  onSubmit() {
    if (this.patientForm.valid) {
      const newPatient: Patient | any = this.patientForm.value;
      this.service.createPatient(newPatient).subscribe({
        next: () => {
          this.router.navigate(['/patients']);
        },
        error: (error) => {
          this.errorMessage = error.error || 'An error occurred';
        },
      });
    }
  }
}
