package com.example.tutorly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorViewHolder> {


    private Context mCtx;
    private List<Tutor> tutorList;


    public TutorAdapter(Context mCtx, List<Tutor> tutorList) {
        this.mCtx = mCtx;
        this.tutorList = tutorList;
    }

    @NonNull
    @Override
    public TutorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //Creates a view holder instance
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_list_layout, null);
        return new TutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorViewHolder tutorViewHolder, int i) { //Binds data to view holder
        Tutor tutor = tutorList.get(i);

        tutorViewHolder.textViewFullName.setText(tutor.name);
        tutorViewHolder.textViewBio.setText(tutor.getShortBio());
        tutorViewHolder.textViewRate.setText(tutor.getPayRate()); //Come back later and change, add to Tutor class

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable()); have to populate from tutor profile image
    }

    @Override
    public int getItemCount() { //Returns the size of the list
        return tutorList.size();
    }

    class TutorViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView; //Tutor profile picture
        TextView textViewFullName, textViewBio, textViewRate;

        public TutorViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewFullName = itemView.findViewById(R.id.textViewFullName);
            textViewBio = itemView.findViewById(R.id.textViewBio);
            textViewRate = itemView.findViewById(R.id.textViewRate);
        }
    }
}
