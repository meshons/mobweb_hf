package hu.dornyayse.liveresultat_viewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import hu.dornyayse.liveresultat_viewer.CompetitionDetailsActivity;
import hu.dornyayse.liveresultat_viewer.R;
import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;

public class CompetitionAdapter
        extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>{

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private final List<Competition> competitions = new ArrayList<>();
    private final List<Competition> todayCompetitions = new ArrayList<>();

    private boolean darkMode;

    public CompetitionAdapter(Context context) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);

        darkMode = pref.getBoolean("dark_mode", false);
    }

    @NonNull
    @Override
    public CompetitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_competition, parent, false);
        return new CompetitionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompetitionViewHolder holder, int position) {
        Competition competition;

        if (position < todayCompetitions.size()) {
            competition = todayCompetitions.get(position);
            holder.itemView.setBackgroundColor(
                    holder.itemView.getResources().getColor(R.color.colorLiveTodayLight)
            );
            holder.setWhiteText();
            holder.dateTextView.setText(R.string.live_today);
        } else {
            competition = competitions.get((position - todayCompetitions.size()));
            holder.dateTextView.setText(
                    DateFormat.getDateInstance(DateFormat.SHORT).format(competition.getDate())
            );
            holder.itemView.setBackgroundColor(
                    holder.itemView.getResources().getColor(darkMode ? R.color.black : R.color.gray)
            );
            if (darkMode)
                holder.setWhiteText();
            else
                holder.setBlackText();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CompetitionDetailsActivity.class);
                i.putExtra("competition", competition.getId());
                v.getContext().startActivity(i);
            }
        });

        holder.nameTextView.setText(competition.getName());
        holder.clubTextView.setText(competition.getOrganizer());

        holder.competition = competition;
    }

    public void update(List<Competition> updatedCompetitions) {
        competitions.clear();
        competitions.addAll(updatedCompetitions);
        notifyDataSetChanged();
    }

    public void updateToday(List<Competition> updatedCompetitions) {
        todayCompetitions.clear();
        todayCompetitions.addAll(updatedCompetitions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return competitions.size() + todayCompetitions.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView clubTextView;
        TextView dateTextView;
        boolean liveToday;

        Competition competition;

        CompetitionViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_competition_name);
            clubTextView = itemView.findViewById(R.id.item_competition_club);
            dateTextView = itemView.findViewById(R.id.item_competition_date);
        }

        void setWhiteText() {
            nameTextView.setTextColor(
                    itemView.getResources().getColor(R.color.gray)
            );
            clubTextView.setTextColor(
                    itemView.getResources().getColor(R.color.gray)
            );
            dateTextView.setTextColor(
                    itemView.getResources().getColor(R.color.gray)
            );
        }

        void setBlackText() {
            nameTextView.setTextColor(
                    itemView.getResources().getColor(R.color.black)
            );
            clubTextView.setTextColor(
                    itemView.getResources().getColor(R.color.black)
            );
            dateTextView.setTextColor(
                    itemView.getResources().getColor(R.color.black)
            );
        }
    }
}
