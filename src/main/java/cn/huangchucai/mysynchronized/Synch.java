package cn.huangchucai.mysynchronized;

public class Synch {
    public static void main(String[] args) {
        Callme target = new Callme();
        // 共享同一个target对象，开通三个线程
        Caller obj1 = new Caller("Hello", target);
        Caller obj2 = new Caller("Synchronized",target);
        Caller obj3 = new Caller("world", target);
        // 三个线程全部执行完成
        try {
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
