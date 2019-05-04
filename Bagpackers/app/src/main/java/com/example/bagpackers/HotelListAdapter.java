package com.example.bagpackers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;

import java.util.ArrayList;

public class HotelListAdapter extends ArrayAdapter<Hotels> implements Filterable
{
    private ArrayList<Hotels> todos;
    private ArrayList<Hotels> filteredtodos;
    private Filter filter;

    public HotelListAdapter(Context context, ArrayList<Hotels> notes){
        super(context,0,notes);
        this.todos = notes;
        this.filteredtodos = notes;
    }

    public Hotels getItem(int position){
        return filteredtodos.get(position);
    }

    public int getCount() {
        return filteredtodos.size();
    }

    public View getView(int position, View convertView,ViewGroup parent)
    {
        Hotels t= getItem(position);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hotel_layout,parent,false);
        }


     //   convertView.setBackgroundColor(t.getColor()); //Code here to display to listview

//        ImageView name = convertView.findViewById(R.id.hotel_pic);
//        Uri uri = Uri.parse(t.picture);
//        name.setImageURI(uri);


        TextView place = (TextView) convertView.findViewById(R.id.hotel_name);
        place.setText(t.name);

        TextView num = (TextView) convertView.findViewById(R.id.numb);
        num.setText("Number: "+t.number);

        TextView rating = (TextView) convertView.findViewById(R.id.hotelrating);
        rating.setText("Rating"+Double.toString(t.rating));

        TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText("Price per Room :"+Double.toString(t.rate));



        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new TodoFilter();
        }
        return filter;
    }

    public class TodoFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            String check=constraint.toString();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Hotels> filteredList = new ArrayList<Hotels>();
                for(int i=0; i < todos.size(); i++){
                    if(todos.get(i).name.contains(check))
                    {
                        filteredList.add(todos.get(i));
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            }
            else{
                results.count = todos.size();
                results.values = todos;
            }

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            filteredtodos = (ArrayList<Hotels>) results.values;
            notifyDataSetChanged();
        }

    }
}
