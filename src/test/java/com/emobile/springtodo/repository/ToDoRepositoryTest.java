package com.emobile.springtodo.repository;

import com.emobile.springtodo.entity.ToDoItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Тесты для репозитория To Do, работающего с Hibernate.
 */
@ExtendWith(MockitoExtension.class)
class ToDoRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private ToDoRepositoryImpl toDoRepository;

    @Test
    void testSave() {
        ToDoItem toDoItem = new ToDoItem(null, "Title", "Description", false, LocalDateTime.now());


        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);

        toDoRepository.save(toDoItem);

        verify(session).persist(toDoItem);
        verify(transaction).commit();
        verify(session).close();
    }


    @Test
    void testUpdate() {
        ToDoItem toDoItem = new ToDoItem(1L, "Updated Title", "Updated Description", true, LocalDateTime.now());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);

        toDoRepository.update(toDoItem);

        verify(session).merge(toDoItem);
        verify(transaction).commit();
        verify(session).close();
    }


    @Test
    void testFindById() {
        Long id = 1L;
        ToDoItem toDoItem = new ToDoItem(id, "Title", "Description", false, LocalDateTime.now());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ToDoItem.class, id)).thenReturn(toDoItem);

        Optional<ToDoItem> result = toDoRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(toDoItem, result.get());
        verify(session).close();
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        ToDoItem toDoItem = new ToDoItem(id, "Title", "Description", false, LocalDateTime.now());

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(ToDoItem.class, id)).thenReturn(toDoItem);
        when(session.getTransaction()).thenReturn(transaction);

        toDoRepository.deleteById(id);

        verify(session).remove(toDoItem);
        verify(transaction).commit();
        verify(session).close();
    }

}
