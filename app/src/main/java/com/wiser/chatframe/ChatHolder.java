package com.wiser.chatframe;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.wiser.chat.ChatBaseHolder;
import com.wiser.chatframe.model.ChatModel;

/**
 * @author Wiser
 * 
 *         聊天Holder
 */
public class ChatHolder extends ChatBaseHolder<ChatModel> {

	private TextView	tvContent;

	private TextView	tvName;

	public ChatHolder(@NonNull View itemView) {
		super(itemView);
		tvContent = itemView.findViewById(R.id.tv_chat_content);
		tvName = itemView.findViewById(R.id.tv_chat_name);
	}

	@Override public void bindData(ChatModel chatModel, int position) {
		if (chatModel == null) return;
		if (chatModel.content != null && !"".equals(chatModel.content)) tvContent.setText(chatModel.content);
		if (chatModel.name != null && !"".equals(chatModel.name)) tvName.setText(chatModel.name);
	}
}
