package todolist.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import todolist.Table.Task;
import todolist.Table.TaskRepository;


import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping
public class MainController extends HttpServlet {

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/task")
    public String tasks(Model model){
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/task")
    public String taskSubmit(@ModelAttribute Task task,  String description) {
        addNewTask(description);
        return "task";
    }


    @GetMapping(path = "/add")
    public @ResponseBody String addNewTask(@RequestParam String description) {

        Task task = new Task();
        task.setDescription(description);
        task.setCreatedAt(getDate());
        task.setArchived(false);

        taskRepository.save(task);

        return "Task saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    @GetMapping(path = "/archived")
    public @ResponseBody
    Iterable<Task> getArchived() {
        return taskRepository.findByIsArchived(true);
    }


    @GetMapping(path = "/notarchived")
    public @ResponseBody
    Iterable<Task> getNotArchived() {
        return taskRepository.findByIsArchived(false);
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

}
