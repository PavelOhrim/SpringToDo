package com.emobile.springtodo.repository;

import com.emobile.springtodo.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Репозиторий для работы с задачами ToDo.
 *
 * @author PavelOkhrimchuk
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem, Long> {

    /**
     * Получить все задачи с поддержкой пагинации.
     *
     * @param limit  Количество задач для выборки.
     * @param offset Смещение для выборки.
     * @return Список задач.
     */
    @Query(value = "SELECT * FROM todo_items t ORDER BY t.created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<ToDoItem> findAll(@Param("limit") int limit, @Param("offset") int offset);

}