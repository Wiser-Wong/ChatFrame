package com.wiser.chatframe;

import android.content.Context;
import android.view.ViewGroup;

import com.wiser.chat.ChatBaseAdapter;
import com.wiser.chat.ChatBaseHolder;

public class ChatAdapter extends ChatBaseAdapter<ChatModel, ChatBaseHolder> {

    private static final int LEFT = 0X111;
    private static final int RIGHT = 0X112;

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        ChatModel chatModel = getItem(position);
        if (chatModel != null && chatModel.messageType != null && !"".equals(chatModel.messageType)) {
            if ("left".equals(chatModel.messageType)) {
                return LEFT;
            }
            if ("right".equals(chatModel.messageType)) {
                return RIGHT;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public ChatBaseHolder newViewHolder(ViewGroup viewGroup, int type) {
        ChatBaseHolder holder;
        switch (type) {
            case LEFT:
                holder = new ChatHolder(inflate(viewGroup, R.layout.chat_left_text_layout));
                break;
            case RIGHT:
                holder = new ChatHolder(inflate(viewGroup, R.layout.chat_right_text_layout));
                break;
            default:
                holder = new ChatHolder(inflate(viewGroup, R.layout.chat_left_text_layout));
                break;
        }
        return holder;
    }
}
