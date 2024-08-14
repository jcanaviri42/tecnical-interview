import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from './Patient';

@Injectable({
  providedIn: 'root',
})
export class PatientsService {
  private BASE_URL: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllPatients(): Observable<Patient[]> {
    const patientUrl = `${this.BASE_URL}/patients`;
    return this.http.get<Patient[]>(patientUrl);
  }

  getPatientById(id: number): Observable<Patient> {
    const patientUrl = `${this.BASE_URL}/patients/${id}`;
    return this.http.get<Patient>(patientUrl);
  }

  updatePatient(id: number, patient: Patient): Observable<Patient> {
    const patientUrl = `${this.BASE_URL}/patients/${id}`;
    return this.http.put<Patient>(patientUrl, patient);
  }
}
