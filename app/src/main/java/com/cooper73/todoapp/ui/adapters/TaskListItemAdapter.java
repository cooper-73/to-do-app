package com.cooper73.todoapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;

import java.util.ArrayList;

public class TaskListItemAdapter extends RecyclerView.Adapter<TaskListItemAdapter.ViewHolder> {
    private final ArrayList<TaskListViewModel> taskLists;
    private final Listener listener;

    public interface Listener {
        void onRecyclerItemClick(TaskListViewModel taskList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.tv_home_task_list_title);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }
    }

    public TaskListItemAdapter(ArrayList<TaskListViewModel> taskLists, Listener listener) {
        this.taskLists = taskLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListItemAdapter.ViewHolder holder, int position) {
        holder.getTitleTextView().setText(taskLists.get(position).getTitle());

        holder.itemView.setOnClickListener(v -> listener.onRecyclerItemClick(taskLists.get(position)));
    }

    @Override
    public int getItemCount() {
        return taskLists.size();
    }
}
