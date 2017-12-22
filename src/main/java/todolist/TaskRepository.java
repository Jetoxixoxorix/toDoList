package todolist;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
