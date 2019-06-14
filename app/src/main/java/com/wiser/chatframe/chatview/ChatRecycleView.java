package com.wiser.chatframe.chatview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.wiser.chatframe.ChatAdapter;
import com.wiser.chatframe.listener.ChatListener;
import com.wiser.chatframe.model.ChatModel;
import com.wiser.chatframe.util.ChatTool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wiser
 * 
 *         聊天列表控件
 */
public class ChatRecycleView extends RecyclerView implements View.OnTouchListener {

	private ChatListener chatListener;

	private ChatAdapter adapter;

	public ChatRecycleView(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ChatRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(final Context context) {
		setOnTouchListener(this);

		getRootView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override public void onGlobalLayout() {
				if (ChatTool.isKeyboardShown((Activity) context)) {
					scrollToLastPosition();
					System.out.println("---------->>onGlobalLayout");
				}
			}
		});
	}

	private void addData() {
		List<ChatModel> chatModels = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			ChatModel chatModel = new ChatModel();
			if (i % 2 == 0) {
				chatModel.messageType = "left";
				chatModel.name = "小王";
				chatModel.content = "你好啊" + i;
			} else {
				chatModel.messageType = "right";
				chatModel.name = "小李";
				chatModel.content = "我很好" + i;
			}
			chatModels.add(chatModel);
		}

		if (adapter != null) {
			adapter.setItems(chatModels);
		}
	}

	public void setAdapter() {
		if (adapter == null) {
			adapter = new ChatAdapter(getContext());
			LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
			// 配合windowSoftInputMode实现Title不一起向上推效果
			layoutManager.setStackFromEnd(true);
			setLayoutManager(layoutManager);
			setAdapter(adapter);
			addData();
			scrollToPosition(adapter.getItemCount() - 1);
		} else adapter.notifyDataSetChanged();
	}

	public void scrollToLastPosition() {
		smoothScrollToPosition(adapter.getItemCount() - 1);
	}

	public void setChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}

	@Override

	public boolean onTouch(View v, MotionEvent event) {
		if (chatListener != null) {
			chatListener.recycleViewTouch();
		}
		return false;
	}
}
