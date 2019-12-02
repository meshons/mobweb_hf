package hu.dornyayse.liveresultat_viewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import hu.dornyayse.liveresultat_viewer.R;
import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;

public class CompetitionAdapter
        extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>{

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private final List<Competition> competitions = new ArrayList<>();

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
        Competition competition = competitions.get(position);
        holder.nameTextView.setText(competition.getName());
        holder.clubTextView.setText(competition.getOrganizer());
        holder.dateTextView.setText(
                DateFormat.getDateInstance(DateFormat.SHORT).format(competition.getDate())
        );

        holder.competition = competition;
    }

    public void update(List<Competition> updatedCompetitions) {
        competitions.clear();
        competitions.addAll(updatedCompetitions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return competitions.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView clubTextView;
        TextView dateTextView;

        Competition competition;

        CompetitionViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_competition_name);
            clubTextView = itemView.findViewById(R.id.item_competition_club);
            dateTextView = itemView.findViewById(R.id.item_competition_date);
        }
    }
}
