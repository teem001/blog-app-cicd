package com.olayinka.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_rating")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Post post;
    @Column(nullable = false)
    private Long numberOfOneStar=0L;
    @Column(nullable = false)
    private Long numberOfTwoStar=0L;
    @Column(nullable = false)
    private Long numberOFThreeStar=0L;
    @Column(nullable = false)
    private Long numberOfFourStar=0L;
    @Column(nullable = false)
    private Long numberOfFiveStar=0L;
}
