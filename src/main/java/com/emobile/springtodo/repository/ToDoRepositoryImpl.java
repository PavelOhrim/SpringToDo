package com.emobile.springtodo.repository;

import com.emobile.springtodo.entity.ToDoItem;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Реализация репозитория для работы с задачами ToDo с использованием JdbcTemplate.
 * Реализует методы поиска, сохранения, обновления и удаления задач в базе данных.
 *
 * @author PavelOkhrimchuk
 */
@Repository
@RequiredArgsConstructor
public class ToDoRepositoryImpl implements ToDoRepository {

    private final SessionFactory sessionFactory;



    /**
     * Получить все задачи с поддержкой пагинации.
     *
     * @param limit  Количество задач для выборки.
     * @param offset Смещение для выборки.
     * @return Список задач.
     */
    @Override
    public List<ToDoItem> findAll(int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ToDoItem ORDER BY createdAt DESC", ToDoItem.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .list();
        }
    }

    /**
     * Найти задачу по ID.
     * @param id Идентификатор задачи.
     * @return Опциональная задача.
     */
    @Override
    public Optional<ToDoItem> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(ToDoItem.class, id));
        }
    }

    /**
     * Сохранить новую задачу в базе данных.
     * @param toDoItem Задача для сохранения.
     */
    @Override
    public void save(ToDoItem toDoItem) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            if (toDoItem.getId() == null) {
                session.persist(toDoItem);
            } else {
                session.merge(toDoItem);
            }
            session.getTransaction().commit();
        }
    }


    /**
     * Обновить существующую задачу.
     * @param toDoItem Задача с обновленными данными.
     */
    @Override
    public void update(ToDoItem toDoItem) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(toDoItem);
            session.getTransaction().commit();
        }
    }

    /**
     * Удалить задачу по ID.
     * @param id Идентификатор задачи.
     */
    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ToDoItem item = session.get(ToDoItem.class, id);
            if (item != null) {
                session.remove(item);
            }
            session.getTransaction().commit();
        }
    }
}
