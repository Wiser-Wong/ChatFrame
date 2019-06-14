package com.wiser.chatframe.chatview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wiser.chatframe.listener.ChatListener;
import com.wiser.chatframe.util.ChatTool;
import com.wiser.chatframe.R;

/**
 * @author Wiser
 * 
 *         聊天布局
 */
public class ChatView extends FrameLayout implements View.OnClickListener, ChatListener, View.OnFocusChangeListener {

	private InputMethodManager	imm;

	private ChatRecycleView		rlvChat;

	private AppCompatImageView	ivVoice;

	private AppCompatImageView	ivVoiceKeyboard;

	private AppCompatEditText	etMessage;

	private Button				btnSpeak;

	private AppCompatImageView	ivFace;

	private AppCompatImageView	ivFaceKeyboard;

	private AppCompatImageView	ivMore;

	private ChatFunctionLayout	functionLayout;

	public ChatView(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ChatView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

		View view = LayoutInflater.from(context).inflate(R.layout.chat_layout, this, false);
		rlvChat = view.findViewById(R.id.rlv_chat);
		ivVoice = view.findViewById(R.id.iv_voice_control);
		ivVoiceKeyboard = view.findViewById(R.id.iv_voice_keyboard_control);
		etMessage = view.findViewById(R.id.et_chat_message);
		btnSpeak = view.findViewById(R.id.btn_speak);
		ivFace = view.findViewById(R.id.iv_face_control);
		ivFaceKeyboard = view.findViewById(R.id.iv_face_keyboard_control);
		ivMore = view.findViewById(R.id.iv_more_control);

		functionLayout = view.findViewById(R.id.chat_function_layout);

		addView(view);

		setListener();

		initRecycleView();
	}

	private void setListener() {
		ivVoice.setOnClickListener(this);
		ivVoiceKeyboard.setOnClickListener(this);
		ivFace.setOnClickListener(this);
		ivFaceKeyboard.setOnClickListener(this);
		ivMore.setOnClickListener(this);
		etMessage.setOnClickListener(this);
		etMessage.setOnFocusChangeListener(this);
	}

	private void initRecycleView() {
		rlvChat.setChatListener(this);
		rlvChat.setAdapter();
	}

	@Override public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_voice_control:// 语音切换
				voiceControl();
				break;
			case R.id.iv_voice_keyboard_control:// 语音键盘切换
				voiceKeyboardControl();
				break;
			case R.id.iv_face_control:// 表情切换
				faceControl();
				break;
			case R.id.iv_face_keyboard_control:// 表情键盘切换
				faceKeyboardControl();
				break;
			case R.id.iv_more_control:// 更多切换
				moreControl();
				break;
			case R.id.et_chat_message:
				resetAllControl();
				hideFunction();
				break;
		}
	}

	// 语音控制
	private void voiceControl() {
		ivVoice.setVisibility(View.GONE);
		ivVoiceKeyboard.setVisibility(View.VISIBLE);

		voiceResetOtherControl();

		hideFunction();

		if (ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(false);
		}

		inputAndVoiceControl(true);

	}

	// 语音的键盘控制
	private void voiceKeyboardControl() {
		ivVoiceKeyboard.setVisibility(View.GONE);
		ivVoice.setVisibility(View.VISIBLE);

		inputAndVoiceControl(false);

		hideFunction();

		if (!ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(true);
		}
	}

	// 表情控制
	private void faceControl() {
		ivFace.setVisibility(View.GONE);
		ivFaceKeyboard.setVisibility(View.VISIBLE);

		faceResetOtherControl();

		showFace();
	}

	// 表情的键盘控制
	private void faceKeyboardControl() {
		ivFaceKeyboard.setVisibility(View.GONE);
		ivFace.setVisibility(View.VISIBLE);

		hideFunction();

		if (!ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(true);
		}
	}

	// 更多控制
	private void moreControl() {
		moreResetOtherControl();

		showMore();
	}

	// 输入和语音控制
	private void inputAndVoiceControl(boolean isVoice) {
		if (isVoice) {
			if (isVoice()) return;
			etMessage.setVisibility(View.GONE);
			btnSpeak.setVisibility(View.VISIBLE);
		} else {
			if (!isVoice()) return;
			btnSpeak.setVisibility(View.GONE);
			etMessage.setVisibility(View.VISIBLE);
		}
	}

	private boolean isVoice() {
		return etMessage.getVisibility() == View.GONE && btnSpeak.getVisibility() == View.VISIBLE;
	}

	// 点击语音重置其他
	private void voiceResetOtherControl() {
		ivFace.setVisibility(View.VISIBLE);
		ivFaceKeyboard.setVisibility(View.GONE);
	}

	// 点击表情重置其他
	private void faceResetOtherControl() {
		ivVoice.setVisibility(View.VISIBLE);
		ivVoiceKeyboard.setVisibility(View.GONE);

		inputAndVoiceControl(false);
	}

	// 点击更多重置其他
	private void moreResetOtherControl() {
		ivVoice.setVisibility(View.VISIBLE);
		ivVoiceKeyboard.setVisibility(View.GONE);
		ivFace.setVisibility(View.VISIBLE);
		ivFaceKeyboard.setVisibility(View.GONE);

		inputAndVoiceControl(false);
	}

	// 显示表情
	private void showFace() {
		if (ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(false);
		}
		functionLayout.showFace();
	}

	// 显示更多
	private void showMore() {
		if (ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(false);
		}
		functionLayout.showMore();
	}

	private void hideFunction() {
		functionLayout.hideFunction();
	}

	private void resetAllControl() {
		ivVoice.setVisibility(View.VISIBLE);
		ivVoiceKeyboard.setVisibility(View.GONE);
		ivFace.setVisibility(View.VISIBLE);
		ivFaceKeyboard.setVisibility(View.GONE);

		inputAndVoiceControl(false);
	}

	// 是否显示软键盘
	public void keyboardAction(boolean isShow) {
		if (isShow) {
			etMessage.requestFocus();
			imm.showSoftInput(etMessage, InputMethodManager.SHOW_FORCED);
			resetAllControl();
		} else {
			imm.hideSoftInputFromWindow(etMessage.getWindowToken(), 0);
		}
	}

	@Override public void recycleViewTouch() {
		hideFunction();
		voiceResetOtherControl();
		if (ChatTool.isKeyboardShown((Activity) getContext())) {
			keyboardAction(false);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus){
			resetAllControl();
			hideFunction();
		}
	}
}
