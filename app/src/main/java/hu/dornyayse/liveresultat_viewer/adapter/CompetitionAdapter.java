package hu.dornyayse.liveresultat_viewer.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import hu.dornyayse.liveresultat_viewer.CompetitionDetailsActivity;
import hu.dornyayse.liveresultat_viewer.CompetitionListActivity;
import hu.dornyayse.liveresultat_viewer.R;
import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;

public class CompetitionAdapter
        extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>{

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private final List<Competition> competitions = new ArrayList<>();
    private final List<Competition> todayCompetitions = new ArrayList<>();

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
                    holder.itemView.getResources().getColor(R.color.colorLiveToday)
            );
            holder.setWhiteText();
            holder.dateTextView.setText(R.string.live_today);
        } else {
            competition = competitions.get((position - todayCompetitions.size()));
            holder.dateTextView.setText(
                    DateFormat.getDateInstance(DateFormat.SHORT).format(competition.getDate())
            );
            holder.itemView.setBackgroundColor(
                    holder.itemView.getResources().getColor(R.color.gray)
            );
            holder.setBlackText();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CompetitionDetailsActivity.class);
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
        return competitions.size() + todayCompetitions.size() + 2;
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
