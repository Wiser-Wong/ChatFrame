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
 *         表情控件
 */
public class ChatFaceLayout extends FrameLayout implements Animation.AnimationListener {

	private Animation	faceAnimIn;

	private Animation	faceAnimOut;

	public ChatFaceLayout(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ChatFaceLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.chat_face_layout, this, false);
		addView(view);

		faceAnimIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bottom_in);
		faceAnimIn.setAnimationListener(this);
		faceAnimOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bottom_out);
		faceAnimOut.setAnimationListener(this);
	}

	public void showFaceAnim() {
		startAnimation(faceAnimIn);
	}

	public void hideFaceAnim() {
		startAnimation(faceAnimOut);
	}

	@Override public void onAnimationStart(Animation animation) {
		if (animation.equals(faceAnimIn)) {
			setVisibility(View.VISIBLE);
		}
	}

	@Override public void onAnimationEnd(Animation animation) {
		if (animation.equals(faceAnimOut)) {
			setVisibility(View.GONE);
		}
	}

	@Override public void onAnimationRepeat(Animation animation) {

	}
}
