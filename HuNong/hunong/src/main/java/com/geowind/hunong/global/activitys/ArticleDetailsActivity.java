package com.geowind.hunong.global.activitys;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

/**
 * Created by zhangwen on 16-8-1.
 */
public class ArticleDetailsActivity extends BaseActivity{

    private WebView mWv_content;
    private ImageButton mBt_back;
    private ProgressBar mProgressBar;
    private TextView mTv_title;

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
        mWv_content.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWv_content.getSettings().setJavaScriptEnabled(true);
        mWv_content.getSettings().setSupportZoom(true);
        mWv_content.getSettings().setBuiltInZoomControls(true);
        mWv_content.getSettings().setUseWideViewPort(true);
        mWv_content.getSettings().setLoadWithOverviewMode(true);
        mWv_content.getSettings().setUseWideViewPort(true);
        mWv_content.getSettings().setAppCacheEnabled(true);
        mWv_content.getSettings().setDomStorageEnabled(true);

        //加载webView时必须加载头部
        mWv_content.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                MaterialViewPagerHelper.injectHeader(mWv_content, true);
                mProgressBar.setVisibility(View.GONE);
//                System.out.println("网页加载完成");
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
               // System.out.println(url+"网页正在加载");
            }
        });
       // mWv_content.loadUrl("http://geek.csdn.net/news/detail/172477");
      mWv_content.loadUrl(getIntent().getStringExtra("articleUrl"));
        mWv_content.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                mProgressBar.setBackgroundColor(Color.WHITE);
                mProgressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_article_details);
        mWv_content = (WebView) findViewById(R.id.wv_article_content);
        mBt_back = (ImageButton) findViewById(R.id.return_btn);
        mTv_title = (TextView) findViewById(R.id.title);
        mProgressBar = new ProgressBar(ArticleDetailsActivity.this);
        mTv_title.setText("");
        mProgressBar.setVisibility(View.VISIBLE);

    }
}
