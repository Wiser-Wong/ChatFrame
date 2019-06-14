package com.wiser.chatframe.chatview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wiser.chatframe.R;

/**
 * @author Wiser
 * 
 *         聊天功能模块
 */
public class ChatFunctionLayout extends FrameLayout {

	private ChatFaceLayout	faceLayout;

	private ChatMoreLayout	moreLayout;

	public ChatFunctionLayout(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ChatFunctionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.chat_function_layout, this, false);
		faceLayout = view.findViewById(R.id.chat_face_layout);
		moreLayout = view.findViewById(R.id.chat_more_layout);

		addView(view);
	}

	public void showFace() {
		setVisibility(View.VISIBLE);
		if (faceLayout.getVisibility() == View.VISIBLE) {
			hideFunction();
			return;
		}
		if (moreLayout.getVisibility() == View.VISIBLE) {
			moreLayout.setVisibility(View.GONE);
		}
		faceLayout.setVisibility(View.VISIBLE);
	}

	public void showMore() {
		setVisibility(View.VISIBLE);
		if (moreLayout.getVisibility() == View.VISIBLE) {
			hideFunction();
			return;
		}
		if (faceLayout.getVisibility() == View.VISIBLE) {
			faceLayout.setVisibility(View.GONE);
		}
		moreLayout.setVisibility(View.VISIBLE);
	}

	public void hideFunction() {
		if (isShowFunction()) setVisibility(View.GONE);
		if (faceLayout.getVisibility() == View.VISIBLE) {
			faceLayout.setVisibility(View.GONE);
		}
		if (moreLayout.getVisibility() == View.VISIBLE) {
			moreLayout.setVisibility(View.GONE);
		}
	}

	public boolean isShowFunction() {
		return getVisibility() == View.VISIBLE;
	}
}
