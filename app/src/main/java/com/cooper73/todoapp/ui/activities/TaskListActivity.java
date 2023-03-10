package com.cooper73.todoapp.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.presentation.presenters.TaskListPresenter;
import com.cooper73.todoapp.presentation.presenters.TaskListPresenterImpl;
import com.cooper73.todoapp.ui.adapters.TaskItemAdapter;
import com.cooper73.todoapp.ui.fragments.DeleteListDialogFragment;
import com.cooper73.todoapp.ui.fragments.RenameListDialogFragment;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.DialogView;
import com.cooper73.todoapp.ui.views.InputDialogView;
import com.cooper73.todoapp.ui.views.TaskListView;

import java.util.ArrayList;
import java.util.Date;

public class TaskListActivity extends AppCompatActivity implements TaskListView, InputDialogView.Listener, DialogView.Listener {
    private String taskListId, taskListTitle;
    private TextView toDoTasksTextView, completedTasksTextView, addTaskTextView;
    private View toDoTasksLine, completedTasksLine;
    private RecyclerView tasksRecyclerView;
    private TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getExtras();
        setActionBar();
        initPresenter();
        bindUI();
        initUI();
        initEvents();
        showToDoTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_edit) {
            showRenameListDialog();
            return true;
        } else if (itemId == R.id.action_delete) {
            showDeleteListDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initPresenter() {
        presenter = new TaskListPresenterImpl(this);
    }

    @Override
    public void bindUI() {
        toDoTasksTextView = findViewById(R.id.tv_task_list_to_do);
        completedTasksTextView = findViewById(R.id.tv_task_list_completed);
        toDoTasksLine = findViewById(R.id.line_task_list_to_do);
        completedTasksLine = findViewById(R.id.line_task_list_completed);
        tasksRecyclerView = findViewById(R.id.rv_task_list_tasks);
        addTaskTextView = findViewById(R.id.tv_task_list_add_task);
    }

    @Override
    public void initUI() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvents() {
        toDoTasksTextView.setOnClickListener(v -> onClickToDoTasks());
        completedTasksTextView.setOnClickListener(v -> onClickCompletedTasks());
        addTaskTextView.setOnClickListener(v -> showAddTaskActivity());
    }

    @Override
    public void getExtras() {
        taskListId = getIntent().getStringExtra("id");
        taskListTitle = getIntent().getStringExtra("title");
    }

    @Override
    public void setActionBar() {
        int color = getColorFromAttribute(com.google.android.material.R.attr.colorPrimaryVariant);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)  return;
        setActionBarTitle(taskListTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(color));
        actionBar.setElevation(0);
    }

    @Override
    public void setActionBarTitle(String title) {
        this.taskListTitle = title;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && title != null)
            actionBar.setTitle(title);
    }

    @Override
    public void showRenameListDialog() {
        DialogFragment dialog = new RenameListDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "RenameListDialogFragment");
    }

    @Override
    public void showDeleteListDialog() {
        DialogFragment dialog = new DeleteListDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "DeleteListDialogFragment");
    }

    @Override
    public void onClickToDoTasks() {
        int onColor = getColorFromAttribute(androidx.appcompat.R.attr.colorPrimary);
        int offColor = getColorFromAttribute(R.attr.colorOnSurfaceLowBrush);
        toDoTasksTextView.setTextColor(onColor);
        toDoTasksLine.setVisibility(View.VISIBLE);
        completedTasksTextView.setTextColor(offColor);
        completedTasksLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickCompletedTasks() {
        int onColor = getColorFromAttribute(androidx.appcompat.R.attr.colorPrimary);
        int offColor = getColorFromAttribute(R.attr.colorOnSurfaceLowBrush);
        toDoTasksTextView.setTextColor(offColor);
        toDoTasksLine.setVisibility(View.INVISIBLE);
        completedTasksTextView.setTextColor(onColor);
        completedTasksLine.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToDoTasks() {
        ArrayList<TaskViewModel> arrayList = new ArrayList<>();
        arrayList.add(new TaskViewModel(
                "1",
                "Test Task",
                new Date(),
                new Date(),
                "Short description",
                true,
                false));
        arrayList.add(new TaskViewModel(
                "2",
                "Test Task 2",
                new Date(),
                new Date(),
                "Short description 2",
                false,
                true));

        TaskItemAdapter adapter = new TaskItemAdapter(arrayList);
        tasksRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showCompletedTasks() {

    }

    @Override
    public void markTaskAsCompleted() {

    }

    @Override
    public void markTaskAsImportant() {

    }

    @Override
    public void showAddTaskActivity() {
        Toast.makeText(this, "Adding", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public int getColorFromAttribute(int attributeResId) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(attributeResId, typedValue, true);
        return ContextCompat.getColor(this, typedValue.resourceId);
    }

    @Override
    public void onDialogPositiveClick(InputDialogView dialog) {
        if (dialog != null) {
            ((DialogFragment) dialog).dismiss();
            String newTitle = dialog.getUserInput();
            presenter.renameTaskList(taskListId, newTitle);
        }
    }

    @Override
    public void onDialogNegativeClick(InputDialogView dialog) {
        if (dialog != null)
            ((DialogFragment) dialog).dismiss();
    }

    @Override
    public void onDialogPositiveClick(DialogView dialog) {
        if (dialog != null) {
            ((DialogFragment) dialog).dismiss();
            presenter.deleteTaskList(taskListId);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogView dialog) {
        if (dialog != null)
            ((DialogFragment) dialog).dismiss();
    }
}