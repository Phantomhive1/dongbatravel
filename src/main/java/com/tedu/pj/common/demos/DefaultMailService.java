package com.tedu.pj.common.demos;

class LogAspect {
    void before() {
        System.out.println("start: "+System.currentTimeMillis());
    }
    void after() {
        System.out.println("end: "+System.currentTimeMillis());
    }

}

class LogMailService extends DefaultMailService {
    private LogAspect logAspect;

    public LogMailService(LogAspect logAspect) {
        this.logAspect = logAspect;
    }

    @Override
    public void send(String msg) {
        logAspect.before();
        super.send(msg);
        logAspect.after();
    }
}



public class DefaultMailService implements MailService {
    @Override
    public void send(String msg) {
        System.out.println("send: "+msg);
    }
}
