package com.olayinka.blogapp.entities;

import lombok.*;


import javax.persistence.*;

@Entity
@Table (name="admin")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
//    @OneToMany()
//    private List<Post> posts;





//    public void addPost(Post post){
//        if(this.posts==null)
//            posts = new ArrayList<>();
//        posts.add(post);
//    }



}
