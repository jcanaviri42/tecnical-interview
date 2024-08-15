import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Patient } from '../Patient';
import { PatientsService } from '../patients.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-search',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, FormsModule],
  templateUrl: './patient-search.component.html',
  styleUrl: './patient-search.component.scss',
})
export class PatientSearchComponent {
  searchQuery = '';
  startDateControl = new FormControl('');
  endDateControl = new FormControl('');
  patients!: Patient[];
  @Output() hide: EventEmitter<any> = new EventEmitter<boolean>(false);

  constructor(private service: PatientsService) {}

  search() {
    this.service.searchPatients(this.searchQuery).subscribe((patients) => {
      this.patients = patients;
      this.hide.emit(true);
    });
  }

  searchByDates() {
    const startDateValue = this.startDateControl.value;
    const endDateValue = this.endDateControl.value;

    const startDate = startDateValue ? new Date(startDateValue) : null;
    const endDate = endDateValue ? new Date(endDateValue) : null;

    const formattedStartDate = startDate
      ? startDate.toISOString().split('T')[0]
      : '';
    const formattedEndDate = endDate ? endDate.toISOString().split('T')[0] : '';

    this.service
      .searchByDates(formattedStartDate, formattedEndDate)
      .subscribe((patients) => {
        this.patients = patients;
        this.hide.emit(true);
      });
  }
}
