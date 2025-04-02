package com.emobile.springtodo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс, представляющий задачу (ToDoItem) в системе.
 * Содержит информацию о задаче, такую как идентификатор, название, описание, статус выполнения и дата создания.
 *
 * @author PavelOkhrimchuk
 */
@Entity
@Table(name = "todo_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {

    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название задачи.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Описание задачи.
     */
    private String description;

    /**
     * Статус выполнения задачи.
     */
    private boolean completed;

    /**
     * Дата и время создания задачи.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
