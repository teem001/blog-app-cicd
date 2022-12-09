package com.olayinka.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olayinka.blogapp.enums.Ratings;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String imagePath;
    @Column(length = 1500)
    private String information;
    @ManyToOne
    private Admin admin;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ratings ratings = Ratings.NO_RATING;
    @Column( nullable = false)
    private Integer noOfReview=0;
    @OneToOne (orphanRemoval = true, mappedBy = "post", fetch = FetchType.LAZY)
    @JsonIgnore
    private Rating rating;





}
