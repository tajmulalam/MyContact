package com.example.sumon.mycontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sumon on 3/13/2016.
 */
public class AdapterForContact extends ArrayAdapter<Contact> {

    Context context;
    ArrayList<Contact>contactArrayList;
    public AdapterForContact(Context context, ArrayList<Contact>contactArrayList) {
        super(context, R.layout.custom_row, contactArrayList);
        this.context=context;
        this.contactArrayList=contactArrayList;
    }


    static class ViewHolder{
        TextView nameTextView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_row,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.nameTextView= (TextView) convertView.findViewById(R.id.nameTV);
            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.nameTextView.setText(contactArrayList.get(position).getName());

        return convertView;
    }
}
