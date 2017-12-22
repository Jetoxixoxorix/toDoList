package todolist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        task.setDate(dateFormat.format(date));
        task.setStatus(false);

        taskRepository.save(task);

        return "Task saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
