import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { PatientsComponent } from './patients/patients.component';
import { SpecialitiesComponent } from './specialities/specialities.component';

export const routes: Routes = [
  {
    path: 'doctors',
    component: DoctorsComponent,
  },
  {
    path: 'patients',
    component: PatientsComponent,
  },
  {
    path: 'specialities',
    component: SpecialitiesComponent,
  },
];
