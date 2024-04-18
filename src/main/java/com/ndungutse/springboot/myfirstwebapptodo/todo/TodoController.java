package com.ndungutse.springboot.myfirstwebapptodo.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TodoController {
    private TodoService todoSrv;
    @Autowired
    private HttpSession session;

    public TodoController(TodoService todoSrv) {
        this.todoSrv = todoSrv;
    }

    // ***** Get All Todos *****
    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String getAllTodos(ModelMap model) {
        // ***** V1 *****
        // String username = getLoggedInUsername();
        // // String username = (String) model.get("username");
        // List<Todo> todos = todoSrv.findByUsername(username);
        // model.put("todos", todos);

        // ***** V2 JPA ******

        List<Todo> todos = todoSrv.findByUsername(getLoggedInUsername());
        System.out.println("TODOS ********" + todos);
        model.put("todos", todos);
        return "listTodos";
    }

    // ***** Add Todo *****
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String renderAddTodoPage(ModelMap model) {
        String username = (String) model.get("username");
        // Binding todo page with todo bean
        Todo todo = new Todo(0, username, "", LocalDate.now().plusDays(4), false);
        model.put("todo", todo);
        return "todo";
    }

    // V1: REQUESTPARAMS
    // @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    // public String createTodo(@RequestParam String description, @RequestParam
    // LocalDate targetDate) {
    // // Or username = new ModelMap().get(username) if we have
    // // @SessionAttributes("username")
    // String username = (String) session.getAttribute("username");
    // todoSrv.addTodo(username, description, targetDate, false);

    // return "redirect:list-todos";
    // }

    // V2: COMMAND BEAN
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    // Binding tof form to controller
    // @Valid enforce validations on TOdo
    //
    public String createTodo(@Valid Todo todo, BindingResult result) {
        // Or username = new ModelMap().get(username) if we have
        // @SessionAttributes("username")
        // BindingResults receives errors from validations also allow spring form to
        // scan for errors

        if (result.hasErrors()) {
            return "todo";
        }

        // String username = (String) session.getAttribute("username");
        // todoSrv.addTodo(username, todo.getDescription(), todo.getTargetDate(),
        // false);

        // **** V2 JPA *****
        todoSrv.addTodo(todo);

        return "redirect:list-todos";
    }

    // ***** DELETE TODO
    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {

        todoSrv.deleteTodoById(id);

        return "redirect:list-todos";
    }

    // ***** Update TODO
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String renderUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoSrv.findTodoById(id);

        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todoSrv.updateTodo(todo);

        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authenitcation = SecurityContextHolder.getContext().getAuthentication();
        return authenitcation.getName();
    }
}
