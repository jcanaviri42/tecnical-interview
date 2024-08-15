import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Doctor } from './Doctor';
import { Observable } from 'rxjs';
import { Patient } from '../patients/Patient';

@Injectable({
  providedIn: 'root',
})
export class DoctorsService {
  private BASE_URL: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllDoctors(): Observable<Doctor[]> {
    const doctorsUrl = `${this.BASE_URL}/doctors`;
    return this.http.get<Doctor[]>(doctorsUrl);
  }

  getDoctorById(id: number): Observable<Doctor> {
    const doctorsUrl = `${this.BASE_URL}/doctors/${id}`;
    return this.http.get<Doctor>(doctorsUrl);
  }

  updateDoctor(id: number, doctor: Doctor): Observable<Doctor> {
    const doctorsUrl = `${this.BASE_URL}/doctors/${id}`;
    return this.http.put<Doctor>(doctorsUrl, doctor);
  }

  createDoctor(doctor: Doctor): Observable<Doctor> {
    const doctorsUrl = `${this.BASE_URL}/doctors`;
    console.log('doctor =', doctor);

    return this.http.post<Doctor>(doctorsUrl, doctor);
  }

  searchDoctor(name: string): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(`${this.BASE_URL}/doctors/search?name=${name}`);
  }

  searchByDates(startDate: string, endDate: string): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(
      `${this.BASE_URL}/doctors/search?startDate=${startDate}&endDate=${endDate}`
    );
  }

  getAllPatients(id: number): Observable<Patient[]> {
    return this.http.get<Patient[]>(
      `${this.BASE_URL}/doctors/${id}/patients`
    );
  }
}
