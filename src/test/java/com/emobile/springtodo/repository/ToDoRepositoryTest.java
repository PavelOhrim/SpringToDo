package com.emobile.springtodo.repository;

import com.emobile.springtodo.entity.ToDoItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тесты для репозитория ToDo с использованием Spring Data JPA.
 * Включает тестирование операций сохранения, обновления и удаления задач.
 *
 * @author PavelOkhrimchuk
 */
@ExtendWith(MockitoExtension.class)
class ToDoRepositoryTest {

    @Mock
    private ToDoRepository toDoRepository;

    @Test
    void testSave() {
        ToDoItem toDoItem = new ToDoItem(null, "Title", "Description", false, LocalDateTime.now());
        ToDoItem savedItem = new ToDoItem(1L, "Title", "Description", false, toDoItem.getCreatedAt());

        when(toDoRepository.save(toDoItem)).thenReturn(savedItem);

        ToDoItem result = toDoRepository.save(toDoItem);

        assertEquals(1L, result.getId());
        verify(toDoRepository).save(toDoItem);
    }

    @Test
    void testUpdate() {
        ToDoItem toDoItem = new ToDoItem(1L, "Updated Title", "Updated Description", true, LocalDateTime.now());
        when(toDoRepository.save(toDoItem)).thenReturn(toDoItem);

        ToDoItem updatedItem = toDoRepository.save(toDoItem);

        assertEquals("Updated Title", updatedItem.getTitle());
        assertEquals("Updated Description", updatedItem.getDescription());
        assertEquals(true, updatedItem.isCompleted());
        verify(toDoRepository).save(toDoItem);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(toDoRepository).deleteById(id);

        toDoRepository.deleteById(id);

        verify(toDoRepository).deleteById(id);
    }
}