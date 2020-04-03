package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
  
public class UdpFileSender {
	
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 8888;
         
        File file = new File("d:/D_Other/Tulips.jpg");
        DatagramSocket ds = null; 
        if (!file.exists()) {// 파일존재하는지 체크
            System.out.println("파일이 존재하지 않습니다.");
            System.exit(0);
        }
        long fileSize = file.length(); // 파일사이즈를 바이트단위로 리턴한다.
        long totalReadBytes = 0; //전체바이트가 몇바이트인지알려주려고 선언만해두었다.
 
        double startTime = 0;  
        try {
            ds = new DatagramSocket();//1.소켓만든다.
            InetAddress serverAdd = InetAddress.getByName(serverIP);
            startTime = System.currentTimeMillis();
            String str = "start"; // 전송 시작을 알려주기 위한 문자열
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
            // getByte: 문자열을 byte 배열(바이너리파일)로 바꿔준다.
            ds.send(dp);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
             
            str = String.valueOf(fileSize); // 총 파일 사이즈 정보를 알려줌.
            dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);// ip주소를 객체화해서 넣음.
            ds.send(dp);// 스타트메세지보냄.
             
            while (true) {
                Thread.sleep(10); // 패킷전송간의 간격을 주기 위해서..
                int readBytes = fis.read(buffer, 0, buffer.length);// 버퍼가없으면 1바이트씩보낸다.버퍼가 10000이어서 10000개씩보낼것임.
                if (readBytes == -1)// 더이상 읽을 데이터가 없으면.
                    break;
                dp = new DatagramPacket(buffer, readBytes, serverAdd, port); // 읽어온 파일내용 패킷에 담기
                ds.send(dp);// 사이즈보냄.
                totalReadBytes += readBytes;// 10000씩 늘어날것임.(버퍼때문에 10000개씩 보낼거니까.)
                System.out.println("In progress: " + totalReadBytes + "/"
                        + fileSize + " Byte(s) ("
                        + (totalReadBytes * 100 / fileSize) + " %)");// 다운로드가 몇퍼센트되었는지 알려주기위해 찍어놓은 부분.
            
            }
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;;// 전송속도를 알려주기위함.
            double transferSpeed = (fileSize / 1000)/ diffTime;
             
            System.out.println("time: " + diffTime+ " second(s)");
            System.out.println("Average transfer speed: " + transferSpeed + " KB/s");
             
            str = "end"; // 전송이 완료되었음을 알리기 위한 문자열. 상대방은 이거없으면 데이터를 다보낸건지 아닌건지 모른다.
            dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
            ds.send(dp);
            //Thread.sleep(5000); 
            System.out.println("전송 완료...");
            fis.close();
            ds.close();
  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
  
    }
  
}


