package com.example.dinostudy.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinostudy.R;
import com.example.dinostudy.view.item.SubjectItem;

import java.util.ArrayList;

public class WatchAdapter extends RecyclerView.Adapter<WatchAdapter.CustomViewHolder> {

    private ArrayList<SubjectItem> arrayList;
    private Context context;

    public WatchAdapter(ArrayList<SubjectItem> arrayList) {
        this.arrayList = arrayList;
        this.context= context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


        protected TextView tv_subject;
        protected TextView tv_subject_time;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            this.tv_subject_time = (TextView) itemView.findViewById(R.id.tv_subject_time);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            MenuItem Edit = contextMenu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = contextMenu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case 1001:  //편집 기능
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_watch_plus_subject, null, false);

                        builder.setView(view);
                        final EditText et_subject = (EditText)view.findViewById(R.id.et_subject);
                        final Button btn_subject_insert = (Button)view.findViewById(R.id.btn_subject_insert);
                        btn_subject_insert.setText("변경");

                        //기존 데이터
                        et_subject.setText(arrayList.get(getAdapterPosition()).getTv_subject());

                        final AlertDialog dialog = builder.create();
                        btn_subject_insert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //새로 입력한 이름으로 업데이트
                                String str_subject = et_subject.getText().toString();
                                String str_subject_time = "00:00:00";

                                SubjectItem ary = new SubjectItem(str_subject, str_subject_time);

                                arrayList.set(getAdapterPosition(), ary);
                                notifyItemChanged(getAdapterPosition()); //새로고침

                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                        break;

                    case 1002:  //삭제 기능
                        arrayList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), arrayList.size());

                        break;
                }

                return true;
            }
        };
    }

    @NonNull
    @Override
    public WatchAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchAdapter.CustomViewHolder holder, int position) {
        holder.tv_subject.setText(arrayList.get(position).getTv_subject());
        holder.tv_subject_time.setText(arrayList.get(position).getTv_subject_time());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_subject.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

}
