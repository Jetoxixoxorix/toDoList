package todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todolist.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByIsArchivedAndUserId(boolean isArchived, Long userId);
}
