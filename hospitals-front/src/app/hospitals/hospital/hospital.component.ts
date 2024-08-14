import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HospitalsService } from '../hospitals.service';
import { Hospital } from '../Hospital';

@Component({
  selector: 'app-hospitals',
  standalone: true,
  imports: [],
  templateUrl: './hospital.component.html',
  styleUrl: './hospital.component.scss',
})
export class HospitalComponent implements OnInit {
  hospitalId!: number;
  hospital!: Hospital;

  constructor(
    private route: ActivatedRoute,
    private service: HospitalsService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.hospitalId = params['id'];

      if (this.hospitalId)
        this.service
          .getHospitalById(this.hospitalId)
          .subscribe((result) => (this.hospital = result));
    });
  }
}
