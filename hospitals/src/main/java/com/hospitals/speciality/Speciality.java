package com.hospitals.speciality;

import com.hospitals.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "specialities")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Transient
    private String gravatarUrl;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String createdBy;

    private String updatedBy;

    @ManyToMany(mappedBy = "specialities")
    private Set<Doctor> doctors = new HashSet<>();

    public String getGravatarUrl() {
        if (this.gravatarUrl == null) {
            var hash = DigestUtils.md5Hex(this.name.toLowerCase().trim());
            gravatarUrl = "https://www.gravatar.com/avatar/" + hash + "?s=80&d=retro";
        }

        return gravatarUrl;
    }
}
