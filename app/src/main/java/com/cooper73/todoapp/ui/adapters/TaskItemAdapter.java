package com.cooper73.todoapp.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;

import java.util.ArrayList;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {
    private final ArrayList<TaskViewModel> tasks;
    private final Listener listener;

    public TaskItemAdapter(ArrayList<TaskViewModel> tasks, Listener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    public interface Listener {
        void onCompletedCheckBoxClick(TaskViewModel task, boolean isCompleted, int position);
        void onRecyclerItemClick(TaskViewModel task);
        void onImportantCheckBoxClick(TaskViewModel task, boolean isImportant, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox completedCheckBox, importantCheckBox;
        private final TextView titleTextView, detailsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            completedCheckBox = itemView.findViewById(R.id.chb_task_list_task_completed);
            titleTextView = itemView.findViewById(R.id.tv_task_list_task_title);
            detailsTextView = itemView.findViewById(R.id.tv_task_list_task_details);
            importantCheckBox = itemView.findViewById(R.id.chb_task_list_task_important);
        }
    }

    @NonNull
    @Override
    public TaskItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskViewModel task = tasks.get(position);

        holder.completedCheckBox.setChecked(task.isCompleted());
        holder.titleTextView.setText(task.getTitle());
        if (task.getDueDate() != null)
            holder.detailsTextView.setText(task.getDueDate().toString());
        else
            holder.detailsTextView.setVisibility(View.GONE);
        holder.importantCheckBox.setChecked(task.isImportant());

        holder.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                Log.i("DELETE", task.getTitle());
                task.setCompleted(isChecked);
                listener.onCompletedCheckBoxClick(task, isChecked, position);
            }
        });

        holder.importantCheckBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                task.setImportant(isChecked);
                listener.onImportantCheckBoxClick(task, isChecked, position);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
