package com.example.orion.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.orion.R;
import com.example.orion.databinding.FragmentTodoBinding;
import com.example.orion.databinding.FragmentTodoPlusContentBinding;

import com.example.orion.model.todo.AddTodoData;
import com.example.orion.model.todo.CreateTodoData;
import com.example.orion.model.todo.DeleteTodoData;
import com.example.orion.model.todo.EditTodoData;
import com.example.orion.model.todo.ReadTodoData;
import com.example.orion.model.todo.EditTodoCheckData;

import com.example.orion.view.adapter.TodoAdapter;
import com.example.orion.view.item.TodoItem;

import com.example.orion.viewModel.TodoViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodoFragment extends Fragment {


    private ArrayList<TodoItem> arrayList;
    private TodoAdapter todoAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FragmentTodoBinding binding_todo;
    private FragmentTodoPlusContentBinding binding_plus_todo;
    private TodoViewModel todoViewModel;

    private DatePickerDialog.OnDateSetListener callbackMethod;
    private String username;
    private String curYear, curMonth, curDay;




    public TodoFragment(){

    }

    Context ct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* binding */
        binding_todo = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container,false);
        View v = binding_todo.getRoot();

        ct = container.getContext();

        linearLayoutManager = new LinearLayoutManager(ct);
        binding_todo.rvTodo.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        todoAdapter = new TodoAdapter(ct,arrayList);
        binding_todo.rvTodo.setAdapter(todoAdapter);


        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        // username 받아옴
        username = getArguments().getString("username");
        System.out.println("------ TodoFragment로 받음--------- : " + username);


        // 현재 날짜 불러옴
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");

        curYear = year.format(date);
        curMonth = month.format(date);
        curDay = day.format(date);

        String curDate = sdf.format(date);

        this.InitializeListener(curDate);

        // 현재 날짜로 세팅
        binding_todo.dayTodo.setText(curDate);

        // db내용 불러오기 -> read
        todoViewModel.readTodo(new ReadTodoData(username, curDate));

        // 서버에서 정상적인 값을 받으면
        todoViewModel.readResult.observe(getViewLifecycleOwner(), res -> {
            Boolean todo_check = false;
            System.out.println("*****read todo 서버에서 값 잘 받음*******");

            //readCode.set(res.getCode());

            //ArrayList에 추가
            if (res.getCode() == 200) { // 데이터 존재
                if (!res.getContent1().equals(".")) {
                    if (res.getCheck1() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo1 = new TodoItem(todo_check, res.getContent1());
                    arrayList.add(dataTodo1); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent2().equals(".")) {
                    if (res.getCheck2() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo2 = new TodoItem(todo_check, res.getContent2());
                    arrayList.add(dataTodo2); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent3().equals(".")) {
                    if (res.getCheck3() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo3 = new TodoItem(todo_check, res.getContent3());
                    arrayList.add(dataTodo3); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent4().equals(".")) {
                    if (res.getCheck4() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo4 = new TodoItem(todo_check, res.getContent4());
                    arrayList.add(dataTodo4); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent5().equals(".")) {
                    if (res.getCheck5() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo5 = new TodoItem(todo_check, res.getContent5());
                    arrayList.add(dataTodo5); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent6().equals(".")) {
                    if (res.getCheck6() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo6 = new TodoItem(todo_check, res.getContent6());
                    arrayList.add(dataTodo6); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent7().equals(".")) {
                    if (res.getCheck7() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo7 = new TodoItem(todo_check, res.getContent7());
                    arrayList.add(dataTodo7); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent8().equals(".")) {
                    if (res.getCheck8() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo8 = new TodoItem(todo_check, res.getContent8());
                    arrayList.add(dataTodo8); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent9().equals(".")) {
                    if (res.getCheck9() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo9 = new TodoItem(todo_check, res.getContent9());
                    arrayList.add(dataTodo9); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                }
                if (!res.getContent10().equals(".")) {
                    if (res.getCheck10() == 1) {
                        todo_check = true;
                    } else {
                        todo_check = false;
                    }
                    TodoItem dataTodo10 = new TodoItem(todo_check, res.getContent10());
                    arrayList.add(dataTodo10); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)
                }

                todoAdapter.notifyDataSetChanged(); //새로고침

            } else if (res.getCode() == 204) { // 데이터 없음
                if (curDate.equals(binding_todo.dayTodo.getText().toString())) {
                    todoViewModel.createTodo(new CreateTodoData(username, curDate));
                    todoAdapter.notifyDataSetChanged(); //새로고침
                }
            } else { // 에러
            }
        });

//        todoViewModel.createResult.observe(getViewLifecycleOwner(), res -> {
//            if (res.getCode() == 200) {
//                arrayList.add(new SubjectItem("과목1", "00:00:00")); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)
//                stopwatchAdapter.notifyItemInserted(stopwatchAdapter.getItemCount() + 1);
//
//            }
//        });

        // 리사이클러뷰 아이템 클릭 이벤트
        todoAdapter.setOnItemClickListener (new TodoAdapter.OnItemClickListener(){

            // 삭제
            @Override
            public void onDeleteClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                builder.setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        String tempContent[] = new String[10];

                                        for (int i =0; i<10; i++){
                                            if(i < position){
                                                tempContent[i] = arrayList.get(i).getTv_todo();
                                            }else if(i<=arrayList.size()-2) {
                                                tempContent[i] = arrayList.get(i+1).getTv_todo();
                                            }else {
                                                tempContent[i]=".";
                                            }
                                        }

                                        todoViewModel.deleteTodo(new DeleteTodoData
                                                (username, curDate, tempContent[0],tempContent[1],
                                                        tempContent[2],tempContent[3], tempContent[4],
                                                        tempContent[5],tempContent[6],tempContent[7],
                                                        tempContent[8], tempContent[9]));
                                        todoViewModel.deleteResult.observe(getViewLifecycleOwner(), res -> {
                                            if (res.getCode() == 200) {
                                                arrayList.remove(position);
                                                todoAdapter.notifyItemRemoved(position);
                                                Toast.makeText(ct, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                                System.out.println("remove "+ position);

                                            }
                                        });
                                    }
                                })
                        .setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(ct, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .show();

            }

            // 편집
            @Override
            public void onEditClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                binding_plus_todo = FragmentTodoPlusContentBinding.inflate(inflater, container, false);
                builder.setView(binding_plus_todo.getRoot());

                String beforeContent = arrayList.get(position).getTv_todo();

                binding_plus_todo.btnTodoInsert.setText("변경"); // 버튼 -> 변경으로 바꾸기
                binding_plus_todo.btnTodoEt.setText(beforeContent); // 기존 데이터

                final AlertDialog dialog = builder.create();

                binding_plus_todo.btnTodoInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //새로 입력한 이름으로 업데이트
                        String todo_content = binding_plus_todo.btnTodoEt.getText().toString();
                        String todo_before_content = beforeContent;

                        todoViewModel.editTodo(new EditTodoData(position+1,todo_content, username, curDate));

                        todoViewModel.editResult.observe(getViewLifecycleOwner(), res -> {
                            if (res.getCode() == 200) {
                                TodoItem ary = new TodoItem(false, todo_content);

                                arrayList.set(position, ary);
                                todoAdapter.notifyItemChanged(position); //새로고침

                                dialog.dismiss();
                            }
                        });
                    }
                });

                dialog.show();
            }

            // 체크박스 편집
            @Override
            public void onEditCheckClick(View v, int position) {
                if (arrayList.get(position).getCb_todo() == false) {
                    todoViewModel.updateCheckTodo(new EditTodoCheckData(position + 1, 1, username, curDate));

                } else {
                    todoViewModel.updateCheckTodo(new EditTodoCheckData(position + 1, 0, username, curDate));
                }

                String content = arrayList.get(position).getTv_todo();
                boolean check = !(arrayList.get(position).getCb_todo());

                TodoItem ary = new TodoItem(check, content);
                arrayList.set(position, ary);
                todoAdapter.notifyItemChanged(position); //새로고침

//                todoViewModel.checkResult.observe(getViewLifecycleOwner(), res -> {
//                    if (res.getCode() == 200) {
//                        String content = arrayList.get(position).getTv_todo();
//                        boolean check = res.getMessage() == "1"? true:false;
//                        TodoItem ary = new TodoItem(check, content);
//
//                        arrayList.set(position, ary);
//                        todoAdapter.notifyItemChanged(position); //새로고침
//                    }
//                });
            }
        });

        binding_todo.btnPlusTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("추가버튼 눌렀음1");

                if (todoAdapter.getItemCount() >= 10) {
                    Toast.makeText(ct, "할일 추가는 10개까지만 가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                    binding_plus_todo = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_plus_content, container,false);
                    builder.setView(binding_plus_todo.getRoot());

                    final AlertDialog dialog = builder.create();

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    binding_plus_todo.btnTodoInsert.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("FragmentLiveDataObserve") // 이게 뭐지
                        @Override
                        public void onClick(View view) {

                            //사용자 입력한 내용
                            String todo_content = binding_plus_todo.btnTodoEt.getText().toString();
                            todoViewModel.addTodo(new AddTodoData(username, curDate, todo_content, arrayList.size()+1));
                            TodoItem dataSubject = new TodoItem(false, todo_content );
                            arrayList.add(dataSubject); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)

                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }


            }
        });

        binding_todo.btnTodoDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoAdapter.notifyDataSetChanged();
                String date = binding_todo.dayTodo.getText().toString();

                int y = Integer.parseInt(date.substring(0, 4));
                int m = Integer.parseInt(date.substring(5, 7));
                int d = Integer.parseInt(date.substring(8, 10));

                DatePickerDialog dialog = new DatePickerDialog(getContext(), callbackMethod, y, m-1 ,d);
                dialog.show();

            }
        });


        return v;
    }

    public void InitializeListener(String curDate) {
        todoAdapter.notifyDataSetChanged();
        callbackMethod = new DatePickerDialog.OnDateSetListener()  {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)  {
                todoAdapter.notifyDataSetChanged();
                String mm = Integer.toString(monthOfYear+1);
                String dd = Integer.toString(dayOfMonth);

                mm = String.format("%02d", Integer.parseInt(mm));
                dd = String.format("%02d", Integer.parseInt(dd));

                String selDate =year+"."+mm+"."+dd;
                System.out.println(selDate);
                arrayList.clear();
                todoAdapter.notifyDataSetChanged();
                todoViewModel.readTodo(new ReadTodoData(username,selDate));

                binding_todo.dayTodo.setText(selDate);
//                selected_day = textview_date.getText().toString();    //textview 선택된 날짜로 변경

                if (!curDate.equals(selDate)) {
                    binding_todo.btnPlusTodo.setVisibility(View.INVISIBLE);
                } else {
                    binding_todo.btnPlusTodo.setVisibility(View.VISIBLE);
                }

            }
        };
    }

}