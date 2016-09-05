package com.geowind.hunong.map;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.geowind.hunong.R;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.json.TaskJson;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.oguzdev.circularfloatingactionmenu.lib.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.lib.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.lib.SubActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by zhangwen on 16-7-9.
 */
public class BaiduMapActivity extends Activity {

    private static final int CHANGELATING = 1;
    private static final int CHANGEPOI = 2;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private MarkerOptions mMark;
    private BDLocation mCurrentLocation;
    private boolean isFirst = true;
    private LatLng mLatLng;
    private ImageButton mIb_quxiao;
    private List<Task> mTasks;
    private List<OverlayOptions> mMarkers;
    private int[] mMakerIconsRes;
    private MyPopTextView mTv_address;
    private MyPopTextView mTv_taskType;
    private MyPopTextView mTv_plantsType;
    private MyPopTextView mTv_machineName;
    private MyPopTextView mTv_taskTime;
    private MyPopTextView mTv_taskState;
    private TextView mTv_note;
    private BitmapUtils mBitmapUtils;
    private MapStatusUpdate mMapStatusUpdate;
    private MapStatus mMapStatus;
    private TextView mTv_task_name;
    private FloatingActionMenu mLeftLowerMenu;
    private ImageView mFabIconNew;
    private ImageView mRlIcon1;
    private ImageView mRlIcon2;
    private ImageView mRlIcon3;
    private ImageView mRlIcon4;
    private AlertDialog mTaskDetailsDialog;
    private ImageButton mIb_map_back;
    private ImageView mIv_task;
    private PoiSearch mPoiSearch;
    private DialogPlus mTaskListdialog;
    private List<String> mPois;
    private List<LatLng> mLatLngs;
    private DialogPlus mMapPoiDialog;
    private ArrayAdapter mPoiAdapter;
    private DialogPlus mMapModeDialog;
    private DialogPlus mRoutPlanDialog;
    private RoutePlanSearch mRoutePlanSearch;
    private LatLng mPt_end;
    private DrivingRouteOverlay mDrivingRouteOverlay;
    private TextView mTv_StartInfo;
    private TextView mTv_endInfo;
    private List<PoiInfo> mAllPoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPois=new ArrayList<String>();
        mLatLngs=new ArrayList<LatLng>();
        mTv_endInfo = new TextView(getApplicationContext());
        mTv_StartInfo = new TextView(getApplicationContext());
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        /**
         * 发送key给服务 校验key是否正确
         */
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.baidumap);
        /**
         * 初始化百度定位
         */
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        //任务标注
        mMakerIconsRes = new int[]{R.drawable.icon_mark1, R.drawable.icon_mark2, R.drawable.icon_mark3,
                R.drawable.icon_mark4, R.drawable.icon_mark5, R.drawable.icon_mark6};
        initView();
        initData();
        initEvent();

    }

    private void initEvent() {
        mIb_map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
            if(TextUtils.isEmpty(marker.getTitle())){
                   //什么也不做
                }
                else {
                    int i=Integer.parseInt(marker.getTitle());
                    mMark=(MarkerOptions)mMarkers.get(i);
                    LatLng lating=new LatLng(mTasks.get(i).getLatitude(),mTasks.get(i).getLongitude());
                    if(mMapStatusUpdate==null)
                        showLoction(lating);
                    else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(BaiduMapActivity.this);
                        View view=View.inflate(getApplicationContext(),R.layout.map_popwindow,null);
                        //获取弹出框中的组件
                        mTv_task_name = (TextView) view.findViewById(R.id.tv_taskName);
                        mIb_quxiao = (ImageButton) view.findViewById(R.id.ib_pop_quxiao);
                        mTv_address = (MyPopTextView) view.findViewById(R.id.tv_pop_address);
                        mTv_taskType = (MyPopTextView) view.findViewById(R.id.tv_pop_taskType);
                        mTv_plantsType = (MyPopTextView) view.findViewById(R.id.tv_pop_plantsType);
                        mTv_machineName = (MyPopTextView)view. findViewById(R.id.tv_pop_machineName);
                        mTv_taskTime = (MyPopTextView)view. findViewById(R.id.tv_pop_taskTime);
                        mTv_taskState = (MyPopTextView)view. findViewById(R.id.tv_pop_taskState);
                        mTv_note = (TextView)view. findViewById(R.id.tv_pop_note);
                        mIv_task=  (ImageView) view.findViewById(R.id.iv_map_pop_task);
                        int n=i+1;
                        mTv_task_name.setText("任务"+n+"详情");
                        mTv_address.setContent(mTasks.get(i).getFaddr());
                        mTv_taskType.setContent(mTasks.get(i).getType());
                        mTv_machineName.setContent(mTasks.get(i).getMstyle());
                        mTv_plantsType.setContent(mTasks.get(i).getCropType());
                        mTv_taskTime.setContent(mTasks.get(i).getDate());
                        mTv_taskState.setContent(mTasks.get(i).getDate());
                        mTv_note.setText(mTasks.get(i).getNote());
                        mBitmapUtils.display(mIv_task,mTasks.get(i).getFpic());

                        builder.setView(view);
                        mTaskDetailsDialog = builder.create();
                        //设置dialog背景为透明
                        Window window=mTaskDetailsDialog.getWindow();
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.alpha=0.8f;
                        window.setAttributes(attributes);
                        mTaskDetailsDialog.show();
                        //弹出框的取消事件
                        mIb_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mTaskDetailsDialog.dismiss();
                            }
                        });
                    }
                }

                return true;
            }
        });
        //浮动菜单的点击事件
        FloatMenuItemClickListener listener=new FloatMenuItemClickListener();

        mRlIcon1.setOnClickListener(listener);
        mRlIcon2.setOnClickListener(listener);
        mRlIcon3.setOnClickListener(listener);
        mRlIcon4.setOnClickListener(listener);
    }
    private  class PopAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view=View.inflate(getApplicationContext(),R.layout.item_pop_map_task_lv,null);
            MyPopTextView tv_adress= (MyPopTextView) view.findViewById(R.id.tv_pop_map_address);
            MyPopTextView tv_time= (MyPopTextView) view.findViewById(R.id.tv_pop_map_time);
            TextView tv_taskName= (TextView) view.findViewById(R.id.tv_pop_map_taskName);
            tv_adress.setContent(mTasks.get(i).getFaddr());
            tv_time.setContent(mTasks.get(i).getDate());
            tv_taskName.setText("任务"+(i+1));
            return view;
        }
    }
  private OnItemClickListener mOnItemClickListener=  new OnItemClickListener() {
        @Override
        public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
            if(dialog.equals(mTaskListdialog)){
                LatLng la=new LatLng(mTasks.get(position).getLatitude(),mTasks.get(position).getLongitude());
                showLoction(la);
            }else if(dialog.equals(mMapModeDialog)){
                switch (position){
                    case 0:
                        if(mBaiduMap.getMapType()!=BaiduMap.MAP_TYPE_NORMAL)
                            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        if(mBaiduMap.isTrafficEnabled())
                            mBaiduMap.setTrafficEnabled(false);
                        else mBaiduMap.setTrafficEnabled(true);
                        break;
                    case 2:
                        if(mBaiduMap.getMapType()!=BaiduMap.MAP_TYPE_SATELLITE)
                            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        else mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                    default:
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        break;
                }
            }else if(dialog.equals(mMapPoiDialog)){
                LatLng poiLating=mLatLngs.get(position);
                if(mAllPoi.size()!=0)
                mTv_endInfo.setText(mAllPoi.get(position).address);
                //显示路线规划结果
                routePaln(poiLating);
            }else if(dialog.equals(mRoutPlanDialog)) {
                if (position == 0)
                    showLoction(mLatLng);
                else
                    routePaln(new LatLng(mTasks.get(position-1).getLatitude(), mTasks.get(position-1).getLongitude()));
            }
            dialog.dismiss();
        }
    };

    //路线规划

    private void routePaln(LatLng latLng) {
        mPt_end = latLng;
        mRoutePlanSearch = RoutePlanSearch.newInstance();
        PlanNode fromPlanNode=PlanNode.withLocation(mLatLng);
        PlanNode toPlanNode=PlanNode.withLocation(mPt_end);
        DrivingRoutePlanOption drivingRoutePlanOption=new DrivingRoutePlanOption();
        drivingRoutePlanOption.from(fromPlanNode);
        drivingRoutePlanOption.to(toPlanNode);
        mRoutePlanSearch.drivingSearch(drivingRoutePlanOption);
        OnGetRoutePlanResultListener onGetRoutePlanResultListener=new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                if(mDrivingRouteOverlay!=null)
                    mDrivingRouteOverlay.removeFromMap();
                mDrivingRouteOverlay = new DrivingRouteOverlay(mBaiduMap);
                DrivingRouteLine drivingRouteLine=drivingRouteResult.getRouteLines().get(0);
                mDrivingRouteOverlay.setData(drivingRouteLine);
                mDrivingRouteOverlay.addToMap();
            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };
        mRoutePlanSearch.setOnGetRoutePlanResultListener(onGetRoutePlanResultListener);
        //结束调启功能时调用finish方法以释放相关资源
        BaiduMapRoutePlan.finish(BaiduMapActivity.this);
    }

    //floatMenu的图标点击事件
    private class FloatMenuItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
           if(view.equals(mRlIcon1)){//查看任务清单
              if(mTasks!=null){
                  mTaskListdialog = DialogPlus.newDialog(BaiduMapActivity.this)
                          .setAdapter(new PopAdapter())
                          .setContentBackgroundResource(R.drawable.pop_map_task_bg)
                          .setInAnimation(R.anim.abc_fade_in)
                          .setOutAnimation(R.anim.abc_fade_out)
                          .setHeader(R.layout.header_map_task_)
                          .setCancelable(true)
                          .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                          .setOnItemClickListener(mOnItemClickListener)
                          .setExpanded(true,300)
                          .setGravity(Gravity.TOP)
                          .create();
                  mTaskListdialog.show();
              }else {
                  Toast.makeText(getApplicationContext(),"当前没用任务哦",Toast.LENGTH_SHORT).show();
              }
           }else if(view.equals(mRlIcon2)){//更改地图模式
               List<String> modes=new ArrayList<String>();
               modes.add("普通模式");
               modes.add("交通模式");
               modes.add("卫星模式");
               mMapModeDialog = DialogPlus.newDialog(BaiduMapActivity.this)
                       .setAdapter(new ArrayAdapter<>(BaiduMapActivity.this,android.R.layout.simple_list_item_1,modes))
                       .setContentBackgroundResource(R.drawable.pop_map_task_bg)
                       .setInAnimation(R.anim.abc_fade_in)
                       .setOutAnimation(R.anim.abc_fade_out)
                       .setHeader(R.layout.header_map_task_)
                       .setCancelable(true)
                       .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                       .setOnItemClickListener(mOnItemClickListener)
                       .setExpanded(true)
                       .setGravity(Gravity.CENTER)
                       .create();
               View view1= mMapModeDialog.getHeaderView();
               TextView tv_headerName= (TextView) view1.findViewById(R.id.tv_pop_task_headerName);
               tv_headerName.setText("地图模式");
               mMapModeDialog.show();
           }else if(view.equals(mRlIcon3)){//搜索附
               mPoiAdapter = new ArrayAdapter(BaiduMapActivity.this,android.R.layout.simple_dropdown_item_1line,mPois);
               mMapPoiDialog = DialogPlus.newDialog(BaiduMapActivity.this)
                        .setContentBackgroundResource(R.drawable.pop_map_task_bg)
                        .setInAnimation(R.anim.abc_fade_in)
                        .setOutAnimation(R.anim.abc_fade_out)
                        .setHeader(R.layout.header_pop_poi)
                        .setCancelable(true)
                        .setAdapter(mPoiAdapter)
                        .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setOnItemClickListener(mOnItemClickListener)
                        .setExpanded(true)
                        .setGravity(Gravity.TOP)
                        .create();
               View view1=mMapPoiDialog.getHeaderView();
               ImageButton ib_search= (ImageButton) view1.findViewById(R.id.ib_poiSearch);
               final EditText et_keyWord= (EditText) view1.findViewById(R.id.et_poikeyword);
               mMapPoiDialog.show();
               ib_search.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       String keyWord=et_keyWord.getText().toString();
                       startPoiSearch(keyWord);

                   }
               });

           }else if(view.equals(mRlIcon4)){//路线规划
               List<String> destinations=new ArrayList<String>();
               destinations.add("我的位置");
               if(mTasks!=null){
                   for (int i=0;i<mTasks.size();i++)
                       destinations.add("任务"+(i+1)+": "+mTasks.get(i).getFaddr());
               }
               mRoutPlanDialog = DialogPlus.newDialog(BaiduMapActivity.this)
                        .setAdapter(new ArrayAdapter<>(BaiduMapActivity.this,android.R.layout.simple_list_item_1,destinations))
                        .setContentBackgroundResource(R.drawable.pop_map_task_bg)
                        .setInAnimation(R.anim.abc_fade_in)
                        .setOutAnimation(R.anim.abc_fade_out)
                        .setHeader(R.layout.header_map_task_)
                        .setCancelable(true)
                        .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setOnItemClickListener(mOnItemClickListener)
                        .setExpanded(true)
                        .setGravity(Gravity.CENTER)
                        .create();
               View view1=  mRoutPlanDialog.getHeaderView();
               TextView tv_headerName= (TextView) view1.findViewById(R.id.tv_pop_task_headerName);
               tv_headerName.setText("路线规划");
               mRoutPlanDialog.show();
           }
        }
    }
    /**
     * 初始化数据
     */
    private void initData() {
        mBitmapUtils = new BitmapUtils(getApplicationContext());
        initLocation();
        mLocationClient.start();
    }

  public void startPoiSearch(String key) {
        mPoiSearch = PoiSearch.newInstance();
        final List<String> poiString=new ArrayList<String>();
//        第二步，创建POI检索监听者；
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){
            public void onGetPoiResult(final PoiResult result){
                //获取POI检索结果
                mAllPoi = result.getAllPoi();
                if (mAllPoi ==null){
                    return;
                }
                mPois.clear();
                mLatLngs.clear();
                for(int i = 0; i< mAllPoi.size(); i++){
                    mPois.add(mAllPoi.get(i).name+"\n"+ mAllPoi.get(i).address+"\n");
                    mLatLngs.add(mAllPoi.get(i).location);
                }
                mPoiAdapter.notifyDataSetChanged();
            }
            public void onGetPoiDetailResult(PoiDetailResult result){

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };

//        第三步，设置POI检索监听者；
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//        第四步，发起检索请求；
        if(mCurrentLocation==null)
        {
            return;
        }

        PoiNearbySearchOption poiNearbySearchOption=new PoiNearbySearchOption();
        poiNearbySearchOption.location(new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()))
                .radius(10*1000)//10公里范围
                .keyword(key)
                .pageNum(100)
                .pageNum(0);
        mPoiSearch.searchNearby(poiNearbySearchOption);
    }


    /**
     * 初始化定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        // 定位频率 必须大于1000 单位毫秒
        int span = 5000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 初始化view
     */
    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mIb_map_back = (ImageButton) findViewById(R.id.ib_map_back);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//设置地图默认模式为卫星模式
        //显示浮动菜单
        showFloatingActionMenu();
    }


    /**
     *show float menu
     */
    private void showFloatingActionMenu() {
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);

        mFabIconNew = new ImageView(this);
        mFabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(mFabIconNew)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_LEFT)
                .build();


        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        mRlIcon1 = new ImageView(this);
        mRlIcon2 = new ImageView(this);
        mRlIcon3 = new ImageView(this);
        mRlIcon4 = new ImageView(this);

        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin);
        rLSubBuilder.setLayoutParams(blueContentParams);
        // Set custom layout params
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        rLSubBuilder.setLayoutParams(blueParams);

        mRlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat_light));
        mRlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera_light));
        mRlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video_light));
        mRlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place_light));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        mLeftLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(mRlIcon1, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(mRlIcon2, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(mRlIcon3, blueContentParams).build())
                .addSubActionView(rLSubBuilder.setContentView(mRlIcon4, blueContentParams).build())
                .attachTo(rightLowerButton)
                .setStartAngle(360)
                .setEndAngle(270)
                .build();
        // Listen menu open and close events to animate the button content view
        mLeftLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                mFabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(mFabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                mFabIconNew.setRotation(180);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(mFabIconNew, pvhR);
                animation.start();
            }

        });
    }


    //从服务器获取pop数据

    public void getPOpJsonFromNet() {
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("method","getTaskInfo");
        params.add("username", SpTools.getString(getApplicationContext(),MyConstants.USERNAME,""));
        asyncHttpClient.post(MyConstants.TASKURL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody);
                mTasks=new ArrayList<Task>();
                mTasks.addAll(TaskJson.paseJson(jsonString));
                mMarkers = new ArrayList<OverlayOptions>();
                if (mTasks.size() > 0) {
                    for (int i = 0; i < mTasks.size(); i++) {
                        //添加标注
                        mMark = new MarkerOptions();
                        LatLng latLng = new LatLng(mTasks.get(i).getLatitude(), mTasks.get(i).getLongitude());
                        mMark.icon(BitmapDescriptorFactory.fromResource(mMakerIconsRes[i]))
                                .draggable(true)
                                .position(latLng)
                        .title(i+"");
                        mMarkers.add(mMark);
                    }
                    mBaiduMap.addOverlays(mMarkers);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"哎呀，没网了！",Toast.LENGTH_LONG);
            }
        });
    }




    /**
     * 注册监听事件$$
     */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mCurrentLocation = location;
            if (isFirst) {
                isFirst = false;
                setUserMapCept();
                addMark();//在当ia前定位加上标注
            }
        }
    }

//    设置中心点

    private void setUserMapCept() {
        mLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        //定义地图状态
        mMapStatus = new MapStatus.Builder()
                .target(mLatLng)
                .zoom(16)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    /**
     * 添加标注
     */

    private void addMark() {
        getPOpJsonFromNet();
    }

    /**
     * 定位到当前的位置
     */

    private void showLoction(LatLng latLng) {
        mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 21);
        mBaiduMap.animateMapStatus(mMapStatusUpdate, 3000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
        if(mPoiSearch!=null)
        mPoiSearch.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

