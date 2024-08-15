import { Component } from '@angular/core';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';
import { RouterModule } from '@angular/router';
import { HospitalSearchComponent } from '../hospital-search/hospital-search.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hospitals-list',
  standalone: true,
  imports: [RouterModule, HospitalSearchComponent, CommonModule],
  templateUrl: './hospitals-list.component.html',
  styleUrl: './hospitals-list.component.scss',
})
export class HospitalsListComponent {
  hospitals: Hospital[] = [];
  hide = false;

  constructor(private service: HospitalsService) {}

  ngOnInit(): void {
    this.service
      .getAllHospitals()
      .subscribe((result) => (this.hospitals = result));
  }

  onHideList(hide: boolean) {
    this.hide = hide;
  }
}
