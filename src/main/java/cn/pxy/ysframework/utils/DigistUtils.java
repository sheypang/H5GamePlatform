package cn.pxy.ysframework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

//加密工具类
public class DigistUtils {

    //生成64位的UUID
    public static String getUUID64(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase()+
                UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }

    //生成32位的UUID
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }

    /** 传入文本内容，返回SHA-256 串*/
    public static String encry256(final String strText){
        return  SHA(strText,"SHA-256");
    }

    /** 传入文本内容，返回SHA-512 串*/
    public static String encry512(final String strText){
        return SHA(strText,"SHA-512");
    }

    /** 字符串，MD5加密 */
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /** 字符串 SHA加密*/
    private  static String SHA(final String strText,final String strType){

        // 返回值
        String strResult=null;

        // 是否是有效的字符串
        if (strText != null && strText.length()>0){

            // 加密开始，创建加密对象，并传入加密类型
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到bytes类型结果
                byte[] byteBuffer = messageDigest.digest();
                //将byte转为string
                StringBuffer strHexString = new StringBuffer();
                for (int i =0;i<byteBuffer.length;i++){
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length()==1){
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回的结果
                strResult = strHexString.toString();


            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        return  strResult;
    }
}
