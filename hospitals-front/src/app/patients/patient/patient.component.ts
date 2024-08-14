import { Component } from '@angular/core';
import { Patient } from '../Patient';
import { ActivatedRoute } from '@angular/router';
import { PatientsService } from '../patients.service';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss',
})
export class PatientsComponent {
  patientId!: number;
  patient!: Patient;

  constructor(
    private route: ActivatedRoute,
    private service: PatientsService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.patientId = params['id'];

      if (this.patientId)
        this.service
          .getPatientById(this.patientId)
          .subscribe((result) => (this.patient = result));
    });
  }
}
