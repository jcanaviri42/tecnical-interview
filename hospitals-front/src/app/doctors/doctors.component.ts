import { Component, OnInit } from '@angular/core';
import { DoctorsService } from './doctors.service';
import { Doctor } from './Doctor';

@Component({
  selector: 'app-doctors',
  standalone: true,
  imports: [],
  templateUrl: './doctors.component.html',
  styleUrl: './doctors.component.scss',
})
export class DoctorsComponent implements OnInit {
  doctors: Doctor[] = [];

  constructor(private service: DoctorsService) {}

  ngOnInit(): void {
    this.service.getAllDoctors().subscribe((result) => (this.doctors = result));
  }
}
