import { Routes } from '@angular/router';
import { DoctorsComponent } from './doctors/doctors.component';
import { HospitalComponent } from './hospitals/hospital/hospital.component';
import { PatientsComponent } from './patients/patients.component';
import { SpecialitiesComponent } from './specialities/specialities.component';
import { HospitalsListComponent } from './hospitals/hospitals-list/hospitals-list.component';

export const routes: Routes = [
  {
    path: '',
    component: HospitalsListComponent,
  },
  {
    path: 'hospitals/:id',
    component: HospitalComponent,
  },
  {
    path: 'doctors',
    component: DoctorsComponent,
  },
  {
    path: 'doctors/:id',
    component: DoctorsComponent,
  },
  {
    path: 'patients',
    component: PatientsComponent,
  },
  {
    path: 'patients/:id',
    component: PatientsComponent,
  },
  {
    path: 'specialities',
    component: SpecialitiesComponent,
  },
  {
    path: 'specialities/:id',
    component: SpecialitiesComponent,
  },
];
