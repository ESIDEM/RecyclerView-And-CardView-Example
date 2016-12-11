package com.example.esidemjnr.techdepo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by ESIDEM jnr on 12/10/2016.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private Context mContext;
   // private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;
      OnItemClickListener mItemClickListener;


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //each data item is just a string in this case
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView instructionUrl;
        public ImageView thumbnailImageView;
        public ViewHolder(View v) {
            super(v);

            subtitleTextView = (TextView) v.findViewById(R.id.card_text);
            titleTextView = (TextView) v.findViewById(R.id.card_title);
            thumbnailImageView = (ImageView) v.findViewById(R.id.card_image);
            instructionUrl = (TextView) v.findViewById(R.id.instructionText);


            v.setOnClickListener(this);
        }
                @Override
                public void onClick(View view) {


                    Intent detailIntent = new Intent(view.getContext(), Details.class);
                    detailIntent.putExtra("title",titleTextView.getText().toString() );
                    detailIntent.putExtra("url", instructionUrl.getText().toString());

                    mContext.startActivity(detailIntent);

                }





    }
            public interface OnItemClickListener{
                public void onItemClick(View view, int Position);
            }

                public void SetOnItemClickListner(final OnItemClickListener mItemClickListener){
                    this.mItemClickListener = mItemClickListener;
                }
    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
       // mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new ViewHolder(itemView);
    }




//        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
//        titleTextView.setTypeface(titleTypeFace);
//
//        Typeface subtitleTypeFace =
//                Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
//        subtitleTextView.setTypeface(subtitleTypeFace);
//
//        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
//        detailTextView.setTypeface(detailTypeFace);
//
//        detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(recipe.label)));
//        return convertView;
//    }


    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {

        // - get element from arraylist at this position
        // - replace the contents of the view with that element

        Recipe recipe = mDataSource.get(position);

        holder.titleTextView.setText(recipe.getTitle());
        holder.subtitleTextView.setText(recipe.description);
        holder.instructionUrl.setText(recipe.instructionUrl);
        Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(holder.thumbnailImageView);

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
}
