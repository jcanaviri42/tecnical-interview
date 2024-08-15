import { Component, Input } from '@angular/core';
import { Note } from '../Note';

@Component({
  selector: 'app-note-list',
  standalone: true,
  imports: [],
  templateUrl: './note-list.component.html',
  styleUrl: './note-list.component.scss',
})
export class NoteListComponent {
  @Input() notes: Note[] = [];
}
