package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import todolist.model.User;
import todolist.service.TaskManager;
import todolist.model.Task;

@Controller
@RequestMapping
public class MainController {

    TaskManager taskManager;
    UserController userController;

    @Autowired
    public MainController(TaskManager taskManager, UserController userController){
        this.taskManager = taskManager;
        this.userController = userController;
    }

    @GetMapping({"/task", "/"})
    public String tasks(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/task")
    public String taskSubmit(@ModelAttribute("task") Task task, String description, @ModelAttribute("userDataLogin") User user) {
        taskManager.addNewTask(description, user);
        return "task";
    }

    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", taskManager.getById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editTaskSubmit(String description, @PathVariable("id") Long id, Model model) {
        taskManager.editTask(description, id);
        return getTasks(model);
    }

    @GetMapping("/archive/{id}")
    public String archiveTask(@PathVariable("id") Long id, Model model) {
        taskManager.archiveTask(id);
        return getTasks(model);
    }

    @GetMapping("/unarchive/{id}")
    public String unarchiveTask(@PathVariable("id") Long id, Model model) {
        taskManager.archiveTask(id);
        return getArchived(model);
    }

    @GetMapping("/done/{id}")
    public String done(@PathVariable("id") Long id, Model model) {
        taskManager.makeDone(id);
        return getTasks(model);
    }

    @GetMapping("/archived")
    public String getArchived(Model model) {
        model.addAttribute("archived", taskManager.getArchived(true));
        return "archived";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskManager.getArchived(false));
        return "tasks";
    }
}
