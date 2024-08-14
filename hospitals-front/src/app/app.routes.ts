import { Routes } from '@angular/router';
import { DoctorCreateComponent } from './doctors/doctor-create/doctor-create.component';
import { DoctorComponent } from './doctors/doctor/doctor.component';
import { DoctorsListComponent } from './doctors/doctors-list/doctors-list.component';
import { HospitalCreateComponent } from './hospitals/hospital-create/hospital-create.component';
import { HospitalComponent } from './hospitals/hospital/hospital.component';
import { HospitalsListComponent } from './hospitals/hospitals-list/hospitals-list.component';
import { PatientCreateComponent } from './patients/patient-create/patient-create.component';
import { PatientListComponent } from './patients/patient-list/patient-list.component';
import { PatientsComponent } from './patients/patient/patient.component';
import { SpecialitiesCreateComponent } from './specialities/specialities-create/specialities-create.component';
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
    path: 'hospital-create',
    component: HospitalCreateComponent,
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
    path: 'doctor-create',
    component: DoctorCreateComponent,
  },
  {
    path: 'patients',
    component: PatientListComponent,
  },
  {
    path: 'patient-create',
    component: PatientCreateComponent,
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
    path: 'speciality-create',
    component: SpecialitiesCreateComponent,
  },
  {
    path: 'specialities/:id',
    component: SpecialitiesComponent,
  },
];
