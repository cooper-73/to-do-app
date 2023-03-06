package com.cooper73.todoapp.ui.mappers;

import com.cooper73.todoapp.data.entities.TaskList;
import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskListMapper {
    
    public static TaskListViewModel from(TaskList taskList) {
        TaskListViewModel taskListViewModel = new TaskListViewModel();
        taskListViewModel.setId(taskList.getId());
        taskListViewModel.setTitle(taskList.getTitle());
        
        return taskListViewModel;
    }
    
    public static ArrayList<TaskListViewModel> from(List<TaskList> taskLists) {
        ArrayList<TaskListViewModel> taskListArray = new ArrayList<>();
        for (TaskList taskList : taskLists) {
            taskListArray.add(TaskListMapper.from(taskList));
        }

        return taskListArray;
    }
}
