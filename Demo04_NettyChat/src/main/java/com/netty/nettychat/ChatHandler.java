package com.netty.nettychat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author：ke
 * @ Date： 2019/7/6 20:36
 * @ Version 1.0
 */

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用来保存所有的客户连接
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    //当Channel中有新的事件消息会自动调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //当接受到数据后台会自动调用

        //获取客户端发送过来的文本消息
        String text = msg.text();
        System.out.println("接受到的消息: " + text);

        for (Channel client : clients) {
            //将消息发送到所有客户端
            client.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date()) + ":" + text));
        }
    }

    //当有新的客户端连接服务器之后,会自动调用这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将新的通道加入到clients
        clients.add(ctx.channel());
    }



}
