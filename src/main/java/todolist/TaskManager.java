package todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.model.Task;
import todolist.model.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskManager {

    @Autowired
    public TaskRepository taskRepository;

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void addNewTask(String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setCreatedAt(getDate());
        task.setArchived(false);
        taskRepository.save(task);
    }

    public void editTask(String description, Long id){
        Task task = taskRepository.findOne(id);
        task.setDescription(description);
        task.setUpdatedAt(getDate());
        taskRepository.save(task);
    }

    public void makeDone(Long id){
        Task task = taskRepository.findOne(id);
        if (task.getDoneAt() == null) {
            task.setDoneAt(getDate());
        }
        taskRepository.save(task);
    }

    public void archiveTask(Long id){
        Task task = taskRepository.findOne(id);
        task.setArchived(!task.isArchived());
        taskRepository.save(task);
    }

    public List<Task> getArchived(boolean archived){
        List<Task> tasks = taskRepository.findByIsArchived(archived);
        return tasks;
    }

    public Task getById(Long id){
        Task task = taskRepository.findOne(id);
        return task;
    }
}
