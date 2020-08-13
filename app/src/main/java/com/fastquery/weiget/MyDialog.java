package com.fastquery.weiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.fastquery.R;


public class MyDialog {


    public static void showDialog(final Context context) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(R.layout.dialog_view);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);
        dialog.setView(view);

        final EditText authorizationCode = view.findViewById(R.id.et_authorizationCode);
        Button contactCustomer = view.findViewById(R.id.bt_ContactCustomer);

        contactCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = authorizationCode.getText().toString().trim();
                if (code.isEmpty()) {

                    Toast.makeText(context, "请输入核验码", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });


    }


}
