package com.xtayfjpk.netty.test.pojo;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * @author zj
 *
 */
public class TimeDecoder extends ByteToMessageDecoder {//1

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {//2
		if (in.readableBytes() < 4) {
            return; // (3)
        }
        out.add(new UnixTime(in.readInt()));//4
	}

}
