package com.example.bagpackers;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.R;

import java.util.ArrayList;

public class TodoListAdapter extends ArrayAdapter<com.example.bagpackers.Classes.Place> implements Filterable
{
    private ArrayList<com.example.bagpackers.Classes.Place> todos;
    private ArrayList<com.example.bagpackers.Classes.Place> filteredtodos;
    private Filter filter;

    public TodoListAdapter(Context context,ArrayList<com.example.bagpackers.Classes.Place> notes){
        super(context,0,notes);
        this.todos = notes;
        this.filteredtodos = notes;
    }

    public com.example.bagpackers.Classes.Place getItem(int position){
        return filteredtodos.get(position);
    }

    public int getCount() {
        return filteredtodos.size();
    }

    public View getView(int position, View convertView,ViewGroup parent)
    {
        com.example.bagpackers.Classes.Place t= getItem(position);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_note_list_item,parent,false);
        }


     //   convertView.setBackgroundColor(t.getColor()); //Code here to display to listview

        ImageView name = convertView.findViewById(R.id.myImageView);
        Uri uri = Uri.parse(t.picture);
        name.setImageURI(uri);

        TextView place = (TextView) convertView.findViewById(R.id.textView12);
        place.setText(t.name);

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
                ArrayList<Place> filteredList = new ArrayList<Place>();
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
            filteredtodos = (ArrayList<Place>) results.values;
            notifyDataSetChanged();
        }

    }
}
