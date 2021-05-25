package com.example.speedmath;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IntermediateAdapter extends RecyclerView.Adapter<IntermediateAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<OperationModel> mQuestionsList;

    public IntermediateAdapter(Context context, ArrayList<OperationModel> mQuestionsList) {
        this.context = context;
        this.mQuestionsList = mQuestionsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IntermediateAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position < 10) {
            holder.num_1.setText("" + mQuestionsList.get(position).getFirstTerm());
            holder.squareOperator.setText("2");
            holder.operator.setText("");
            holder.num_2.setText("");
            holder.result.setText("" + (int) mQuestionsList.get(position).getResult());
            holder.user_result.setText("" + (int) mQuestionsList.get(position).getUserResult());

            if (mQuestionsList.get(position).getResult() == mQuestionsList.get(position).getUserResult()) {
                Picasso.get().load(R.drawable.correct).into(holder.result_image);
            } else {
                Picasso.get().load(R.drawable.wrong).into(holder.result_image);
            }
        } else if (position < 20) {
            holder.num_1.setText("" + mQuestionsList.get(position).getFirstTerm());
            holder.num_2.setText("" + mQuestionsList.get(position).getSecondTerm());
            holder.operator.setText("/");
            holder.result.setText("" + mQuestionsList.get(position).getResult() + "%");
            holder.user_result.setText("" + mQuestionsList.get(position).getUserResult() + "%");

            if (mQuestionsList.get(position).getResult() == mQuestionsList.get(position).getUserResult()) {
                Picasso.get().load(R.drawable.correct).into(holder.result_image);
            } else {
                Picasso.get().load(R.drawable.wrong).into(holder.result_image);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView num_1;
        TextView num_2;
        TextView operator;
        TextView squareOperator;
        TextView result;
        TextView user_result;
        TextView square_placeholder;
        ImageView result_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            num_1 = itemView.findViewById(R.id.final_num_1);
            num_2 = itemView.findViewById(R.id.final_num_2);
            operator = itemView.findViewById(R.id.final_operator);
            squareOperator = itemView.findViewById(R.id.square_operator);
            result = itemView.findViewById(R.id.final_correct_result);
            user_result = itemView.findViewById(R.id.final_user_result);
            square_placeholder = itemView.findViewById(R.id.square_placeholder);
            result_image = itemView.findViewById(R.id.final_image);
        }
    }
}
