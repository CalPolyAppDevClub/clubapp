package club.polyappdev.clubapp;

<<<<<<< HEAD
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import java.util.*;

/**
 * Created by Alex on 2/4/17.
=======
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by dphey on 2/4/2017.
>>>>>>> 8228bb9d6a37ce1e4c5743ba45341c41dea32cef
 */

public class subscriptionRowAdapter extends BaseAdapter {

<<<<<<< HEAD

    public subscriptionRowAdapter(Context context, int resource) {
=======
    public subscriptionRowAdapter(Context context, int resource) {
    }

    public void setLists(ArrayList<String> inputList, int inputTextView){
>>>>>>> 8228bb9d6a37ce1e4c5743ba45341c41dea32cef

    }

    @Override
    public int getCount() {
        return 0;
    }

<<<<<<< HEAD
    public void setLists(ArrayList<String> inputList, int inputTextView) {

    }

    @Override
    public Object getItem(int i) {
=======
    @Override
    public Object getItem(int position) {
>>>>>>> 8228bb9d6a37ce1e4c5743ba45341c41dea32cef
        return null;
    }

    @Override
<<<<<<< HEAD
    public long getItemId(int i) {
=======
    public long getItemId(int position) {
>>>>>>> 8228bb9d6a37ce1e4c5743ba45341c41dea32cef
        return 0;
    }

    @Override
<<<<<<< HEAD
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


=======
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
>>>>>>> 8228bb9d6a37ce1e4c5743ba45341c41dea32cef
}
