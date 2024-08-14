import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../Patient';
import { PatientsService } from '../patients.service';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss',
})
export class PatientsComponent {
  patientId!: number;
  patient!: Patient;
  patientForm = new FormGroup({
    name: new FormControl(),
    lastName: new FormControl(),
    address: new FormControl(),
    birthDate: new FormControl(),
  });

  constructor(
    private route: ActivatedRoute,
    private service: PatientsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.patientId = params['id'];

      if (this.patientId)
        this.service.getPatientById(this.patientId).subscribe((result) => {
          this.patient = result;
          this.patientForm.patchValue(result);
        });
    });
  }

  onUpdate() {
    const updatedPatient = { ...this.patient, ...this.patientForm.value };
    this.service
      .updatePatient(this.patientId, updatedPatient)
      .subscribe(() => this.router.navigate(['/patients']));
  }
}
