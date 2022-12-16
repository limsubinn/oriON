package com.example.orion.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orion.R;
import com.example.orion.databinding.ItemSubjectBinding;
import com.example.orion.databinding.ItemSubjectChartBinding;
import com.example.orion.view.item.SubjectItem;

import java.util.ArrayList;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder>{

    Context context;
    ArrayList<SubjectItem> arrayList;

    // ViewHolder 객체
    class ViewHolder extends RecyclerView.ViewHolder {

        ItemSubjectChartBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSubjectChartBinding.bind(itemView);
        }

        // 레이아웃 연결
        void bindItem(SubjectItem item){
            binding.tvSubject.setText(item.getSubject());
            binding.tvSubjectTime.setText(item.getTime());
        }
    }

    public ChartAdapter(Context context, ArrayList<SubjectItem> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    // ViewHolder 만들어질 때 실행할 동작
    public ChartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_subject_chart, parent, false);
        return new ChartAdapter.ViewHolder(itemView);
    }


    @Override
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(arrayList.get(position));
    }

    @Override
    // 표현할 Item의 총 개수
    public int getItemCount() {
        return arrayList.size();
    }
}
