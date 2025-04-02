package com.emobile.springtodo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс, представляющий задачу (ToDoItem) в системе.
 */
@Entity
@Table(name = "todo_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
