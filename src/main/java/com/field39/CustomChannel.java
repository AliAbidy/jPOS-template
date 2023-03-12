package com.field39;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.NACChannel;

import java.io.IOException;

public class CustomChannel extends NACChannel {
    public CustomChannel() {
        super();
    }

    public CustomChannel(String host, int port, ISOPackager p, byte[] TPDU) {
        super(host, port, p, TPDU);
    }
    @Override
    protected void sendMessageLength(int len) throws IOException {
        System.out.println("SendLength: "+len);
        super.sendMessageLength(len);
    }

    @Override
    protected int getMessageLength() throws IOException, ISOException {
        return super.getMessageLength();
    }


}
