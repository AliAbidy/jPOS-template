package com.field39;

import org.jpos.iso.ISODate;
import org.jpos.iso.ISOHeader;
import org.jpos.iso.ISOMsg;

import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.NACChannel;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Client {
    private static AtomicLong stan = new AtomicLong();
    public static void main(String[] args) {
        Q2 q2 = new Q2();

        q2.start();

        try {

            Thread.sleep(3000);

            QMUX sender = NameRegistrar.get("mux.mymux");


            sendLogon(sender);
            sendPurchase(sender);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendLogon(QMUX sender) throws Exception {
        ISOMsg request = new ISOMsg();
        request.setMTI("0800");
        request.set(7, ISODate.getDateTime (new Date()));
        request.set(11, ISOUtil.zeropad (Long.toString (stan.incrementAndGet()), 6));
        request.set(70, "001");

        ISOMsg response = sender.request(request, 10 * 1000);

//        dump("Request", request);
//        dump("Response", response);

    }
    private static void sendPurchase(QMUX sender) throws Exception {
        ISOMsg request = new ISOMsg();
        request.setMTI("0100");
        request.set(2, "1234567890123456");
        request.set(3, "000000");
        request.set(4, ISOUtil.formatAmount(100, 12));
        request.set(7, ISODate.getDateTime (new Date()));
        request.set(9, "1.0000");
        request.set(11, ISOUtil.zeropad (Long.toString (stan.incrementAndGet()), 6));
        request.set(12, ISODate.getTime(new Date()));
        request.set(13, ISODate.getDate (new Date()));
        request.set(14, ISODate.getExpirationDate (new Date()));
        request.set(15, ISODate.getDate (new Date()));

        request.set(18, "4111");
        request.set(19, "840");
        request.set(22, "012");
        request.set(25, "00");
        request.set(26, "00");
        request.set(32, "12345678901");
        request.set(35, "12345678901");
        request.set(37, "123456789012");
        request.set(41, "12345678");
        request.set(42, "123456789012345");
        request.set(43, "123456789012345");
        request.set(49, "840");
        request.set(50, "840");
//        request.set(52, "zzzz");
        request.set(60, "84");
        request.set(61, "84");
        request.set(103, "84");
        request.set(120, "84");
        request.set(121, "84");
        request.set(122, "84");
        request.set(123, "84");
        request.set(125, "84");
        request.set(126, "84");
        request.set(127, "84");
        request.set(127, "84");

        ISOMsg response = sender.request(request, 10 * 1000);

//        dump("Request", request);
//        dump("Response", response);
    }

    private static void dump(String type, ISOMsg request) throws Exception {
        String st = new String(request.pack(), Charset.forName("cp1047"));

        System.out.println(type+": "+st);
    }

}
