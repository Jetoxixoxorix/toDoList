package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import todolist.TaskManager;
import todolist.model.Task;
import todolist.model.TaskRepository;

@Controller
@RequestMapping
public class MainController {

    TaskManager taskManager = new TaskManager();

/*    @Autowired
    public MainController(TaskManager taskManager){

    }*/

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String tasks(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/task")
    public String taskSubmit(@ModelAttribute("task") Task task, String description) {
        taskRepository.save(taskManager.addNewTask(description));
        return "task";
    }


    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", taskRepository.findOne(id));
        return "edit";
    }



    @PostMapping("/edit/{id}")
    public String editTaskSubmit(String description, @PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findOne(id);
        taskManager.editTask(task, description);
        taskRepository.save(task);
        return getTasks(model);
    }


    @PostMapping("/archive/{id}")
    public String archiveTask(@PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findOne(id);
        taskManager.archiveTask(task);
        taskRepository.save(task);
        return getTasks(model);
    }


/*    @PostMapping("/done/{id}") //to przerobic czy inna metoda jeszcze?
    public String makeDone(@PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findOne(id);
        taskManager.makeDone(task);
        taskRepository.save(task);
        return getTasks(model);
    }*/



/*
    @PostMapping("/done/{id}")
    public String makeDone(@PathVariable("id") Long id, Model model) {
        Task task = taskManager.taskRepository.findOne(id);
        taskManager.makeDone(task);
        taskManager.taskRepository.save(task);
        return getTasks(model);
    }
*/

/*    @GetMapping("/done/{id}")
    public String done(@PathVariable("id") Long id, Model model){
        Task task = taskRepository.findOne(id);
        taskManager.makeDone(task);
        taskRepository.save(task);
        return getTasks(model);
    }*/

    @GetMapping("/done/{id}")
    public String done(@PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findOne(id);
        taskManager.makeDone(task);
        taskRepository.save(task);
        return getTasks(model);
    }


    @GetMapping("/archived")
    public String getArchived(Model model) {
        model.addAttribute("archived", taskRepository.findByIsArchived(true));
        return "archived";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findByIsArchived(false));
        return "tasks";
    }


}
