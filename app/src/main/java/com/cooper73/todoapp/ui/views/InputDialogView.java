package com.cooper73.todoapp.ui.views;

import android.view.View;

public interface InputDialogView {
    void bindUI(View view);
    void initUI();
    void initEvents();
    String getUserInput();
    interface Listener {
        void onDialogPositiveClick(InputDialogView dialog);
        void onDialogNegativeClick(InputDialogView dialog);
    }
}
