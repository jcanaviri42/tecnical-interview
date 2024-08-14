import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Speciality } from './Speciality';

@Injectable({
  providedIn: 'root',
})
export class SpecialitiesService {
  private BASE_URL: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllSpecialities(): Observable<Speciality[]> {
    const specialitiesUrl = `${this.BASE_URL}/specialities`;
    return this.http.get<Speciality[]>(specialitiesUrl);
  }

  getSpecialityById(id: number): Observable<Speciality> {
    const specialitiesUrl = `${this.BASE_URL}/specialities/${id}`;
    return this.http.get<Speciality>(specialitiesUrl);
  }
}
