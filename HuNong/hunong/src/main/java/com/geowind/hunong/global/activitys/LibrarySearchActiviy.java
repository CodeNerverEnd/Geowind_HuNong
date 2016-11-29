package com.geowind.hunong.global.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.geowind.hunong.R;
import com.geowind.hunong.dao.impl.LibSearchDaoImpl;
import com.geowind.hunong.entity.LibSearch;
import com.geowind.hunong.global.adapter.SearchAdapter;
import com.geowind.hunong.json.LibSearchJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.view.SearchView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by zhangwen on 2016/11/16.
 */

public class LibrarySearchActiviy extends BaseActivity implements SearchView.SearchViewListener {
    /**
     * 搜索结果列表view
     */
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;

    /**
     * 数据库数据，总数据
     */
    private List<LibSearch> dbData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    private List<LibSearch> resultData;

    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;
    private TextView mTv_title;
    private List<LibSearch> mLibSearches;
    private LibSearchDaoImpl mLibSearchDao;
    private boolean mDbHasKeyword;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        LibrarySearchActiviy.hintSize = hintSize;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_search);
        initData();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);
        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String url = resultData.get(position).getUrl();
                Intent intent=new Intent(LibrarySearchActiviy.this,ArticleDetailsActivity.class);
                intent.putExtra("articleUrl",url);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化热搜版数据
        getHintData();
        //初始化搜索结果数据
//        getResultData(null);
    }

    /**
     * 获取db 数据
     */
    private void getServerData(String text) {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","searchLib");
        params.add("keyword",text);
        client.post(MyConstants.LIBRARYSEARCH, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result=new String(responseBody);
                System.out.println("文库搜索=========="+result);
                    if(TextUtils.isEmpty(result)){
                    Toast.makeText(getApplicationContext(),"没有要找的内容",Toast.LENGTH_LONG).show();
                }else {
                        lvResults.setVisibility(View.VISIBLE);
                        searchView.setHintVisible(View.GONE);
                        if(resultData.size()>0)
                            resultData.clear();
                    resultData.addAll(LibSearchJson.paseJson(result));

                        if (resultAdapter == null) {
                            resultAdapter = new SearchAdapter(LibrarySearchActiviy.this, resultData, R.layout.item_bean_list);
                            lvResults.setAdapter(resultAdapter);
                        } else {
                            resultAdapter.notifyDataSetChanged();
                        }
                }


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"连接失败"+statusCode,Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        mLibSearchDao = new LibSearchDaoImpl(getApplicationContext());
        mLibSearches = mLibSearchDao.findAll();
        if(mLibSearches !=null&& mLibSearches.size()>0){
            for (LibSearch libSearch : mLibSearches){
                hintData.add(libSearch.getTitle());
                System.out.println("hintData====="+libSearch.getTitle());
            }
        }

        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 获取搜索结果data和adapter
     */
   private void getResultData(String text) {
       int size = 100;
       resultData=new ArrayList<LibSearch>(size);
        //从数据库获取数据
        getServerData(text);

    }


    @Override
    public void onRefreshAutoComplete(String text) {

    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        for(LibSearch search:mLibSearchDao.findAll()){
            if(search.getTitle().equals(text))
                mDbHasKeyword = true;
        }
        if(!mDbHasKeyword){
            LibSearch libSearch=new LibSearch();
            libSearch.setTitle(text);
            mLibSearchDao.insert(libSearch);
            mDbHasKeyword=false;
        }

        //更新result数据
        getResultData(text);


    }

}
