package com.xtayfjpk.netty.test.pojo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimerServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		ChannelFuture future = ctx.writeAndFlush(new UnixTime());
		future.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}
}
