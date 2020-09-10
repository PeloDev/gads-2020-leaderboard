package com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class LearnerLeaderRecyclerAdapter extends RecyclerView.Adapter<LearnerLeaderRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<Learner> learners;
    private Boolean isSkillIQ;

    public LearnerLeaderRecyclerAdapter(Context context, List<Learner> learners, boolean isSkillIQ) {
        this.context = context;
        this.learners = learners;
        this.isSkillIQ = isSkillIQ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_learner_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Learner learner = learners.get(position);
        holder.nameTV.setText(learner.getName());
        String scoreStr = isSkillIQ ? learner.getScore() + " skill IQ Score, " + learner.getCountry() : learner.getScore() + " learning hours, " + learner.getCountry();
        holder.scoreTV.setText(scoreStr);
        Glide.with(holder.badgeIV.getContext())
                .load(learner.getBadgeUrl())
                //.placeholder(((MainActivity) context).getDrawable(R.drawable...))
                //.error(((MainActivity) context).getDrawable(R.drawable...))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                        holder.badgeIV.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV, scoreTV;
        private ImageView badgeIV;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.learner_name_tv);
            scoreTV = (TextView) itemView.findViewById(R.id.learner_score_tv);
            badgeIV = (ImageView) itemView.findViewById(R.id.badge_iv);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, mCourses.get(mCurrentPosition).getTitle(),
                            Snackbar.LENGTH_LONG).show();

                }
            });*/
        }
    }
}
