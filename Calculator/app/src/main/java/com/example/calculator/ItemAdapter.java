package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private List<OperationItem> operationItemList;
    private  LayoutInflater layoutInflater;
    public ItemAdapter(Context context ,List<OperationItem> operationItemList){
        this.context = context;
        this.operationItemList = operationItemList ;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(operationItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return operationItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView operation , operand;
        public ViewHolder( View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.textView_operation);
            operand = itemView.findViewById(R.id.textView_operand);

        }
        public void  bindItem(OperationItem operationItem){
            operation.setText(Utils.getOperationAsString(operationItem.getOperation()));
            operand.setText(operationItem.getValue());
        }
    }
}