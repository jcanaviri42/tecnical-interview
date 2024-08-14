import { Component } from '@angular/core';
import { Speciality } from '../Speciality';
import { SpecialitiesService } from '../specialities.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-specialities-list',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './specialities-list.component.html',
  styleUrl: './specialities-list.component.scss',
})
export class SpecialitiesListComponent {
  specialities: Speciality[] = [];

  constructor(private service: SpecialitiesService) {}

  ngOnInit(): void {
    this.service
      .getAllSpecialities()
      .subscribe((result) => (this.specialities = result));
  }
}
