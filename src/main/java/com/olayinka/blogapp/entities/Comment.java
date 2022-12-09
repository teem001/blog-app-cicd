package com.olayinka.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter



public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String nameUser;
    @Column(unique = true)

   private String localhostAdress;

    @PreUpdate
    public void setLocalhostAdress(){
        try {
            this.localhostAdress = (""+InetAddress.getLocalHost()).trim();
        }catch (UnknownHostException e){
            throw new RuntimeException("local Address does not Exist");
        }
    }


   @Column(length = 500)
   private String comment;

   @ManyToOne()
   @JoinColumn(name ="post_id", referencedColumnName = "id")
   private Post post;


}
