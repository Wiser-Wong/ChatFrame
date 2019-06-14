package com.wiser.chatframe.chatview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.wiser.chatframe.R;

/**
 * @author Wiser
 * 
 *         更多控件
 */
public class ChatMoreLayout extends FrameLayout implements Animation.AnimationListener {

	private Animation	moreAnimIn;

	private Animation	moreAnimOut;

	public ChatMoreLayout(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ChatMoreLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.chat_more_layout, this, false);
		addView(view);

		moreAnimIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bottom_in);
		moreAnimIn.setAnimationListener(this);
		moreAnimOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bottom_out);
		moreAnimOut.setAnimationListener(this);
	}

	public void showMoreAnim() {
		startAnimation(moreAnimIn);
	}

	public void hideMoreAnim() {
		startAnimation(moreAnimOut);
	}

	@Override public void onAnimationStart(Animation animation) {
		if (animation.equals(moreAnimIn)) {
			setVisibility(View.VISIBLE);
		}
	}

	@Override public void onAnimationEnd(Animation animation) {
		if (animation.equals(moreAnimOut)) {
			setVisibility(View.GONE);
		}
	}

	@Override public void onAnimationRepeat(Animation animation) {

	}
}
