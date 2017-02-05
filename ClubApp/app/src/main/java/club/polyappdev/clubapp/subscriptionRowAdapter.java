package club.polyappdev.clubapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by dphey on 2/4/2017.
 */

public class subscriptionRowAdapter extends BaseAdapter {

    public subscriptionRowAdapter(Context context, int resource) {
    }

    public void setLists(ArrayList<String> inputList, int inputTextView){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
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
}
