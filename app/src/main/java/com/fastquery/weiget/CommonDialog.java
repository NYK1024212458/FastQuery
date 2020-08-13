package com.fastquery.weiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fastquery.R;

public class CommonDialog {


    private static TextView tv_cancel;
    private static TextView tv_contacts_customer;

    public static void showDialog(Context context,String content){


        AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        View view =LayoutInflater.from(context).inflate(R.layout.dialog_home_warning,null);

        dialog.setView(view);
        AlertDialog alertDialog = dialog.create();
        TextView tv_content = view.findViewById(R.id.tv_content);
        tv_content.setText(content);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_contacts_customer = view.findViewById(R.id.tv_contacts_customer);




        alertDialog.show();


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        tv_contacts_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OnLineCustomerDialog.showDialog(context);

            }
        });



    }

}
