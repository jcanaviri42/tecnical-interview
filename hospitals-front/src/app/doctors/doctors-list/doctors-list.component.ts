import { Component } from '@angular/core';
import { Doctor } from '../Doctor';
import { DoctorsService } from '../doctors.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-doctors-list',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './doctors-list.component.html',
  styleUrl: './doctors-list.component.scss',
})
export class DoctorsListComponent {
  doctors: Doctor[] = [];

  constructor(private service: DoctorsService) {}

  ngOnInit(): void {
    this.service.getAllDoctors().subscribe((result) => (this.doctors = result));
  }
}
