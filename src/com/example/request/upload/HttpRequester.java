package com.example.request.upload;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class HttpRequester {
    private static final String TAG = HttpRequester.class.getSimpleName();
    private static int REQUEST_TIME_OUT = 100 * 1000;

    public static String post(String uploadUrl, Map<String, String> paramsMap, String inputStreamKey, String filePath) {
//        if (TextUtils.isEmpty(uploadUrl) || null == paramsMap || paramsMap.isEmpty()
//                || TextUtils.isEmpty(inputStreamKey) || TextUtils.isEmpty(filePath)) {
//            return null;
//        }

        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        print("serverAddress:" + uploadUrl);
        String result = "";
        String PREFIX = "--";
        String LINEND = "\r\n";
        String CHARSET = "UTF-8";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(REQUEST_TIME_OUT);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }
            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());

            /**
             * 涓嬮潰鏄紶鍥�
             */
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            sb1.append("Content-Disposition: form-data; name=\"" + inputStreamKey + "\"; filename=\"" + fileName + "\""
                    + LINEND);
            sb1.append("Content-Type: image/jpg; charset=" + CHARSET + LINEND);
            sb1.append(LINEND);
            outStream.write(sb1.toString().getBytes());
            byte[] buffer = new byte[1024];
            int len = 0;
//            print("杩斿洖inputStream == null ? " + (inputStream == null));
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }

            inputStream.close();
            outStream.write(LINEND.getBytes());

            // 璇锋眰缁撴潫鏍囧織
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                print("返回码" + responseCode);
                return null;
            }
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in, CHARSET);
            BufferedReader bufReader = new BufferedReader(isReader);
            result = bufReader.readLine();
            print("杩斿洖result锛�" + result);
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void print(String str) {
        Log.d(TAG, str);
    }

    public static String post(String uploadUrl, Map<String, String> paramsMap, ArrayList<String> inputStreamKeys, ArrayList<String> filePaths) {
        if (TextUtils.isEmpty(uploadUrl) || null == paramsMap || paramsMap.isEmpty()
                || inputStreamKeys == null || filePaths == null) {
            return null;
        }
        Log.i("aaaaaaaaaaaa",filePaths.toString());
        print("serverAddress:" + uploadUrl);
        String result = "";
        String PREFIX = "--";
        String LINEND = "\r\n";
        String CHARSET = "UTF-8";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(REQUEST_TIME_OUT);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            // 棣栧厛缁勬嫾鏂囨湰绫诲瀷鐨勫弬鏁�
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                // 杩欓噷鍔犱笂杩欏彞璇� 锛屼笉鐭ラ亾鏈嶅姟绔负浠�涔堜細鏄贡鐮�
                // sb.append("Content-Type: text/plain; charset=" + CHARSET +
                // LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);

//                print("鍙傛暟锛�" + entry.getKey() + "鍊硷細" + entry.getValue());
            }
            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());

            /**
             * 涓嬮潰鏄紶鍥�
             */
            ArrayList<String> fileNames = new ArrayList<String>();
            for (int i=0;i<filePaths.size();i++){
                fileNames.add(filePaths.get(i).substring(filePaths.get(i).lastIndexOf("/")));
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(filePaths.get(i));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"" + inputStreamKeys.get(i) + "\"; filename=\"" + fileNames.get(i) + "\""
                        + LINEND);
                sb1.append("Content-Type: image/jpg; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());
                byte[] buffer = new byte[1024];
                int len = 0;
//            print("杩斿洖inputStream == null ? " + (inputStream == null));
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }

                inputStream.close();
                outStream.write(LINEND.getBytes());

                // 璇锋眰缁撴潫鏍囧織
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
                outStream.write(end_data);
                outStream.flush();
            }


            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                print("杩斿洖responseCode锛�" + responseCode);
                return null;
            }
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in, CHARSET);
            BufferedReader bufReader = new BufferedReader(isReader);
            result = bufReader.readLine();
            print("杩斿洖result锛�" + result);
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
