package com.cooper73.todoapp.ui.views;

import android.view.View;

public interface DialogView {
    void bindUI(View view);
    void initEvents();
    interface Listener {
        void onDialogPositiveClick(DialogView dialog);
        void onDialogNegativeClick(DialogView dialog);
    }
}
