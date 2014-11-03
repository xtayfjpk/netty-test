package com.xtayfjpk.netty.test.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 
 * @author zj
 *
 */
public class TimeEncoder2 extends MessageToByteEncoder<UnixTime> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
		out.writeLong(msg.value());		
	}
}
