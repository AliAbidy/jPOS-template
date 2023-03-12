package com.field39;

import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.*;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
public class Listener implements ISORequestListener {
    private AtomicLong stan = new AtomicLong();

    @Override
    public boolean process(ISOSource source, ISOMsg m) {

        log.info("Message received......");

        ISOMsg response =(ISOMsg) m.clone();

        try {
            String st = new String(m.pack(), Charset.forName("cp1047"));
//            System.out.println("Request: "+st);
//            System.out.println(m.getISOHeader().getLength()+"");

            response.setMTI("0810");
            response.set(39, "00");
            source.send(response);

            log.info("Message sent.....");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
