import { Component } from '@angular/core';
import { Patient } from '../Patient';
import { PatientsService } from '../patients.service';
import { RouterModule } from '@angular/router';
import { PatientSearchComponent } from '../patient-search/patient-search.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [RouterModule, PatientSearchComponent, CommonModule],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss',
})
export class PatientListComponent {
  patients: Patient[] = [];
  hide = false;

  constructor(private service: PatientsService) {}

  ngOnInit(): void {
    this.service
      .getAllPatients()
      .subscribe((result) => (this.patients = result));
  }

  onHideList(hide: boolean) {
    this.hide = hide;
  }
}
