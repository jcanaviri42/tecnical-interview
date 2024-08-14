import { Component } from '@angular/core';
import { Speciality } from '../Speciality';
import { ActivatedRoute } from '@angular/router';
import { SpecialitiesService } from '../specialities.service';

@Component({
  selector: 'app-specialities',
  standalone: true,
  imports: [],
  templateUrl: './speciality.component.html',
  styleUrl: './speciality.component.scss',
})
export class SpecialitiesComponent {
  specialityId!: number;
  speciality!: Speciality;

  constructor(
    private route: ActivatedRoute,
    private service: SpecialitiesService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.specialityId = params['id'];

      if (this.specialityId)
        this.service
          .getSpecialityById(this.specialityId)
          .subscribe((result) => (this.speciality = result));
    });
  }
}
