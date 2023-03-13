package com.cooper73.todoapp.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class TaskListActivity extends AppCompatActivity implements TaskListView, InputDialogView.Listener, DialogView.Listener, TaskItemAdapter.Listener {
    private String taskListId, taskListTitle;
    private TextView toDoTasksTextView, completedTasksTextView;
    private EditText addTaskEditText;
    private View toDoTasksLine, completedTasksLine;
    private RecyclerView tasksRecyclerView;
    private LinearLayout addTaskContainerLinearLayout;
    private TaskListPresenter presenter;
    private InputMethodManager imm;

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
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
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
    public void onBackPressed() {
        if (addTaskEditText != null && addTaskEditText.hasFocus()) {
            addTaskEditText.clearFocus();
            return;
        }

        super.onBackPressed();
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
        addTaskContainerLinearLayout = findViewById(R.id.ll_task_list_add_task_container);
        addTaskEditText = findViewById(R.id.et_task_list_add_task);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public void initUI() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvents() {
        toDoTasksTextView.setOnClickListener(v -> onClickToDoTasks());
        completedTasksTextView.setOnClickListener(v -> onClickCompletedTasks());
        addTaskEditText.setOnFocusChangeListener((v, hasFocus) -> setAddTaskEditTextOnFocusChange(hasFocus));
        addTaskEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0 && addTaskEditText.hasFocus()) {
                    addTaskEditText.setError("The item cannot be empty");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addTaskEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (addTaskEditText.getText().toString().length() != 0) {
                    imm.hideSoftInputFromWindow(addTaskEditText.getWindowToken(), 0);
                    addTaskEditText.clearFocus();
//                    Send to presenter
                    return false;
                } else {
                    return true;
                }

            }
            return false;
        });
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

        TaskItemAdapter adapter = new TaskItemAdapter(arrayList, this);
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

    public void setAddTaskEditTextOnFocusChange(boolean isFocused) {
        if (isFocused) {
            addTaskContainerLinearLayout.setBackground(new ColorDrawable(
                    getColorFromAttribute(com.google.android.material.R.attr.colorSurface)
            ));
            addTaskEditText.setHeight(58);
            addTaskEditText.setBackgroundResource(androidx.appcompat.R.drawable.abc_edit_text_material);
            addTaskEditText.setHintTextColor(getColorFromAttribute(R.attr.colorOnSurfaceLowBrush));
            addTaskEditText.setTextSize(16);

            imm.showSoftInput(addTaskEditText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            addTaskContainerLinearLayout.setBackground(new ColorDrawable(Color.TRANSPARENT));
            addTaskEditText.setHeight(40);
            addTaskEditText.setBackgroundResource(R.drawable.shape_add_task_btn);
            addTaskEditText.setError(null);
            addTaskEditText.setText("");
            addTaskEditText.setHintTextColor(getColorFromAttribute(com.google.android.material.R.attr.colorOnPrimary));
            addTaskEditText.setTextSize(14);
        }
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

    @Override
    public void onCompletedCheckBoxClick(TaskViewModel task) {

    }

    @Override
    public void onRecyclerItemClick(TaskViewModel task) {

    }

    @Override
    public void onImportantCheckBoxClick(TaskViewModel task) {

    }
}