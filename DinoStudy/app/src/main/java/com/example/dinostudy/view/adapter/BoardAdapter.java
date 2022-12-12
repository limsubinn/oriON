package com.example.dinostudy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ItemBoardBinding;
import com.example.dinostudy.view.item.BoardItem;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{
    Context context;
    ArrayList<BoardItem> arrayList;

    //아이템 클릭 리스너 인터페이스
    public interface OnItemClickListener{
        void onItemClick(View v, int position); // 아이템 클릭
        void onEditClick(View v, int position); // 편집
        void onDeleteClick(View v, int position); // 삭제
    }

    //리스너 객체 참조 변수
    private OnItemClickListener mListener = null;

    //리스너 객체 참조를 어댑터에 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // ViewHolder 객체
    class ViewHolder extends RecyclerView.ViewHolder {

        ItemBoardBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBoardBinding.bind(itemView);
        }

        // 레이아웃 연결
        void bindItem(BoardItem item){
            binding.comment.setText(Integer.toString(item.getComment()));
            binding.boardDate.setText(item.getDate());
            binding.nickname.setText(item.getUsername());
            binding.tvTitle.setText(item.getTitle());
        }
    }

    public BoardAdapter(Context context, ArrayList<BoardItem> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    // ViewHolder 만들어질 때 실행할 동작
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_board, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(arrayList.get(position));

        // 아이템 클릭
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition ();
                if (position!=RecyclerView.NO_POSITION){
                    if (mListener!=null){
                        mListener.onItemClick(v, position);
                    }
                }
            }
        });

        // 아직 편집 버튼, 삭제 버튼 안 만들어놔서 일단 주석처리함.
//        // 편집 버튼
//        holder.binding.btnEdit.setOnClickListener (new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION){
//                    if (mListener != null){
//                        mListener.onEditClick(v, position);
//                    }
//                }
//            }
//        });
//
//        // 삭제 버튼
//        holder.binding.btnDelete.setOnClickListener (new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION){
//                    if (mListener != null){
//                        mListener.onDeleteClick(v, position);
//                    }
//                }
//            }
//        });
    }

    @Override
    // 표현할 Item의 총 개수
    public int getItemCount() {
        return arrayList.size();
    }

}