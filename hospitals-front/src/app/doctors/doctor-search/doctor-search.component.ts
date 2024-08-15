import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Doctor } from '../Doctor';
import { DoctorsService } from '../doctors.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-doctor-search',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './doctor-search.component.html',
  styleUrl: './doctor-search.component.scss',
})
export class DoctorSearchComponent {
  searchQuery = '';
  startDateControl = new FormControl('');
  endDateControl = new FormControl('');
  doctors!: Doctor[];
  @Output() hide: EventEmitter<any> = new EventEmitter<boolean>(false);

  constructor(private service: DoctorsService) {}

  search() {
    this.service
      .searchDoctor(this.searchQuery)
      .subscribe((doctors) => {
        this.doctors = doctors;
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
      .subscribe((doctors) => {
        this.doctors = doctors;
        this.hide.emit(true);
      });
  }
}
