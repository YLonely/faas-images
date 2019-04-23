import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    /**
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
     */
    public String getGeocoderLatitude(String lat, String lng) {
        System.out.println(lat + ",lng:" + lng);
        BufferedReader in = null;
        try {
            String uri = "http://api.map.baidu.com/geocoder/v2/?ak=Tqz9uAMlbpwlhNGNRc1fpewCO7EccTpz&callback=renderReverse&"
                    + "location=" + lat + "," + lng + "&output=json&pois=0";
            // "http://api.map.baidu.com/geocoder?callback=renderReverse&location="+ address
            // +"&output=json&pois=0&key="+ KEY_1
            URL tirc = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) tirc.openConnection();
            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
            conn.setDoOutput(true);
            // 尝试链接5次，如果正确的话就跳出循环没如果连接超时就重新连接
            for (int i = 0; i < 5; i++) {
                conn.connect();
                if (conn.getResponseCode() == 200)
                    break;
                else if (conn.getResponseCode() == 408)
                    Thread.sleep(5);
            }
            ;
            System.out.println(conn.getResponseCode());
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());

            }
            String str = sb.toString();
            System.out.println(str);
            if (str != null && str.split("formatted_address\"\\:\"")[1] != null
                    && str.split("formatted_address\"\\:\"")[1].split("\"\\,")[0] != null) {
                System.out.println(str.split("formatted_address\"\\:\"")[1].split("\"\\,")[0]);
                return str.split("formatted_address\"\\:\"")[1].split("\"\\,")[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String args[]) {
        Main baiduUtil = new Main();
        // 43.90637886442,lng:125.30130752475
        String str = baiduUtil.getGeocoderLatitude("43.90637886442", "125.30130752475");

    }
}