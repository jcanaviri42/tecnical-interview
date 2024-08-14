import { Component, OnInit } from '@angular/core';
import {
  FormArray,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Hospital } from '../../hospitals/Hospital';
import { Speciality } from '../../specialities/Speciality';
import { DoctorsService } from '../doctors.service';
import { HospitalsService } from '../../hospitals/hospitals.service';
import { SpecialitiesService } from '../../specialities/specialities.service';
import { Router } from '@angular/router';
import { Doctor } from '../Doctor';

@Component({
  selector: 'app-doctor-create',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './doctor-create.component.html',
  styleUrl: './doctor-create.component.scss',
})
export class DoctorCreateComponent implements OnInit {
  doctorForm = new FormGroup({
    name: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    specialitiesIds: new FormArray([], Validators.required),
    hospitalId: new FormControl('', Validators.required),
  });

  hospitals: Hospital[] = [];
  specialities: Speciality[] = [];
  errorMessage: string | null = null;

  constructor(
    private service: DoctorsService,
    private hospitalService: HospitalsService,
    private specialitiesService: SpecialitiesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.hospitalService.getAllHospitals().subscribe((result) => {
      this.hospitals = result;
    });

    this.specialitiesService.getAllSpecialities().subscribe((result) => {
      this.specialities = result;
      this.specialities.forEach((speciality) => this.addSpeciality(speciality));
    });
  }

  get specialitiesControls() {
    return this.doctorForm.get('specialitiesIds') as FormArray;
  }

  addSpeciality(speciality: Speciality) {
    const control = new FormControl(false);
    this.specialitiesControls.push(control);
  }

  onSubmit() {
    const selectedSpecialities = this.doctorForm?.value?.specialitiesIds?.map(
      (checked, index) => (checked ? this.specialities[index].id : null)
    );

    if (this.doctorForm.valid) {
      const newDoctor: Doctor | any = this.doctorForm.value;
      newDoctor.specialitiesIds = selectedSpecialities;
      this.service.createDoctor(newDoctor).subscribe({
        next: () => {
          this.router.navigate(['/doctors']);
        },
        error: (error) => {
          this.errorMessage = error.error || 'An error occurred';
        },
      });
    }
  }
}
