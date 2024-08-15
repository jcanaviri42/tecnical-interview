import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Doctor } from '../../doctors/Doctor';
import { Patient } from '../Patient';
import { PatientsService } from '../patients.service';
import { NoteCreateComponent } from '../../notes/note-create/note-create.component';
import { NoteListComponent } from '../../notes/note-list/note-list.component';
import { Note } from '../../notes/Note';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterModule,
    NoteCreateComponent,
    NoteListComponent,
  ],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss',
})
export class PatientsComponent {
  patientId!: number;
  patient!: Patient;
  patientForm = new FormGroup({
    name: new FormControl(),
    lastName: new FormControl(),
    address: new FormControl(),
    birthDate: new FormControl(),
  });
  doctors: Doctor[] = [];
  notes: Note[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: PatientsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.patientId = params['id'];

      if (this.patientId) {
        this.service.getAllNotes(this.patientId).subscribe((result) => {
          this.notes = result;
        });

        this.service.getPatientById(this.patientId).subscribe((result) => {
          this.patient = result;
          this.patientForm.patchValue(result);
        });

        this.service.getAllDoctors(this.patientId).subscribe((result) => {
          this.doctors = result;
        });
      }
    });
  }

  onUpdate() {
    const updatedPatient = { ...this.patient, ...this.patientForm.value };
    this.service
      .updatePatient(this.patientId, updatedPatient)
      .subscribe(() => this.router.navigate(['/patients']));
  }

  onReload(reload: boolean) {
    if (reload)
      this.service.getAllNotes(this.patientId).subscribe((result) => {
        this.notes = result;
      });
  }
}
