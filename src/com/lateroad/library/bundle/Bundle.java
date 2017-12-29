package com.lateroad.library.bundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Bundle {
    private static Bundle instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ResourceBundle bundle;

    public static Bundle getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Bundle();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private Bundle(){
        bundle = ResourceBundle.getBundle("resources_ru");
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void setBundle(ResourceBundle bundle) {
        Bundle.bundle = bundle;
    }

    public static void reload(HttpServletRequest req){
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("inOrOut", "in");
            session.setAttribute("inOrOutLabel", Bundle.getBundle().getString("inOrOutLabel"));
            session.setAttribute("inOrOutAdress", "/signin.html");
        }
    }
}
