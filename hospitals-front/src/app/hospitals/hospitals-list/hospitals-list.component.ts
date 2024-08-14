import { Component } from '@angular/core';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-hospitals-list',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './hospitals-list.component.html',
  styleUrl: './hospitals-list.component.scss',

})
export class HospitalsListComponent {
  hospitals: Hospital[] = [];

  constructor(private service: HospitalsService) {}

  ngOnInit(): void {
    this.service
      .getAllHospitals()
      .subscribe((result) => (this.hospitals = result));
  }
}
