import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Doctor } from '../../doctors/Doctor';
import { HospitalsService } from '../../hospitals/hospitals.service';
import { Note } from '../Note';
import { NoteService } from '../note.service';

@Component({
  selector: 'app-note-create',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './note-create.component.html',
  styleUrl: './note-create.component.scss',
})
export class NoteCreateComponent implements OnInit {
  @Input() patientId!: number;
  @Input() hospitalId!: number;
  noteForm = new FormGroup({
    description: new FormControl('', Validators.required),
    noteDate: new FormControl('', Validators.required),
    doctorId: new FormControl('', Validators.required),
  });
  doctors: Doctor[] = [];
  errorMessage: string | null = null;
  @Output()
  reload: EventEmitter<boolean> = new EventEmitter<boolean>(false);

  constructor(
    private service: NoteService,
    private hospitalService: HospitalsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.hospitalId)
      this.hospitalService
        .getAllDoctors(this.hospitalId)
        .subscribe((result) => {
          this.doctors = result;
        });
  }

  onSubmit() {
    if (this.noteForm.valid) {
      const newNote: Note | any = {
        ...this.noteForm.value,
        patientId: this.patientId,
      };
      this.service.createNotes(newNote).subscribe({
        next: () => {
          this.reload.emit(true);
          this.router.navigate([this.router.url]);
        },
        error: (error) => {
          this.errorMessage = error.error || 'An error occurred';
        },
      });
    }
  }
}
