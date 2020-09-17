package com.smartrider24;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CommonHintArrayAdapter<T> extends ArrayAdapter<T> {


    public CommonHintArrayAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner_item, null);
        TextView tv = view.findViewById(R.id.village);
        if (position == 0) {
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
            tv.setVisibility(View.GONE);
        }


        tv.setText(getItem(position).toString());
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_spinner_adapter, null);
        } else {
            view = convertView;
        }
        TextView tv = view.findViewById(R.id.village);
        tv.setText(getItem(position).toString());
        return view;
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        return super.getCount();
//        return count > 0 ? count - 1 : count;
    }
}