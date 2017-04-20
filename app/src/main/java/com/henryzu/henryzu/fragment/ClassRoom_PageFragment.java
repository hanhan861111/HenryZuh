package com.henryzu.henryzu.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.activity.ChatActivity;
import com.henryzu.henryzu.activity.VideoActivity;
import com.henryzu.henryzu.adapter.RvAdapter_Doctors;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.build.TitleBuilder;
import com.henryzu.henryzu.interfaces.OnItemClickListener;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.views.ShowingPage;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class ClassRoom_PageFragment extends BaseFragment {

    private ListView lv_classroom;
    private RecyclerView rv_classroom;
    private ArrayList<String> doctorList;
    private Button bt_choose_classroom;
    private TextView tv_classroom;

    @Override
    protected void createTitleView(ShowingPage showingPage) {

        new TitleBuilder(showingPage).setMiddleText("亨利族", 30).setLeftImageRes(R.mipmap.amr).setRightImageRes(R.mipmap.ams).build();
    }

    @Override
    protected void onload() {

        CommonUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {

                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                initListView();
                initRecyclerView();


            }
        });


    }

    private void initButton(final Integer position) {
        bt_choose_classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name",position);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerView() {
        rv_classroom.setLayoutManager(new GridLayoutManager(getActivity(),2));
        RvAdapter_Doctors mRvAdapter_Doctors=new RvAdapter_Doctors(getActivity(),doctorList);
        rv_classroom.setAdapter(mRvAdapter_Doctors);
        //recyclerview点击事件 接口回调
        mRvAdapter_Doctors.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
               tv_classroom.setText("您选的是："+position+"医生");
                initButton(position);
            }
        });

    }

    private void initListView() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("亨利族视频" + i);
        }
        lv_classroom.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list));
        lv_classroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommonUtils.startActivity(getActivity(), VideoActivity.class);
            }
        });
    }


    private void initView(View view) {
        doctorList = new ArrayList<String>();
        lv_classroom = (ListView) view.findViewById(R.id.lv_classroom);
        rv_classroom = (RecyclerView) view.findViewById(R.id.rv_classroom);
        bt_choose_classroom = (Button) view.findViewById(R.id.bt_choose_classroom);
        tv_classroom = (TextView) view.findViewById(R.id.tv_classroom);
        doctorList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490873832581&di=d9453353d77df122ff04b6ce47e00433&imgtype=0&src=http%3A%2F%2Fwww.med66.com%2Fupload%2Fhtml%2F2013%2F09%2F11%2Fdyx14e67a24e6d143f59aa2a42941f24b3f.jpg");
        doctorList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490873832580&di=bfc6d65a5d8fdbecbb7d1f4189e6dc81&imgtype=0&src=http%3A%2F%2Fwww.med66.com%2Fupload%2Fhtml%2F2013%2F09%2F10%2Fdyx4a5a0c80adfd42a486c1ff3b6de73db6.jpg");
        doctorList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490873832580&di=63a41c2568440d6772361333760286bb&imgtype=0&src=http%3A%2F%2Fwww.med66.com%2Fupload%2Fhtml%2F2013%2F09%2F08%2Fdyx2b30fa37a8b14905ad08d6d3bd5e8967.jpg");
        doctorList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490873881884&di=7366951be3afb9be575cbf486d19fd61&imgtype=0&src=http%3A%2F%2Fimg.zhzyw.org%2F20120314162912_1804.jpg");
    }


    @Override
    public View CreateSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.classroom_fragment, null);
        initView(view);
        return view;
    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return true;
    }


}
