package com.example.tutorly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder>{

    private Context mCtx;
    private List<Session> sessionList;
    private OnSessionListener mOnSessionListener;

    public SessionAdapter(Context mCtx, List<Session> sessionList, OnSessionListener mOnSessionListener) {
        this.mCtx = mCtx;
        this.sessionList = sessionList;
        this.mOnSessionListener = mOnSessionListener;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.session_list_layout, null);
        SessionViewHolder holder = new SessionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder sessionViewHolder, int i) {
        Session session = sessionList.get(i);
        sessionViewHolder.tutorNameTextView.setText("Tutor: " + session.getTutorName());
        sessionViewHolder.meetingDateAndTimeTextView.setText("Meeting on " + session.getMeetingMonth() + "/" + session.getMeetingDay()
            + "/" + session.getMeetingYear() + " at " + session.getMeetingStartTime());
        sessionViewHolder.meetingLocationTextView.setText("at " + session.getMeetingLocation());
        sessionViewHolder.sessionMessageTextView.setText(session.getSessionRequestMessage());
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    class SessionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tutorNameTextView, meetingDateAndTimeTextView, meetingLocationTextView, sessionMessageTextView;
        OnSessionListener onSessionListener;

        public SessionViewHolder(@NonNull View itemView) {
            super(itemView);

            tutorNameTextView = itemView.findViewById(R.id.tutorNameTextView);
            meetingDateAndTimeTextView = itemView.findViewById(R.id.meetingDateAndTimeTextView);
            meetingLocationTextView = itemView.findViewById(R.id.meetingLocationTextView);
            sessionMessageTextView = itemView.findViewById(R.id.sessionMessageTextView);

            this.onSessionListener = mOnSessionListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onSessionListener.onSessionClick(getAdapterPosition());
        }
    }
    public interface OnSessionListener {
        void onSessionClick(int position);
    }
}
