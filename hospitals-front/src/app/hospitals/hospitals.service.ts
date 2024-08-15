import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hospital } from './Hospital';
import { Doctor } from '../doctors/Doctor';
import { Patient } from '../patients/Patient';

@Injectable({
  providedIn: 'root',
})
export class HospitalsService {
  private BASE_URL: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllHospitals(): Observable<Hospital[]> {
    const hospitalsUrl = `${this.BASE_URL}/hospitals`;
    return this.http.get<Hospital[]>(hospitalsUrl);
  }

  getHospitalById(id: number): Observable<Hospital> {
    const hospitalsUrl = `${this.BASE_URL}/hospitals/${id}`;
    return this.http.get<Hospital>(hospitalsUrl);
  }

  updateHospital(id: number, update: Hospital): Observable<Hospital> {
    const hospitalsUrl = `${this.BASE_URL}/hospitals/${id}`;
    return this.http.put<Hospital>(hospitalsUrl, update);
  }

  createHospital(hospital: Hospital): Observable<Hospital> {
    const hospitalsUrl = `${this.BASE_URL}/hospitals`;
    return this.http.post<Hospital>(hospitalsUrl, hospital);
  }

  searchHospitals(query: string): Observable<Hospital[]> {
    return this.http.get<Hospital[]>(
      `${this.BASE_URL}/hospitals/search?q=${query}`
    );
  }

  searchByDates(startDate: string, endDate: string): Observable<Hospital[]> {
    return this.http.get<Hospital[]>(
      `${this.BASE_URL}/hospitals/search?startDate=${startDate}&endDate=${endDate}`
    );
  }

  getAllDoctors(id: number): Observable<Doctor[]> {
    return this.http.get<Hospital[]>(
      `${this.BASE_URL}/hospitals/${id}/doctors`
    );
  }

  getAllPatients(id: number): Observable<Patient[]> {
    return this.http.get<Patient[]>(
      `${this.BASE_URL}/hospitals/${id}/patients`
    );
  }
}
