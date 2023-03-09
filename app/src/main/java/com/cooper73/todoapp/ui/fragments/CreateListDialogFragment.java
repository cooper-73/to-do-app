package com.cooper73.todoapp.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.InputDialogView;

import java.util.Objects;

public class CreateListDialogFragment extends DialogFragment implements InputDialogView {
    private EditText titleEditText;
    private Button negativeButton, positiveButton;
    private Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement InputDialogView.Listener");
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
        View view = inflater.inflate(R.layout.dialog_create_list, null);
        builder.setView(view);

        bindUI(view);
        initUI();
        initEvents();

        return builder.create();
    }

    @Override
    public void bindUI(View view) {
        titleEditText = view.findViewById(R.id.et_title_new_list_dialog);
        negativeButton = view.findViewById(R.id.btn_negative_new_list_dialog);
        positiveButton = view.findViewById(R.id.btn_positive_new_list_dialog);
    }

    @Override
    public void initUI() {
        positiveButton.setEnabled(false);
    }

    @Override
    public void initEvents() {
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    positiveButton.setEnabled(true);
                } else {
                    positiveButton.setEnabled(false);
                    titleEditText.setError("The item cannot be empty.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        negativeButton.setOnClickListener(view -> listener.onDialogNegativeClick(this));
        positiveButton.setOnClickListener(view -> listener.onDialogPositiveClick(this));
    }

    @Override
    public String getUserInput() {
        return titleEditText.getText().toString();
    }
}
