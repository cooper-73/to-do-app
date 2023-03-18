package com.cooper73.todoapp.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.presentation.presenters.AllTasksPresenter;
import com.cooper73.todoapp.presentation.presenters.AllTasksPresenterImpl;
import com.cooper73.todoapp.ui.adapters.TaskItemAdapter;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity implements HighlightedTasksView, TaskItemAdapter.Listener {
    private RecyclerView tasksRecyclerView;
    private AllTasksPresenter presenter;
    private TaskItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        setActionBar();
        initPresenter();
        bindUI();
        initUI();
        initEvents();
    }

    @Override
    public void initPresenter() {
        presenter = new AllTasksPresenterImpl(this);
    }

    @Override
    public void bindUI() {
        tasksRecyclerView = findViewById(R.id.rv_all_tasks);
    }

    @Override
    public void initUI() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Tasks");
            actionBar.setBackgroundDrawable(new ColorDrawable(getColorFromAttribute(com.google.android.material.R.attr.colorPrimaryVariant)));
            actionBar.setElevation(0);
        }
    }

    private int getColorFromAttribute(int attributeResId) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(attributeResId,typedValue, true);

        return ContextCompat.getColor(this, typedValue.resourceId);
    }

    @Override
    public void showTasks(ArrayList<TaskViewModel> tasks) {
        adapter = new TaskItemAdapter(tasks, this);
        tasksRecyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyTaskRemovedAndRangeChanged(int position, int itemCount) {
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, itemCount);
    }

    @Override
    public void notifyTaskUpdated(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onCompletedCheckBoxClick(TaskViewModel task, boolean isCompleted, int position) {
        presenter.updateTaskCompletedFlag(task.getId(), isCompleted, position);
    }

    @Override
    public void onRecyclerItemClick(TaskViewModel task) {

    }

    @Override
    public void onImportantCheckBoxClick(TaskViewModel task, boolean isImportant, int position) {
        presenter.updateTaskImportantFlag(task.getId(), isImportant, position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllTasks("d1e0c4fc-207c-41fb-ac57-98a383ec612e");
    }
}