package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
  
public class UdpFileReceiver {
	// udp는 sender말고 receiver를 먼저 실행해서 받을준비를 해야한다.
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
    public static void main(String[] args) throws Exception {
    	
        int port = 8888;
 
        long fileSize;
        long totalReadBytes = 0;
         
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int nReadSize = 0;
        System.out.println("파일 수신 대기중...");
          
        DatagramSocket ds = new DatagramSocket(port);
        FileOutputStream fos = null;       
        fos = new FileOutputStream("d:/D_Other/Tulips3.jpg");
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);//스타트메세지받음.?
        String str = new String(dp.getData()).trim();
         
        if (str.equals("start")){ // sender에서 전송을 시작한 경우...
        	// 튤립파일의 바이너리데이터를 족족받는다.
        	
            dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp); // 전체사이즈를 받는다.
            str = new String(dp.getData()).trim();
            fileSize = Long.parseLong(str);
            double startTime = System.currentTimeMillis(); 
            while (true) {
                ds.receive(dp);
                str = new String(dp.getData()).trim();
                nReadSize = dp.getLength();
                fos.write(dp.getData(), 0, nReadSize);
                totalReadBytes+=nReadSize;
                System.out.println("In progress: " + totalReadBytes + "/"
                        + fileSize + " Byte(s) ("
                        + (totalReadBytes * 100 / fileSize) + " %)");
                if(totalReadBytes>=fileSize) {// 받은파일사이즈가 처음에받은 전체사이즈보다 크거나 같을경우 다 받은거니 끝낸다.
                	break;
                }
            }
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;// 밀리세컨말고 초로 보여주려고 1000으로 나눴다.
            double transferSpeed = (fileSize / 1000)/ diffTime;// 받는데 걸린시간.
             
            System.out.println("time: " + diffTime+ " second(s)");
            System.out.println("Average transfer speed: " + transferSpeed + " KB/s");
            System.out.println("File transfer completed");
            
            System.out.println("수신 처리 완료...");
            fos.close();
            ds.close();
            
        }else{
            System.out.println("수신 처리 불가!!!");
            fos.close();
            ds.close();
        }
  
    }
}

