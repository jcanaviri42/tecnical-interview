import { Routes } from '@angular/router';
import { HospitalsComponent } from './hospitals/hospitals.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { PatientsComponent } from './patients/patients.component';
import { SpecialitiesComponent } from './specialities/specialities.component';

export const routes: Routes = [
  {
    path: 'home',
    component: HospitalsComponent
  },
  {
    path: 'doctors',
    component: DoctorsComponent
  },
  {
    path: 'patients',
    component: PatientsComponent
  },
  {
    path: 'specialities',
    component: SpecialitiesComponent
  },
];
