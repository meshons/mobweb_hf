package hu.dornyayse.liveresultat_viewer.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.model.LastPassing;

public class LastPassingAdapter
        extends RecyclerView.Adapter<LastPassingAdapter.LastPassingViewHolder>{

    List<LastPassing> lastPassings;

    @NonNull
    @Override
    public LastPassingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LastPassingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LastPassingViewHolder extends RecyclerView.ViewHolder {
        public LastPassingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
