package com.thedistantblue.triaryapp.mainscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.thedistantblue.triaryapp.R;

public class DeleteAlertDialog extends Dialog implements View.OnClickListener {

    private Button deleteButton, cancelButton;
    private ItemTouchHelperAdapter adapter;
    private int position;

    public DeleteAlertDialog(ItemTouchHelperAdapter adapter, int position) {
        super(adapter.getContext());
        this.adapter = adapter;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog_layout);
        deleteButton = findViewById(R.id.delete_button);
        cancelButton = findViewById(R.id.cancel_button);
        deleteButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_button:
                adapter.onItemDismiss(position);
                break;
            case R.id.cancel_button:
                adapter.onRefresh(position);
                break;
            default:
                break;
        }
        dismiss();
    }
}
