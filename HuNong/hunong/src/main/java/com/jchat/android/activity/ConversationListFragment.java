package com.jchat.android.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.geowind.hunong.R;
import com.geowind.hunong.application.JChatDemoApplication;
import com.geowind.hunong.global.fragment.BaseFragment;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.jchat.android.adapter.ConversationListAdapter;
import com.jchat.android.chatting.ChatActivity;
import com.jchat.android.chatting.utils.DialogCreator;
import com.jchat.android.chatting.utils.HandleResponseCode;
import com.jchat.android.entity.Event;
import com.jchat.android.tools.SortConvList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import de.greenrobot.event.EventBus;

/*
 * 会话列表界面
 */
public class ConversationListFragment extends BaseFragment {

    private static String TAG = ConversationListFragment.class.getSimpleName();
    private View mRootView;

    private NetworkReceiver mReceiver;
    private Activity mContext;
    private BackgroundHandler mBackgroundHandler;
    private HandlerThread mThread;
    private List<Conversation> mDatas;
    private ConversationListAdapter mListAdapter;
    private int mWidth;
    private Dialog mDialog;
    private static final int REFRESH_CONVERSATION_LIST = 0x3000;
    private RecyclerView mRecyclerView;

    public static ConversationListFragment newInstance(){return  new ConversationListFragment();}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<Conversation>();
        mContext = this.getActivity();
        EventBus.getDefault().register(this);
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_conv_list,
                (ViewGroup) getActivity().findViewById(R.id.main_view), false);
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.conv_list_view);
        initReceiver();
        //添加系统通知
        getUserInfo("system");
        getUserInfo("professorAnswer");
        getUserInfo("taskMessage");
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        initConvListAdapter();
        //设置分割线
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        initEvent();

    }
    // 得到会话列表
    private void initConvListAdapter() {
        mDatas = JMessageClient.getConversationList();
        //对会话列表进行时间排序
        if (mDatas.size() > 1) {
            SortConvList sortList = new SortConvList();
            Collections.sort(mDatas, sortList);
        }

        mListAdapter = new ConversationListAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(mListAdapter);
    }

    private void initEvent() {
        mListAdapter.setOnItemClickLitener(new ConversationListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                final Intent intent = new Intent();
                    Conversation conv = mDatas.get(position);
                    if (null != conv) {
                        // 当前点击的会话是否为群组
                        if (conv.getType() == ConversationType.group) {
                            long groupId = ((GroupInfo) conv.getTargetInfo()).getGroupID();
                            intent.putExtra(JChatDemoApplication.GROUP_ID, groupId);
                            intent.putExtra(JChatDemoApplication.DRAFT, mListAdapter.getDraft(conv.getId()));
                            intent.setClass(mContext, ChatActivity.class);
                            mContext.startActivity(intent);
                            return;
                        } else {
                            String targetId = ((UserInfo) conv.getTargetInfo()).getUserName();
                            intent.putExtra(JChatDemoApplication.TARGET_ID, targetId);
                            intent.putExtra(JChatDemoApplication.TARGET_APP_KEY, conv.getTargetAppKey());
                            Log.d("ConversationList", "Target app key from conversation: " + conv.getTargetAppKey());
                            intent.putExtra(JChatDemoApplication.DRAFT,mListAdapter.getDraft(conv.getId()));
                        }
                        intent.setClass(mContext, ChatActivity.class);
                        mContext.startActivity(intent);
                    }

            }
        });
        mListAdapter.setItemLongClickListener(new ConversationListAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                if (position > 0) {
                    final Conversation conv = mDatas.get(position);
                    if (conv != null) {
                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (conv.getType() == ConversationType.group) {
                                    JMessageClient.deleteGroupConversation(((GroupInfo) conv.getTargetInfo())
                                            .getGroupID());
                                } else {
                                    //使用带AppKey的接口,可以删除跨/非跨应用的会话(如果不是跨应用,conv拿到的AppKey则是默认的)
                                    JMessageClient.deleteSingleConversation(((UserInfo) conv.getTargetInfo())
                                            .getUserName(), conv.getTargetAppKey());
                                }
                                mDatas.remove(position);
                                mListAdapter.notifyDataSetChanged();
                                mDialog.dismiss();
                            }
                        };
                        mDialog = DialogCreator.createDelConversationDialog(mContext, conv.getTitle(),
                                listener);
                        mDialog.show();
                        mDialog.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams.WRAP_CONTENT);
                    }
                }
            }
        });
    }

    private void getUserInfo(final String targetId){
        JMessageClient.getUserInfo(targetId, new GetUserInfoCallback() {
            @Override
            public void gotResult(final int status, String desc, final UserInfo userInfo) {
                if (status == 0) {
                    Conversation conv = Conversation.createSingleConversation(targetId);
                    if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                        userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                            @Override
                            public void gotResult(int status, String desc, Bitmap bitmap) {
                                if (status == 0) {
                                    mListAdapter.notifyDataSetChanged();
                                } else {
                                    HandleResponseCode.onHandle(getActivity(), status, false);
                                }
                            }
                        });
                    }
                  mListAdapter.setToTop(conv);
                } else {
                    HandleResponseCode.onHandle(getActivity(), status, true);
                }
            }
        });
    }
    private void initReceiver() {
        mReceiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mContext.registerReceiver(mReceiver, filter);
    }

    //监听网络状态的广播
    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeInfo = manager.getActiveNetworkInfo();
            }
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }


    /**
     * 在会话列表中接收消息
     *
     * @param event 消息事件
     */
    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        Log.d(TAG, "收到消息：msg = " + msg.toString());
        ConversationType convType = msg.getTargetType();
        if (convType == ConversationType.group) {
            long groupID = ((GroupInfo) msg.getTargetInfo()).getGroupID();
            Conversation conv = JMessageClient.getGroupConversation(groupID);
            if (conv != null) {
                mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST,
                        conv));
            }
        } else {
            final UserInfo userInfo = (UserInfo) msg.getTargetInfo();
            final String targetID = userInfo.getUserName();
            final Conversation conv = JMessageClient.getSingleConversation(targetID, userInfo.getAppKey());
            if (conv != null) {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //如果设置了头像
                        if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                            //如果本地不存在头像
                            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                                @Override
                                public void gotResult(int status, String desc, Bitmap bitmap) {
                                    if (status == 0) {
                                        mListAdapter.notifyDataSetChanged();
                                    } else {
                                        HandleResponseCode.onHandle(mContext, status, false);
                                    }
                                }
                            });
                        }
                    }
                });
                mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST,
                        conv));
            }
        }
    }

    private class BackgroundHandler extends Handler {
        public BackgroundHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_CONVERSATION_LIST:
                    Conversation conv = (Conversation) msg.obj;
                    mListAdapter.setToTop(conv);
                    break;
            }
        }
    }


    /**
     * 收到创建单聊的消息
     *
     * @param event 可以从event中得到targetID
     */
    public void onEventMainThread(Event.StringEvent event) {
        Log.d(TAG, "StringEvent execute");
        String targetId = event.getTargetId();
        String appKey = event.getAppKey();
        Conversation conv = JMessageClient.getSingleConversation(targetId, appKey);
        if (conv != null) {
            mListAdapter.addNewConversation(conv);
        }
    }

    /**
     * 收到创建或者删除群聊的消息
     *
     * @param event 从event中得到groupID以及flag
     */
    public void onEventMainThread(Event.LongEvent event) {
        long groupId = event.getGroupId();
        Conversation conv = JMessageClient.getGroupConversation(groupId);
        if (conv != null && event.getFlag()) {
            mListAdapter.addNewConversation(conv);
        } else {
            mListAdapter.deleteConversation(groupId);
        }
    }

    /**
     * 收到保存为草稿事件
     * @param event 从event中得到Conversation Id及草稿内容
     */
    public void onEventMainThread(Event.DraftEvent event) {
        String draft = event.getDraft();
        String targetId = event.getTargetId();
        String targetAppKey = event.getTargetAppKey();
        Conversation conv;
        if (targetId != null) {
            conv = JMessageClient.getSingleConversation(targetId, targetAppKey);
        } else {
            long groupId = event.getGroupId();
            conv = JMessageClient.getGroupConversation(groupId);
        }
        //如果草稿内容不为空，保存，并且置顶该会话
        if (!TextUtils.isEmpty(draft)) {
            mListAdapter.putDraftToMap(conv.getId(), draft);
            mListAdapter.setToTop(conv);
        //否则删除
        } else {
            mListAdapter.delDraftFromMap(conv.getId());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mContext.unregisterReceiver(mReceiver);
        if(mBackgroundHandler!=null)
        mBackgroundHandler.removeCallbacksAndMessages(null);
        if(mThread!=null)
        mThread.getLooper().quit();
        super.onDestroy();
    }


    public void StartCreateGroupActivity() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CreateGroupActivity.class);
        startActivity(intent);
    }

    public void sortConvList() {
        mListAdapter.sortConvList();
    }

}
