package com.hospitals.patient;

import com.hospitals.hospital.Hospital;
import com.hospitals.note.Note;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Transient
    private String gravatarUrl;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient")
    private List<Note> notes = new ArrayList<>();

    public String getGravatarUrl() {
        if (this.gravatarUrl == null) {
            var hash = DigestUtils.md5Hex(this.name.toLowerCase().trim());
            gravatarUrl = "https://www.gravatar.com/avatar/" + hash + "?s=80&d=identicon";
        }

        return gravatarUrl;
    }

}
