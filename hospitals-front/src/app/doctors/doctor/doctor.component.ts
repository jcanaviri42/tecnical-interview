import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DoctorsService } from '../doctors.service';
import { Doctor } from '../Doctor';

@Component({
  selector: 'app-doctors',
  standalone: true,
  imports: [],
  templateUrl: './doctors.component.html',
  styleUrl: './doctors.component.scss',
})
export class DoctorComponent implements OnInit {
  doctorId!: number;
  doctor!: Doctor;

  constructor(private route: ActivatedRoute, private service: DoctorsService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.doctorId = params['id'];

      if (this.doctorId)
        this.service
          .getDoctorById(this.doctorId)
          .subscribe((result) => (this.doctor = result));
    });
  }
}
