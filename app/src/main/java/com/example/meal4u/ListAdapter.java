package com.example.meal4u;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ListAdapter extends BaseAdapter {
    private final ArrayList data;

    public ListAdapter(Map<String, String> map)
    {
        data = new ArrayList();
        data.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int i) {
        return (Map.Entry) data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View result;

        if (view == null)
            result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        else
            result = view;

        Map.Entry<String, String> item = getItem(i);

        ((TextView) result.findViewById(R.id.lv_text_day)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.lv_text_menu)).setText(item.getValue());

        return result;
    }
}
