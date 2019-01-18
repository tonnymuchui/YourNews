package com.yournews.yournews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DiaryList extends ArrayAdapter<Diary> {
    private Activity context;
    List<Diary> diaries;

    public DiaryList(Activity context, List<Diary> diaries) {
        super(context, R.layout.layout_diary_list, diaries);
        this.context = context;
        this.diaries = diaries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_diary_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);

        Diary diary = diaries.get(position);
        textViewName.setText(diary.getUName());
        textViewGenre.setText(diary.getDiary());
        textViewDate.setText(diary.getDate());
        return listViewItem;
    }
}