package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import todolist.model.User;
import todolist.service.ITaskManager;
import todolist.service.IUserManager;
import todolist.service.TaskManager;
import todolist.model.Task;
import todolist.service.UserManager;

@Controller
@RequestMapping
public class MainController {

    ITaskManager taskManager;
    IUserManager userManager;

    @Autowired
    public MainController(ITaskManager taskManager, IUserManager userManager){
        this.taskManager = taskManager;
        this.userManager = userManager;
    }

    @GetMapping({"/task", "/"})
    public String tasks(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/task")
    public String taskSubmit(@ModelAttribute("task") Task task, String description) {
        if(UserManager.user == null)
            return "mustlogin";
        taskManager.addNewTask(description, userManager.getUserId());
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
        if(UserManager.user == null)
            return "mustlogin";
        model.addAttribute("archived", taskManager.getArchived(true,userManager.getUserId()));
        return "archived";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        if(UserManager.user == null)
            return "mustlogin";
        model.addAttribute("tasks", taskManager.getArchived(false, userManager.getUserId()));
        return "tasks";
    }
}
