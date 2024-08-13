import { Component, OnInit } from '@angular/core';
import { HospitalsService } from './hospitals.service';
import { Hospital } from './Hospital';

@Component({
  selector: 'app-hospitals',
  standalone: true,
  imports: [],
  templateUrl: './hospitals.component.html',
  styleUrl: './hospitals.component.scss',
})
export class HospitalsComponent implements OnInit {
  hospitals: Hospital[] = [];

  constructor(private service: HospitalsService) {}

  ngOnInit(): void {
    this.service
      .getAllHospitals()
      .subscribe((result) => (this.hospitals = result));
  }
}
