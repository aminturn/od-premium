package com.trubeacon.ordermonitorgui;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;

import java.lang.ref.WeakReference;

/**
 * Created by Andrew on 5/6/15.
 */


public class DoneTypingTextWatcher implements TextWatcher {

    private static final int TEXT_CHANGED = 1337;
    private static final int DONE_TYPING_TIMEOUT_MS = 800;

    public interface OnDoneTypingListener {
        public void onDoneTyping();
    }

    private OnDoneTypingListener onDoneTypingListener;

    public void setOnDoneTypingListener(OnDoneTypingListener onDoneTypingListener) {
        this.onDoneTypingListener = onDoneTypingListener;
    }

    private static class DoneTypingHandler extends Handler {

        private final WeakReference<DoneTypingTextWatcher> watcher;

        public DoneTypingHandler(DoneTypingTextWatcher watcher) {
            this.watcher = new WeakReference<DoneTypingTextWatcher>(watcher);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TEXT_CHANGED) {
                if (watcher != null) {
                    watcher.get().onDoneTypingListener.onDoneTyping();
                }
            }
        }

    }

    private DoneTypingHandler doneTypingHandler = new DoneTypingHandler(this);

    public DoneTypingTextWatcher(OnDoneTypingListener onDoneTypingListener) {
        this.onDoneTypingListener = onDoneTypingListener;
    }

    @Override
    public void afterTextChanged(Editable s) {
        doneTypingHandler.removeMessages(TEXT_CHANGED);
        doneTypingHandler.sendEmptyMessageDelayed(TEXT_CHANGED, DONE_TYPING_TIMEOUT_MS);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

}

