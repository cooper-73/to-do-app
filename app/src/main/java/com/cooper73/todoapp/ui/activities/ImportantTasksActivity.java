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
import com.cooper73.todoapp.ui.adapters.TaskItemAdapter;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.ArrayList;

public class ImportantTasksActivity extends AppCompatActivity implements HighlightedTasksView, TaskItemAdapter.Listener {
    private RecyclerView importantTasksRecyclerView;
    private TaskItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_tasks);
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
        importantTasksRecyclerView = findViewById(R.id.rv_important_tasks_tasks);
    }

    @Override
    public void initUI() {
        importantTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getColorFromAttribute(androidx.appcompat.R.attr.colorError)));
            actionBar.setElevation(0);
            actionBar.setTitle("Important");
        }
    }

    @Override
    public void showTasks(ArrayList<TaskViewModel> tasks) {
        adapter = new TaskItemAdapter(new ArrayList<>(), this);
        importantTasksRecyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyTaskRemovedAndRangeChanged(int position, int itemCount) {

    }

    @Override
    public void notifyTaskUpdated(int position) {

    }

    public int getColorFromAttribute(int attributeResId) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(attributeResId, typedValue, true);
        return ContextCompat.getColor(this, typedValue.resourceId);
    }

    @Override
    public void onCompletedCheckBoxClick(TaskViewModel task, boolean isCompleted, int position) {

    }

    @Override
    public void onRecyclerItemClick(TaskViewModel task) {

    }

    @Override
    public void onImportantCheckBoxClick(TaskViewModel task, boolean isImportant, int position) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}