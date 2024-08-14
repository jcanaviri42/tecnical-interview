import { Routes } from '@angular/router';
import { DoctorComponent } from './doctors/doctor/doctor.component';
import { DoctorsListComponent } from './doctors/doctors-list/doctors-list.component';
import { HospitalComponent } from './hospitals/hospital/hospital.component';
import { HospitalsListComponent } from './hospitals/hospitals-list/hospitals-list.component';
import { PatientListComponent } from './patients/patient-list/patient-list.component';
import { PatientsComponent } from './patients/patient/patient.component';
import { SpecialitiesListComponent } from './specialities/specialities-list/specialities-list.component';
import { SpecialitiesComponent } from './specialities/speciality/speciality.component';

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
    component: DoctorsListComponent,
  },
  {
    path: 'doctors/:id',
    component: DoctorComponent,
  },
  {
    path: 'patients',
    component: PatientListComponent,
  },
  {
    path: 'patients/:id',
    component: PatientsComponent,
  },
  {
    path: 'specialities',
    component: SpecialitiesListComponent,
  },
  {
    path: 'specialities/:id',
    component: SpecialitiesComponent,
  },
];
