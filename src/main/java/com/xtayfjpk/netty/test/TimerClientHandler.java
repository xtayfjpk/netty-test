package com.xtayfjpk.netty.test;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 第一种解决服务端传递的数据可能被分成碎片的问题方案
 * @author zj
 *
 */
public class TimerClientHandler extends ChannelHandlerAdapter {
	private ByteBuf buf;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		this.buf = ctx.alloc().buffer(4);
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		this.buf.release();
		this.buf = null;
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg; // (1)
		buf.writeBytes(m);
		m.release();
		 
		if(buf.readableBytes()>=4) {
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
		}
        
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}
}
