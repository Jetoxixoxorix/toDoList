package todolist.service;

import todolist.model.Task;
import java.util.List;

public interface ITaskManager {
    void addNewTask(String description, Long userId);
    void editTask(String description, Long id);
    void makeDone(Long id);
    void archiveTask(Long id);
    List<Task> getArchived(boolean archived, Long userId);
    Task getById(Long id);
}
