package com.geowind.hunong.agricultureLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

/**
 * Created by zhangwen on 16-8-1.
 */
public class ArticleDetailsActivity extends Activity{

    private TextView mTv_title;
    private WebView mWv_content;
    private Button mBt_back;
    private Button mBt_shell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mBt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        //加载webView时必须加载头部
        mWv_content.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                MaterialViewPagerHelper.injectHeader(mWv_content, true);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWv_content.loadUrl(getIntent().getStringExtra("articleUrl"));
        mTv_title.setText(getIntent().getStringExtra("title"));
    }

    private void initView() {
        setContentView(R.layout.activity_article_details);
        mTv_title = (TextView) findViewById(R.id.tv_article_title);
        mWv_content = (WebView) findViewById(R.id.wv_article_content);
        mBt_back = (Button) findViewById(R.id.bt_lib_back);
        mBt_shell = (Button) findViewById(R.id.bt_lib_share);
    }
}
