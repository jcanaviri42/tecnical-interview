import { Component, OnInit } from '@angular/core';
import { PatientsService } from './patients.service';
import { Patient } from './Patient';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [],
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.scss',
})
export class PatientsComponent implements OnInit {
  patients: Patient[] = [];

  constructor(private service: PatientsService) {}

  ngOnInit(): void {
    this.service
      .getAllPatients()
      .subscribe((result) => (this.patients = result));
  }
}
