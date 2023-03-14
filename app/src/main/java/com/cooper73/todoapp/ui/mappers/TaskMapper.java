package com.cooper73.todoapp.ui.mappers;

import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskViewModel from(Task task) {
        TaskViewModel taskViewModel = new TaskViewModel();

        taskViewModel.setId(task.getId());
        taskViewModel.setTitle(task.getTitle());
        taskViewModel.setDescription(task.getDescription());
        taskViewModel.setCreatedAt(task.getCreatedAt());
        taskViewModel.setDueDate(task.getDueDate());
        taskViewModel.setCompleted(task.isCompleted());
        taskViewModel.setImportant(task.isImportant());

        return taskViewModel;
    }

    public static ArrayList<TaskViewModel> from(List<Task> tasks) {
        ArrayList<TaskViewModel> taskArrayList = new ArrayList<>();

        for (Task task : tasks) {
            taskArrayList.add(TaskMapper.from(task));
        }

        return taskArrayList;
    }
}
