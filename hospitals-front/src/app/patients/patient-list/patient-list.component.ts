import { Component } from '@angular/core';
import { Patient } from '../Patient';
import { PatientsService } from '../patients.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss',
})
export class PatientListComponent {
  patients: Patient[] = [];

  constructor(private service: PatientsService) {}

  ngOnInit(): void {
    this.service
      .getAllPatients()
      .subscribe((result) => (this.patients = result));
  }
}
