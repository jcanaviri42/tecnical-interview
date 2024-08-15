import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Note } from './Note';

@Injectable({
  providedIn: 'root',
})
export class NoteService {
  private BASE_URL: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllNotes(): Observable<Note[]> {
    const notesUrl = `${this.BASE_URL}/notes`;
    return this.http.get<Note[]>(notesUrl);
  }

  getNoteById(id: number): Observable<Note> {
    const NotesUrl = `${this.BASE_URL}/notes/${id}`;
    return this.http.get<Note>(NotesUrl);
  }

  updateNote(id: number, note: Note): Observable<Note> {
    const notesUrl = `${this.BASE_URL}/notes/${id}`;
    return this.http.put<Note>(notesUrl, note);
  }

  createNotes(notes: Note): Observable<Note> {
    const notesUrl = `${this.BASE_URL}/notes`;
    return this.http.post<Note>(notesUrl, notes);
  }
}
