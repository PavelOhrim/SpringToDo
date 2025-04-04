package com.emobile.springtodo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс, представляющий задачу (ToDoItem) в системе.
 * Содержит информацию о задаче, такую как идентификатор, название, описание, статус выполнения и дата создания.
 *
 * @author PavelOkhrimchuk
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_items")
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
