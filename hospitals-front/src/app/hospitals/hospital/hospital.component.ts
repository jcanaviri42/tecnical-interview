import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Doctor } from '../../doctors/Doctor';
import { Patient } from '../../patients/Patient';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';

@Component({
  selector: 'app-hospitals',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './hospital.component.html',
  styleUrl: './hospital.component.scss',
})
export class HospitalComponent implements OnInit {
  hospitalId!: number;
  hospital!: Hospital;
  hospitalForm = new FormGroup({
    name: new FormControl(),
    phone: new FormControl(),
    email: new FormControl(),
  });

  doctors: Doctor[] = [];
  patients: Patient[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: HospitalsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.hospitalId = params['id'];

      if (this.hospitalId) {
        this.service.getHospitalById(this.hospitalId).subscribe((result) => {
          this.hospital = result;
          this.hospitalForm.patchValue(this.hospital);
        });

        this.service.getAllDoctors(this.hospitalId).subscribe((result) => {
          this.doctors = result;
        });

        this.service.getAllPatients(this.hospitalId).subscribe((result) => {
          this.patients = result;
        });
      }
    });
  }

  onUpdate() {
    const updatedHospital = { ...this.hospital, ...this.hospitalForm.value };
    this.service
      .updateHospital(this.hospitalId, updatedHospital)
      .subscribe((_) => {
        this.router.navigate(['']);
      });
  }
}
