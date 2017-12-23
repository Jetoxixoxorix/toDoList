package todolist.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import todolist.Table.Task;
import todolist.Table.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/task")
public class MainController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewTask(@RequestParam String description){
        
        Task task = new Task();
        task.setDescription(description);
        task.setCreatedAt(getDate());
        task.setArchived(false);

        taskRepository.save(task);

        return "Task saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @GetMapping(path = "/archived")
    public @ResponseBody Iterable<Task> getArchived(){
        return null;
    }

    @GetMapping(path = "/notarchived")
    public @ResponseBody Iterable<Task> getNotArchived(){
        return null;
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }
}
