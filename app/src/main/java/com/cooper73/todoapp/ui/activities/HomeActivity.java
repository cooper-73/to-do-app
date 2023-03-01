package com.cooper73.todoapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.adapters.TaskListItemAdapter;
import com.cooper73.todoapp.ui.fragments.CreateListDialogFragment;
import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;
import com.cooper73.todoapp.ui.views.DialogView;
import com.cooper73.todoapp.ui.views.HomeView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeView, DialogView.listener, TaskListItemAdapter.Listener {
    private LinearLayout importantLinearLayout, tasksLinearLayout;
    private RecyclerView taskListsRecyclerView;
    private TextView initialsTextView, nameTextView, newListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hideActionBar();
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
        initialsTextView = findViewById(R.id.tv_home_user_initials);
        nameTextView = findViewById(R.id.tv_home_user_name);
        importantLinearLayout = findViewById(R.id.ll_home_important);
        tasksLinearLayout = findViewById(R.id.ll_home_tasks);
        taskListsRecyclerView = findViewById(R.id.rv_home_task_lists);
        newListTextView = findViewById(R.id.tv_home_new_list);
    }

    @Override
    public void initUI() {
        ArrayList<TaskListViewModel> taskLists = new ArrayList<>();
        taskLists.add(new TaskListViewModel(1, "Demo title"));
        TaskListItemAdapter adapter = new TaskListItemAdapter(taskLists, this);
        taskListsRecyclerView.setAdapter(adapter);
        taskListsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvents() {
        initialsTextView.setOnClickListener(view -> showProfile());
        importantLinearLayout.setOnClickListener(view -> showImportantTasks());
        tasksLinearLayout.setOnClickListener(view -> showAllTasks());
        newListTextView.setOnClickListener(view -> showCreateListDialog());
    }

    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)  actionBar.hide();
    }

    @Override
    public void showProfile() {

    }

    @Override
    public void showImportantTasks() {

    }

    @Override
    public void showAllTasks() {

    }

    @Override
    public void showTaskList() {

    }

    @Override
    public void showCreateListDialog() {
        DialogFragment dialog = new CreateListDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "CreateListDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // Create new list
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onRecyclerItemClick(TaskListViewModel taskList) {
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }
}