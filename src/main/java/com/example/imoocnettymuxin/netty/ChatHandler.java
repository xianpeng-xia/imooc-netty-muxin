package com.example.imoocnettymuxin.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.time.LocalDateTime;

/**
 * Created by xianpeng.xia
 * on 2019-06-30 23:32
 *
 * 处理消息的handler
 *
 * TextWebSocketFrame：在netty中，专门为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    /**
     * 用于记录和管理所有客户端的channel
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传过来的消息
        String content = msg.text();
        System.out.println("recived from client : " + content);
        /*for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[server recived message :]" + LocalDateTime.now() + ", message : " + content));
        }*/
        //与上面的for循环作用一样
        clients.writeAndFlush(new TextWebSocketFrame("[server recived message :]" + LocalDateTime.now() + ", message : " + content));
    }


    /**
     * 当客户端连接服务器之后（打开连接）
     * 读取channel的内容放到ChannelGroup中进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /**
     *
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved，ChannelGroup自动删除
        //clients.remove(ctx.channel());
        System.out.println("clinet disconnent,channel long id :" + ctx.channel().id().asLongText());
        System.out.println("clinet disconnent,channel short id :" + ctx.channel().id().asLongText());
    }
}
