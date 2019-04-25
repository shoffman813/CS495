package com.example.tutorly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SessionAsTutorAdapter extends RecyclerView.Adapter<SessionAsTutorAdapter.SessionAsTutorViewHolder>{

    private Context mCtx;
    private List<Session> sessionList;
    private OnSessionAsTutorListener mOnSessionAsTutorListener;

    public SessionAsTutorAdapter(Context mCtx, List<Session> sessionList, OnSessionAsTutorListener mOnSessionAsTutorListener) {
        this.mCtx = mCtx;
        this.sessionList = sessionList;
        this.mOnSessionAsTutorListener = mOnSessionAsTutorListener;
    }

    @NonNull
    @Override
    public SessionAsTutorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.session_list_as_tutor_layout, null);
        SessionAsTutorViewHolder holder = new SessionAsTutorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionAsTutorViewHolder sessionAsTutorViewHolder, int i) {
        Session session = sessionList.get(i);
        sessionAsTutorViewHolder.userNameTextView5.setText("Student: " + session.getUserName());
        sessionAsTutorViewHolder.meetingDateAndTimeTextView2.setText("Meeting on " + session.getMeetingMonth() + "/" + session.getMeetingDay()
                + "/" + session.getMeetingYear() + " at " + session.getMeetingStartTime());
        sessionAsTutorViewHolder.meetingLocationTextView2.setText("at " + session.getMeetingLocation());
        sessionAsTutorViewHolder.sessionMessageTextView2.setText(session.getSessionRequestMessage());
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    class SessionAsTutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userNameTextView5, meetingDateAndTimeTextView2, meetingLocationTextView2, sessionMessageTextView2;
        OnSessionAsTutorListener onSessionAsTutorListener;

        public SessionAsTutorViewHolder(@NonNull View itemView) {
            super(itemView);

            userNameTextView5 = itemView.findViewById(R.id.userNameTextView5);
            meetingDateAndTimeTextView2 = itemView.findViewById(R.id.meetingDateAndTimeTextView2);
            meetingLocationTextView2 = itemView.findViewById(R.id.meetingLocationTextView2);
            sessionMessageTextView2 = itemView.findViewById(R.id.sessionMessageTextView2);

            this.onSessionAsTutorListener = mOnSessionAsTutorListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onSessionAsTutorListener.onSessionAsTutorClick(getAdapterPosition());
        }
    }
    public interface OnSessionAsTutorListener {
        void onSessionAsTutorClick(int position);
    }
}
