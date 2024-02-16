package com.mujar.addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Contacts> {

    private LayoutInflater inflater;
    private ArrayList<Contacts> contacts;
    private int mViewResourceId;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Contacts> contacts){
        super(context, textViewResourceId, contacts);
        this.contacts = contacts;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(mViewResourceId, null);

        Contacts contact = contacts.get(position);

        if (contact != null) {
            TextView txtName = (TextView) convertView.findViewById(R.id.name);

            if (txtName != null) {
                txtName.setText((contact.getName()));
            }
        }
        return convertView;
    }
}
