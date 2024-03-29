package com.xtayfjpk.netty.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * 第二种解决服务端传递的数据可能被分成碎片的问题方案
    1.ByteToMessageDecoder is an implementation of ChannelHandler which makes it easy to deal with the fragmentation issue.
    2.ByteToMessageDecoder calls the decode() method with an internally maintained cumulative buffer whenever new data is received.
    3.decode() can decide to add nothing to out where there is not enough data in the cumulative buffer. 
      ByteToMessageDecoder will call decode() again when there is more data received.
    4.If decode() adds an object to out, it means the decoder decoded a message successfully. 
      ByteToMessageDecoder will discard the read part of the cumulative buffer. 
      Please remember that you don't need to decode multiple messages. 
      ByteToMessageDecoder will keep calling the decode() method until it adds nothing to out.

 * @author zj
 *
 */
public class TimeDecoder extends ByteToMessageDecoder {//1

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {//2
		if (in.readableBytes() < 4) {
            return; // (3)
        }
        out.add(in.readBytes(4));//4
	}

}
