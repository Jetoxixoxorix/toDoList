package todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import todolist.controller.MainController;
import todolist.model.Task;
import todolist.model.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskManager {

    @Autowired
    public TaskRepository taskRepository;

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Task archiveTask(Task task){
        task.setArchived(true);
        return task;
    }

    public Task addNewTask(String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setCreatedAt(getDate());
        task.setArchived(false);
        return task;
    }

    public Task editTask(Task task, String description){
        task.setDescription(description);
        task.setUpdatedAt(getDate());
        return task;
    }

    public Task makeDone(Task task){
        if (task.getDoneAt() == null) {
            task.setDoneAt(getDate());
        }
        return task;
    }
}
