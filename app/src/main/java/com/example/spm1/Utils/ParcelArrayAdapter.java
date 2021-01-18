package com.example.spm1.Utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spm1.Entities.Parcel;
import com.example.spm1.R;

import java.util.List;

public class ParcelArrayAdapter extends ArrayAdapter<Parcel> {
    private int listItemLayout;
    public ParcelArrayAdapter(Context context, int layoutId, List<Parcel> itemList) {
        super(context, layoutId, itemList);
        listItemLayout = layoutId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Parcel parcel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.parcelId = (TextView) convertView.findViewById(R.id.idParcel);
            viewHolder.parcelName = (TextView) convertView.findViewById(R.id.nameTextView);
            viewHolder.parcelDM = (TextView) convertView.findViewById(R.id.DMName);
            convertView.setTag(viewHolder); // view lookup cache stored in tag
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.parcelId.setText(parcel.getId());
        viewHolder.parcelName.setText(parcel.getFirstName()+" "+parcel.getLastName());
        viewHolder.parcelDM.setText(parcel.getAddress());

        // Return the completed view to render on screen
        return convertView;
    }

    private static class ViewHolder {
        TextView parcelId;
        TextView parcelName;
        TextView parcelDM;
    }
}

