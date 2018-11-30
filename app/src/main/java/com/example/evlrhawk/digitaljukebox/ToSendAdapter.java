package com.example.evlrhawk.digitaljukebox;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ToSendAdapter extends ArrayAdapter<ToSend> {
    private Activity context;
    private List<ToSend> sendList;

    public ToSendAdapter(Activity context, List<ToSend>sendList){
        super(context,R.layout.list_view,sendList);
        this.context = context;
        this.sendList = sendList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.list_view, null, true);

        TextView trackName = (TextView)listView.findViewById(R.id.track);

        ToSend message = sendList.get(position);
        trackName.setText(message.getToSend());

        return listView;
    }
}

