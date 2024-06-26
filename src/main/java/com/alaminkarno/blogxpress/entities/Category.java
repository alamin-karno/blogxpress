package com.alaminkarno.blogxpress.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "name",length = 100,nullable = false)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
