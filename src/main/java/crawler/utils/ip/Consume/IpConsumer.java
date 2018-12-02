package crawler.utils.ip.Consume;

import crawler.result.IPPort;

public class IpConsumer extends Consumer {
    private String ip;
    private String port;

    public IpConsumer(IPPort ipPort) {
        super(ipPort);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public void consume(){
        while(true){//loop start
            String ipPort = null;//ip + port
            //lock the ipPort
            synchronized (this.getIpPort()) {
                if (this.getIpPort().getIpPortQueue().size() == 0) {
                    try {
                        //if queue don't have record
                        System.out.println("ipConsumer wait...");
                        this.getIpPort().wait();
                        System.out.println("ipConsumer waking...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.getIpPort().getIpPortQueue().size() > 0) {//if ipPortQueue's size more than zero
                    System.out.println("before consume: " + this.getIpPort().getIpPortQueue().size());
                    //Retrieves and removes the head of this queue, or return the null if this queue is empty
                    ipPort = this.getIpPort().getIpPortQueue().poll();
                    if(this.getIpPort().getIpPortQueue().size()<10) this.getIpPort().notifyAll();// notify the all ip producer
                    System.out.println("after consume: " + this.getIpPort().getIpPortQueue().size());

                    String str[] = ipPort.split(" ");
                    if (this.getIpUtils().isValidIpPort(ipPort)) {// Check if the ipPort is valid?
                        synchronized (this) {//ensure the replace keep synchronized
                            this.setIp(str[0]);
                            this.setPort(str[1]);
                            System.out.println("current Ip : "+this.getIp()+" port : "+this.getPort()+"\n");
                        }
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        this.consume();
    }
}
