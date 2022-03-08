package matthewbeaulieu.cs.lewisu.edu.soccerplayerratingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SoccerPlayerRatingListAdapter
        extends RecyclerView.Adapter<SoccerPlayerRatingListAdapter.RatingHolder>{
    private List<SoccerPlayerRating> soccerPlayerRatings;
    private final Context context;

    public interface ListAdapterOnClickHandler{
        void onClick(SoccerPlayerRating soccerPlayerRating);
    }

    private final ListAdapterOnClickHandler clickHandler;

    public SoccerPlayerRatingListAdapter(Context context, ListAdapterOnClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rating_row, parent, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
        if (soccerPlayerRatings != null){
            SoccerPlayerRating ratingObject = soccerPlayerRatings.get(position);

            holder.rowNameView.setText(ratingObject.getPlayerName());
            int rating = ratingObject.getRating();
            String ratingString = context.getResources().getQuantityString(R.plurals.starRating, rating, rating);
            holder.rowRatingView.setText(ratingString);
        }
    }

    @Override
    public int getItemCount() {
        if(soccerPlayerRatings != null){
            return soccerPlayerRatings.size();
        }
        return 0;
    }

    public void setSoccerPlayerRatings(List<SoccerPlayerRating> soccerPlayerRatingsParam){
        soccerPlayerRatings = soccerPlayerRatingsParam;
    }

    class RatingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView rowNameView;
        private final TextView rowRatingView;
        public RatingHolder(@NonNull View itemView) {
            super(itemView);
            rowNameView = itemView.findViewById(R.id.rowNameView);
            rowRatingView = itemView.findViewById(R.id.rowRatingView);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            SoccerPlayerRating ratingObject = soccerPlayerRatings.get(adapterPosition);
            clickHandler.onClick(ratingObject);
        }
    }
}
