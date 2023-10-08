package com.mitocode.fullstack.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFile;
    @Column(length = 50, nullable = false)
    private String filename;
    @Column(length = 20, nullable = false)
    private String fileType;
    @Column(name = "content", nullable = false)
    private byte[] value;

}
