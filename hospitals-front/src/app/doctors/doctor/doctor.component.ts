import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Hospital } from '../../hospitals/Hospital';
import { HospitalsService } from '../../hospitals/hospitals.service';
import { Doctor } from '../Doctor';
import { DoctorsService } from '../doctors.service';
import { Patient } from '../../patients/Patient';

@Component({
  selector: 'app-doctors',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './doctors.component.html',
  styleUrl: './doctors.component.scss',
})
export class DoctorComponent implements OnInit {
  doctorId!: number;
  doctor!: Doctor;
  doctorForm = new FormGroup({
    name: new FormControl(),
    lastName: new FormControl(),
    hospitalId: new FormControl(),
  });
  hospitals!: Hospital[];
  patients: Patient[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: DoctorsService,
    private router: Router,
    private hospitalService: HospitalsService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.doctorId = params['id'];

      if (this.doctorId) {
        this.service.getDoctorById(this.doctorId).subscribe((result) => {
          this.doctor = result;
          this.doctorForm.patchValue(result);
        });

        this.service.getAllPatients(this.doctorId).subscribe((patients) => {
          this.patients = patients;
        });

        this.hospitalService
          .getAllHospitals()
          .subscribe((result) => (this.hospitals = result));
      }
    });
  }

  onUpdate() {
    const updatedDoctor = { ...this.doctor, ...this.doctorForm.value };
    this.service
      .updateDoctor(this.doctorId, updatedDoctor)
      .subscribe(() => this.router.navigate(['/doctors']));
  }
}
