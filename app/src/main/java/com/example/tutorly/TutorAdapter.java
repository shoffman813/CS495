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
    private OnTutorListener mOnTutorListener;


    public TutorAdapter(Context mCtx, List<Tutor> tutorList, OnTutorListener onTutorListener) {
        this.mCtx = mCtx;
        this.tutorList = tutorList;
        this.mOnTutorListener = onTutorListener;
    }

    @NonNull
    @Override
    public TutorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //Creates a view holder instance
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_list_layout, null);
        return new TutorViewHolder(view, mOnTutorListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorViewHolder tutorViewHolder, int i) { //Binds data to view holder
        Tutor tutor = tutorList.get(i);

        tutorViewHolder.textViewFullName.setText(tutor.name);
        tutorViewHolder.textViewBio.setText(tutor.getShortBio());
        tutorViewHolder.textViewRate.setText("$" + tutor.getPayRate() +"/hour" ); //Come back later and change, add to Tutor class

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable()); have to populate from tutor profile image
    }

    @Override
    public int getItemCount() { //Returns the size of the list
        return tutorList.size();
    }

    class TutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView; //Tutor profile picture
        TextView textViewFullName, textViewBio, textViewRate;
        OnTutorListener onTutorListener;

        public TutorViewHolder(@NonNull View itemView, OnTutorListener onTutorListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewFullName = itemView.findViewById(R.id.textViewFullName);
            textViewBio = itemView.findViewById(R.id.textViewBio);
            textViewRate = itemView.findViewById(R.id.textViewRate);
            this.onTutorListener = onTutorListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTutorListener.onTutorClick(getAdapterPosition());
        }
    }

    public interface OnTutorListener {
        void onTutorClick(int position);
    }
}
