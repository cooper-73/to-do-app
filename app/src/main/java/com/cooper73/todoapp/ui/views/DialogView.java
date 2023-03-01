package com.cooper73.todoapp.ui.views;

import android.view.View;

import androidx.fragment.app.DialogFragment;

public interface DialogView {
    void bindUI(View view);
    void initUI();
    void initEvents();
    interface Listener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }
}
