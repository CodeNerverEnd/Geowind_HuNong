package com.geowind.hunong.myview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.geowind.hunong.R;
import com.geowind.hunong.global.basepage.BaseTagPage;
import com.geowind.hunong.global.basepage.EnquryBaseTagPage;
import com.geowind.hunong.global.basepage.HomeBaseTagPage;
import com.geowind.hunong.global.basepage.MapBaseTagPage;
import com.geowind.hunong.global.basepage.MsgBaseTagPage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwen on 16-7-18.
 */
//主界面的fragment
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_home)
    private ViewPager mViewPager;
    @ViewInject(R.id.rg_radio)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.ib_menu)
    private ImageButton mIb_menu;
    private List<BaseTagPage> pages=new ArrayList<BaseTagPage>();
    private int mSelectIndex=0;//设置当前的选择编号

    @Override
    public View initView() {
       View root=View.inflate(mMainActivity, R.layout.fragment_content_view,null);
        ViewUtils.inject(this,root);
        return root;
    }

    @Override
    public void initData() {
        pages.add(new HomeBaseTagPage(mMainActivity));
        pages.add(new MsgBaseTagPage(mMainActivity));
        pages.add(new MapBaseTagPage(mMainActivity));
        pages.add(new EnquryBaseTagPage(mMainActivity));
        MyAdapter adapter=new MyAdapter();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        mSelectIndex=0;
                        break;
                    case R.id.rb_msg:
                        mSelectIndex=1;
                        break;
                    case R.id.rb_enquiry:
                        mSelectIndex=2;
                        break;
                    case R.id.rb_my:
                        mSelectIndex=3;
                        break;
                    default:
                        break;
                }
                switchPage();
            }
        });
        mViewPager.setAdapter(adapter);
        switchPage();
        mRadioGroup.check(R.id.rb_home);
        if(mSelectIndex==0){
            mIb_menu.setVisibility(View.VISIBLE);
        }else {
            mIb_menu.setVisibility(View.GONE);
        }
        super.initData();
    }
//    监听事件

    @Override
    public void initEvent() {
        //菜单按钮的点击事件
        mIb_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mMainActivity.getSlidingMenu().toggle();//左侧菜单的开关
            }
        });
        super.initEvent();
    }

    //设置选择的页面
    private void switchPage() {
        mViewPager.setCurrentItem(mSelectIndex);
//        if(mSelectIndex!=0){
//            mMainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//        }else {
//            mMainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        }
    }
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseTagPage baseTagPage = pages.get(position);
            View root = baseTagPage.getRoot();
            container.addView(root);
            return root;
        }
    }
}
