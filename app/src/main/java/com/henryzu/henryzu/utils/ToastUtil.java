package com.henryzu.henryzu.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.henryzu.henryzu.R;


public class ToastUtil {

    public static void show(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, int contentId) {
        Toast.makeText(context, contentId, Toast.LENGTH_LONG).show();
    }

    public static void showSelf(Context context, String info) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_self, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_tv);
        tv.setText(info);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}