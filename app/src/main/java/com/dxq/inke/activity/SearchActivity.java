package com.dxq.inke.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxq.inke.R;
import com.dxq.inke.adapter.ResultAdapter;
import com.dxq.inke.adapter.live.SearchAdapter;
import com.dxq.inke.bean.LiveNodesBean;
import com.dxq.inke.bean.TypeBean;
import com.dxq.inke.bean.UsersBean;
import com.dxq.inke.http.ISearchService;
import com.dxq.inke.http.ServiceGenerator;
import com.dxq.inke.utils.Constant;
import com.dxq.inke.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.cancel)
    TextView mCancel;
    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.clean)
    ImageView mClean;
    @BindView(R.id.searchicon)
    ImageView mSearchicon;
    @BindView(R.id.recyle)
    RecyclerView mRecyle;
    @BindView(R.id.result)
    RecyclerView mResult;
    private SearchAdapter mSearchAdapter;
    private ResultAdapter mResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
        initData();
        getAll();
    }

    private void initView() {
        //推荐的栏目的数据集合
        //推荐的用户的数据集合
        mRecyle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mResult.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //设置一个空的Adapter，后面请求网络了再来更新数据
        mSearchAdapter = new SearchAdapter(new ArrayList<TypeBean>());
        mRecyle.setAdapter(mSearchAdapter);
        mResultAdapter = new ResultAdapter(new ArrayList<UsersBean>());
        mResult.setAdapter(mResultAdapter);
    }

    private void initData() {
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //如果搜索的关键字不为空，就去请求查询接口
                if (!TextUtils.isEmpty(editable.toString())) {
                    mClean.setVisibility(View.VISIBLE);
                    if (editable.length() > 2) {
                        //长度足够时，开始搜索
                        doSearch(editable.toString().trim());
                    }
                } else {
                    mClean.setVisibility(View.GONE);
                    mResult.setVisibility(View.GONE);
                }
            }
        });
        //todo 给adapter设置点击事件
    }

    //获取所有的推荐数据
    private void getAll() {
        ISearchService client = ServiceGenerator.getSingleTon().create(ISearchService.class);
        Call<ResponseBody> call = client.getRecommend();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray live_nodes = jsonObject.getJSONArray("live_nodes");
                        JSONArray user_nodes = jsonObject.getJSONArray("user_nodes");
                        JSONObject jo1 = user_nodes.getJSONObject(0);
                        JSONArray users = jo1.optJSONArray("users");

                        ArrayList<LiveNodesBean> searchTypesAchorBeanList = new ArrayList<>();
                        for (int i = 0; i < live_nodes.length(); i++) {
                            JSONObject tmp = live_nodes.optJSONObject(i);
                            LiveNodesBean liveNodesBean = JsonUtils.parseJson(tmp.toString(), LiveNodesBean.class);
                            searchTypesAchorBeanList.add(liveNodesBean);
                        }

                        ArrayList<UsersBean> usersBeanList = new ArrayList<>();
                        for (int i = 0; i < users.length(); i++) {
                            JSONObject tmp = users.optJSONObject(i);
                            UsersBean usersBean = JsonUtils.parseJson(tmp.toString(), UsersBean.class);
                            usersBeanList.add(usersBean);
                        }

                        //这里拿到了上下两块数据（类型主播和每日推荐主播）
                        ArrayList<TypeBean> typeBeanList = new ArrayList<>();
                        typeBeanList.addAll(searchTypesAchorBeanList);
                        typeBeanList.add(new TypeBean() {
                            @Override
                            public int getType() {
                                return TYPE_RECOMMEND_TITLE;
                            }
                        });
                        typeBeanList.addAll(usersBeanList);
                        mSearchAdapter.updateDatas(typeBeanList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void doSearch(String keyWord) {
        String url = Constant.getSearchKeyword(keyWord);
        ISearchService client = ServiceGenerator.getSingleTon().create(ISearchService.class);
        Call<ResponseBody> call = client.getSearchResult(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        if (TextUtils.isEmpty(json)) {
                            return;
                        }
                        ArrayList<UsersBean> result = new ArrayList<>();
                        JSONObject js = new JSONObject(json);
                        JSONArray users = js.getJSONArray("users");
                        for (int i = 0; i < users.length(); i++) {
                            JSONObject tmp = users.getJSONObject(i);
                            UsersBean bean = JsonUtils.parseJson(tmp.toString(), UsersBean.class);
                            result.add(bean);
                        }
                        //展示搜索结果
                        showSearchResult(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void showSearchResult(ArrayList<UsersBean> result) {
        if (result != null) {
            mResult.setVisibility(View.VISIBLE);
            mResultAdapter.updateData(result);
        }
    }

    @OnClick({R.id.cancel, R.id.clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                if (!TextUtils.isEmpty(mSearch.getText())) {
                    mSearch.setText("");
                } else {
                    onBackPressed();
                }
                break;
            case R.id.clean:
                mSearch.setText("");
                break;
        }
    }
}
