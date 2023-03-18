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
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity implements HighlightedTasksView {
    private RecyclerView tasksRecyclerView;

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

    }

    @Override
    public void notifyTaskRemovedAndRangeChanged(int position, int itemCount) {

    }

    @Override
    public void notifyTaskUpdated(int position) {

    }
}