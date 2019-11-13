package org.lzk;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * @author lzk
 * @description
 * @date 2019/11/11
 */
public class SocketClient {
    public void request(String ip, int port) throws IOException, InterruptedException {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream, true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw.println("GET / HTTP/1.1");
        pw.println("Connection: keep-alive");
        pw.println("Host: www.baidu.com");
        pw.println("Pragma: no-cache");
        pw.println("Upgrade-Insecure-Requests: 1");
        pw.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
        pw.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        pw.println("Accept-Encoding: gzip, deflate, br");
        pw.println("Accept-Language: zh-CN,zh;q=0.9,en;q=0.8");
        pw.println("Cookie: BAIDUID=57F04BAD4E16C4860E07143DC1954AA3:FG=1; PSTM=1572953541; H_PS_PSSID=1440_21094_29567_29699_29221_26350; BD_UPN=12314753; BIDUPSID=57F04BAD4E16C4860E07143DC1954AA3; delPer=0; BD_CK_SAM=1; PSINO=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; pgv_pvi=9575900160; pgv_si=s9554696192; BDSVRTM=126; COOKIE_SESSION=422032_1_0_0_2_0_0_0_0_0_0_0_0_0_1_0_1572953549_1573375580_1573375580%7C2%230_1_1573375580%7C1; shifen[149024552344_38859]=1573375582; BCLID=11654433677095426869; BDSFRCVID=1xFOJeC62A21OXvwvFJs-VnqKeK58TJTH6aISHhHHl_F9PkOvWXgEG0P_U8g0Kubn_OcogKK0mOTHv-F_2uxOjjg8UtVJeC6EG0Ptf8g0f5; H_BDCLCKID_SF=tJ-OVCPhtC-3Db8k247oh4-s5fnQ-Jv0aDveBn5SabrJJbTpqROoK5nL2-j9BtQmJJrCoDjvyPQbflQ_KIc4hR_Ubt4tqp3ZQg-q3lTRWnn1ObR3Q6rmMU4B3n7M0x-jLNOPVn0MW-5DDx7O-PnJyUnQhtnnBnKL3H8HL4nv2JcJbM5m3x6qLTKkQN3T-PKO5bRh_CcJ-J8XhI-mDTjP");


        StringBuilder sb = new StringBuilder(1024);
        String s = "";

        while (true) {
            if (br.ready()) {
                s = br.readLine();
                if (s == null) {
                    break;
                }
                System.out.println(s);
                sb.append(s);
            }
            Thread.currentThread().sleep(50);
        }

        System.out.println(sb.toString());

        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new SocketClient().request("182.61.200.7", 443);
    }


}
