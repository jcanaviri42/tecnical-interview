import { Component } from '@angular/core';
import { Doctor } from '../Doctor';
import { DoctorsService } from '../doctors.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DoctorSearchComponent } from "../doctor-search/doctor-search.component";

@Component({
  selector: 'app-doctors-list',
  standalone: true,
  imports: [RouterModule, CommonModule, DoctorSearchComponent],
  templateUrl: './doctors-list.component.html',
  styleUrl: './doctors-list.component.scss',
})
export class DoctorsListComponent {
  doctors: Doctor[] = [];
  hide = false;

  constructor(private service: DoctorsService) {}

  ngOnInit(): void {
    this.service.getAllDoctors().subscribe((result) => (this.doctors = result));
  }

  onHideList(hide: boolean) {
    this.hide = hide;
  }
}
