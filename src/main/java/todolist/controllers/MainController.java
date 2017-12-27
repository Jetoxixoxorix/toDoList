package todolist.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import todolist.model.Task;
import todolist.model.TaskRepository;


import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping
public class MainController {

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/task")
    public String tasks(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/task")
    public void taskSubmit(@ModelAttribute("task") Task task, String description) {
        addNewTask(description);
    }

    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", taskRepository.findOne(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editTaskSubmit(String description, @PathVariable("id") Long id) {
        Task task = taskRepository.findOne(id);
        task.setDescription(description);
        task.setUpdatedAt(getDate());
        taskRepository.save(task);
        return "notarchived";
    }


    @GetMapping(path = "/add")
    public @ResponseBody
    void addNewTask(@RequestParam String description) {

        Task task = new Task();
        task.setDescription(description);
        task.setCreatedAt(getDate());
        task.setArchived(false);


        taskRepository.save(task);
    }

    @GetMapping("/all")
    public String getAllTasks(Model model) {
        model.addAttribute("all", taskRepository.findAll());
        return "all";
    }


    @GetMapping("/archived")
    public String getArchived(Model model) {
        model.addAttribute("archived", taskRepository.findByIsArchived(true));
        return "archived";
    }


    @GetMapping("/notarchived")
    public String getNotArchived(Model model) {
        model.addAttribute("notArchived", taskRepository.findByIsArchived(false));
        return "notArchived";
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

}
