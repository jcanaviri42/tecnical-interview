import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HospitalComponent } from './hospitals/hospital/hospital.component';
import { HospitalsListComponent } from './hospitals/hospitals-list/hospitals-list.component';
import { MenuComponent } from './menu/menu.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MenuComponent,
    HospitalComponent,
    HospitalsListComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'hospitals-front';
}
