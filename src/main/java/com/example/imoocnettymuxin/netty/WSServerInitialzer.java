package com.example.imoocnettymuxin.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by xianpeng.xia
 * on 2019-06-30 23:18
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        /*用于支持http协议*/
        //websocket基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合，聚合成FullHttpRequest/FullHttpResponse
        //几乎在netty的编程，都会使用此handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /*用于支持websocket*/
        //websocket 服务器处理的协议，用于指定给客户端连接直接访问的路由：/ws
        //会帮你处理复杂的事情
        //比如无手动作：handshaking（close，ping，pong） ping+pong=心跳
        //对于websocket来说，多是以frames进行传输的，不同数据类型对应的frames不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        /*自定义handler*/
        pipeline.addLast(new ChatHandler());
    }
}
