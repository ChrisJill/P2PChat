package com.rdc.p2p.contract;

import com.rdc.p2p.bean.MessageBean;

/**
 * Created by Lin Yaotian on 2018/5/17.
 */
public interface ChatDetailContract {
    interface View{
        void sendSuccess();
        void sendError(String message);
    }

    interface Model{
        void sendMessage(MessageBean msg,String targetIp);
    }

    interface Presenter{
        void sendMessage(MessageBean msg,String targetIp);
        void sendError(String message);
        void sendSuccess();
    }

}
