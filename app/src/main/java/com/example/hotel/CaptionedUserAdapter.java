package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CaptionedUserAdapter
        extends RecyclerView.Adapter<CaptionedUserAdapter.ViewHolder>{
    private Context context;
    private List<User> items;


    public CaptionedUserAdapter(Context context, List<User> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User users = items.get(position);
        CardView cardView = holder.cardView;
        TextView txtName =(TextView)cardView.findViewById(R.id.txtName);
        txtName.setText("Customer Name :" +users.getUserName());
        TextView txtEmail = (TextView)cardView.findViewById(R.id.txtEmail);
        txtEmail.setText("Customer Email :" +users.getEmail());
        TextView txtId = (TextView)cardView.findViewById(R.id.txtId);
        txtId.setText("Customer ID :"+users.getId());
        TextView txtRoomNo = (TextView)cardView.findViewById(R.id.txtRoomNo);
     if(users.getRoomNo().equals("null")||users.getRoomNo().equals("")||users.getRoomNo().isEmpty()) {
         txtRoomNo.setText("Room Number : Not booked yet ");
     }else {
         txtRoomNo.setText("Room Number : "+users.getRoomNo());
     }
        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}

