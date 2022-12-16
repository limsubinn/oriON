package com.example.orion.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.orion.R;
import com.example.orion.databinding.ItemCommentBinding;
import com.example.orion.view.item.CommentItem;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    Context context;
    ArrayList<CommentItem> arrayList;
    String username;

    //아이템 클릭 리스너 인터페이스
    public interface OnItemClickListener{
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

        ItemCommentBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCommentBinding.bind(itemView);
        }

        // 레이아웃 연결
        void bindItem(CommentItem item, String username){
            binding.commentContent.setText(item.getComment_content());
            binding.nickname.setText(item.getNickname());
            binding.commentDate.setText(item.getComment_date());

            // 현재 로그인한 유저와 댓글 작성자가 같으면 편집, 삭제 버튼을 보인다.
            if (username.equals(item.getNickname())) {
                binding.btnEdit.setVisibility(View.VISIBLE);
                binding.btnDelete.setVisibility(View.VISIBLE);
            } else {
                binding.btnEdit.setVisibility(View.INVISIBLE);
                binding.btnDelete.setVisibility(View.INVISIBLE);
            }
        }
    }

    public CommentAdapter(Context context, ArrayList<CommentItem> arrayList, String username) {
        this.arrayList = arrayList;
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    // ViewHolder 만들어질 때 실행할 동작
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(arrayList.get(position), username);

        // 편집 버튼
        holder.binding.btnEdit.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    if (mListener != null){
                        mListener.onEditClick(v, position);
                    }
                }
            }
        });

        // 삭제 버튼
        holder.binding.btnDelete.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    if (mListener != null){
                        mListener.onDeleteClick(v, position);
                    }
                }
            }
        });
    }

    @Override
    // 표현할 Item의 총 개수
    public int getItemCount() {
        return arrayList.size();
    }

}