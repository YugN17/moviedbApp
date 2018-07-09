package com.ia.moviedb.seriesdetail;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ia.moviedb.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeasonEpisodeListAdapter extends BaseExpandableListAdapter{

    private SeriesDetailView view;
    private Context context;
    private List<String> seasons;
    private Map<String, List<String>> episodes;

    public SeasonEpisodeListAdapter(Context context, SeriesDetailView view, List<String> seasons, Map<String, List<String>> episodes) {
        this.context = context;
        this.view = view;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    @Override
    public int getGroupCount() {
        return seasons.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<String> episode = new ArrayList<>();
        if (episodes.get(seasons.get(i))!= null )episode.addAll(episodes.get(seasons.get(i)));
        return episode.size();
    }

    @Override
    public Object getGroup(int i) {
        return seasons.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return episodes.get(seasons.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String season = (String) getGroup(i);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.season, null);
        }

        TextView seasonName = view.findViewById(R.id.seasonName);
        seasonName.setTypeface(null, Typeface.BOLD);
        seasonName.setText("Season " + season);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        final String episode = (String) getChild(i, i1);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.episode, null);
        }

        TextView espisodeName = view
                .findViewById(R.id.episodeName);

        espisodeName.setText(episode);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        view.onSeasonClicked(groupPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        view.onSeasonClosed(groupPosition);
    }
}
