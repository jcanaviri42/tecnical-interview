import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hospitals',
  standalone: true,
  imports: [],
  templateUrl: './hospital.component.html',
  styleUrl: './hospital.component.scss',
})
export class HospitalComponent implements OnInit {
  hospitalId: number | undefined;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.hospitalId = this.route.snapshot.params['id'];
  }
}
