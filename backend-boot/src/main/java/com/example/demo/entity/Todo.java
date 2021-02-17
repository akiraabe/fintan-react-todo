package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="todo")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String text;
    private Boolean completed;
    private String userId;

    public Todo(String text, String userId) {
        this.text = text;
        this.completed = Boolean.FALSE;
        this.userId = userId; //FIXME
    }

    public void changeStatus() {
        this.completed = !this.completed;
    }
}
