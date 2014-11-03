package com.xtayfjpk.netty.test.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * 
    There are quite a few important things to important in this single line.
    First, we pass the original ChannelPromise as-is so that Netty marks it as success or failure 
    when the encoded data is actually written out to the wire.
    Second, we did not call ctx.flush(). There is a separate handler method void flush(ChannelHandlerContext ctx) 
    which is purposed to override the flush() operation.

 * @author zj
 *
 */
public class TimeEncoder extends ChannelHandlerAdapter {
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		UnixTime m = (UnixTime)msg;
		ByteBuf encoded = ctx.alloc().buffer(4);
		encoded.writeInt((int) m.value());//不能使用encoded.writeLong，因为会写入两个字节
		ctx.write(encoded, promise);//1
	}
}
