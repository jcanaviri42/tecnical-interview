import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SpecialitiesService } from '../specialities.service';
import { Speciality } from '../Speciality';

@Component({
  selector: 'app-specialities',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './speciality.component.html',
  styleUrl: './speciality.component.scss',
})
export class SpecialitiesComponent {
  specialityId!: number;
  speciality!: Speciality;
  specialityForm = new FormGroup({
    name: new FormControl(),
    description: new FormControl(),
  });

  constructor(
    private route: ActivatedRoute,
    private service: SpecialitiesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.specialityId = params['id'];

      if (this.specialityId)
        this.service
          .getSpecialityById(this.specialityId)
          .subscribe((result) => {
            this.speciality = result;
            this.specialityForm.patchValue(this.speciality);
          });
    });
  }

  onUpdate() {
    const updatedSpeciality = {
      ...this.speciality,
      ...this.specialityForm.value,
    };
    this.service
      .updateSpeciality(this.specialityId, updatedSpeciality)
      .subscribe(() => this.router.navigate(['/specialities']));
  }
}
