import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Hospital } from '../Hospital';
import { HospitalsService } from '../hospitals.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-hospital-search',
  standalone: true,
  imports: [FormsModule, RouterModule, ReactiveFormsModule],
  templateUrl: './hospital-search.component.html',
  styleUrl: './hospital-search.component.scss',
})
export class HospitalSearchComponent {
  searchQuery = '';
  startDateControl = new FormControl('');
  endDateControl = new FormControl('');
  hospitals!: Hospital[];
  @Output() hide: EventEmitter<any> = new EventEmitter<boolean>(false);

  constructor(private service: HospitalsService) {}

  search() {
    this.service.searchHospitals(this.searchQuery).subscribe((hospitals) => {
      this.hospitals = hospitals;
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
      .subscribe((hospitals) => {
        this.hospitals = hospitals;
        this.hide.emit(true);
      });
  }
}
