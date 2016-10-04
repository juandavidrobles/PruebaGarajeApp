package com.example.jrobles.pruebagarajeapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JRobles on 4/10/2016.
 */

public class ListaPersonalizada extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public ListaPersonalizada(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.list_promo, itemname);
        //TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_promo, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.tNameListView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imListView);
        //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        //extratxt.setText("Description "+itemname[position]);
        return rowView;

    };
}
