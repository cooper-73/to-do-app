package com.cooper73.todoapp.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.DialogView;

import java.util.Objects;

public class DeleteListDialogFragment extends DialogFragment implements DialogView {
    private Button negativeButton, positiveButton;
    private Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implements DialogView.Listener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Objects.requireNonNull(getDialog())
                .getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_list, null);
        builder.setView(view);

        bindUI(view);
        initEvents();

        return builder.create();
    }

    @Override
    public void bindUI(View view) {
        negativeButton = view.findViewById(R.id.btn_negative_delete_list_dialog);
        positiveButton = view.findViewById(R.id.btn_positive_delete_list_dialog);
    }

    @Override
    public void initEvents() {
        negativeButton.setOnClickListener(v -> listener.onDialogNegativeClick(this));
        positiveButton.setOnClickListener(v -> listener.onDialogPositiveClick(this));
    }
}
