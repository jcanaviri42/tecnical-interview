import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';

@Component({
  selector: 'app-hospitals',
  standalone: true,
  imports: [ReactiveFormsModule],
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

  constructor(
    private route: ActivatedRoute,
    private service: HospitalsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.hospitalId = params['id'];

      if (this.hospitalId)
        this.service
          .getHospitalById(this.hospitalId)
          .subscribe((result) => {
            this.hospital = result;
            this.hospitalForm.patchValue(this.hospital)
          });
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
