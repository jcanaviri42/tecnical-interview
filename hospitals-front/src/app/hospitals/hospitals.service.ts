import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hospital } from './Hospital';

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
}
