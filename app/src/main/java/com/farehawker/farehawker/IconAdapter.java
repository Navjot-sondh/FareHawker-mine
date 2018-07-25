package com.farehawker.farehawker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farehawker.farehawker.R;
import com.farehawker.farehawker.Icon;


import java.util.List;


public class IconAdapter extends RecyclerView.Adapter<IconAdapter.GroceryViewHolder>{
    private List<Icon> horizontalGrocderyList;
    Context context;

    public IconAdapter(List<Icon> horizontalGrocderyList, Context context){
        this.horizontalGrocderyList= horizontalGrocderyList;
        this.context = context;
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_list_row, parent, false);
        GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, final int position) {
        holder.imageView.setImageResource(horizontalGrocderyList.get(position).getProductImage());
        holder.txtview.setText(horizontalGrocderyList.get(position).getProductName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String productName = horizontalGrocderyList.get(position).getProductName().toString();
                Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
                switch(productName)
                {
                    case "FLIGHT":Intent intent = new Intent(context,OneWay.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case "HOTEL": intent = new Intent(context,HotelSearch.class);
                    context.startActivity(intent);
                    case "HELICOPTER":
                        break;
                    case "PRIVET JETS":
                        break;
                    case "GROUP BOOKING":
                        break;
                    default:
                        break;
                }//End of switch

            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalGrocderyList.size();
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtview;
        public GroceryViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.icon_imageView);
            txtview=view.findViewById(R.id.icon_textView);
        }
    }
}