import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Doctor } from './Doctor';
import { Observable } from 'rxjs';

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
}
