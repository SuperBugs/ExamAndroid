package www.cjkj.com.baiyue.internet;


import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import www.cjkj.com.baiyue.common.CommonConfig;
import www.cjkj.com.baiyue.common.MyHttpUrlConnectionGet;

public class PostSuggestionData {
    private String mContent;
    private String mContact;
    private String mAccount;
    private boolean result;

    public boolean postSuggestionData(String content, String contact, String account) {
        try {
            //对中文进行编码
            mContact = URLEncoder.encode(contact, "utf-8");
            mContent = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mAccount = account;
        post();
        return result;
    }

    private void post() {
        /**
         * 提交用户建议
         * url:http://url/index.php?c=FeedBack&f=suggestion&account=123&content=厉害&
         * contact=123
         */
        String shareAddress =
                CommonConfig.FeadBackUrl +
                        "&" +
                        CommonConfig.FeadBackParamOne+
                        mAccount +
                        "&" +
                        CommonConfig.FeadBackParamTwo +
                        mContent +
                        "&" +
                        CommonConfig.FeadBackParamThree +
                        mContact;
        //向服务器发送请求
        String response = MyHttpUrlConnectionGet.sendHttpRequest(shareAddress);
        try {
            JSONObject json = new JSONObject(response);
            String res = json.getString("result");
            if (res.equals("true")) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();

        }

    }
}
