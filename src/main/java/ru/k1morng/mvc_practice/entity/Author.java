package ru.k1morng.mvc_practice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Set<Book> books = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Author history = (Author) o;
        return getId() != null && Objects.equals(getId(), history.getId());
    }
    public void addAuthorsBook(Book book){
        books.add(book);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

