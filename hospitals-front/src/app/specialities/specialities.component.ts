import { Component, OnInit } from '@angular/core';
import { SpecialitiesService } from './specialities.service';
import { Speciality } from './Speciality';

@Component({
  selector: 'app-specialities',
  standalone: true,
  imports: [],
  templateUrl: './specialities.component.html',
  styleUrl: './specialities.component.scss',
})
export class SpecialitiesComponent implements OnInit {
  specialities: Speciality[] = [];

  constructor(private service: SpecialitiesService) {}

  ngOnInit(): void {
    this.service
      .getAllSpecialities()
      .subscribe((result) => (this.specialities = result));
  }
}
