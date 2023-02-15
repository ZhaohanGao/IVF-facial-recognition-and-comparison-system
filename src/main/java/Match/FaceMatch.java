package Match;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.Base64Util;
import utils.FileUtil;
import utils.GsonUtils;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Breon
 * @description: TODO
 * @date: 2022/12/16 20:19
 */
public class FaceMatch {

    public static BigDecimal faceMatch() {
        // 请求url
        String url = "use your own url";
        try {
            byte[] data1 = FileUtil.readFileByBytes("D:\\项目\\facial2.0\\statics\\images\\IDCard_photo.jpg");
            byte[] data2 = FileUtil.readFileByBytes("D:\\项目\\facial2.0\\statics\\images\\camera.png");

            HashMap<String, Object> mapOne= new HashMap<>();
            HashMap<String, Object> mapTwo= new HashMap<>();

            mapOne.put("image", Base64Util.encode(data1));
            mapOne.put("image_type", "BASE64");
            mapTwo.put("image", Base64Util.encode(data2));
            mapTwo.put("image_type", "BASE64");

            Object parm = new Object[]{mapOne,mapTwo};
            String param = GsonUtils.toJson(parm);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);

            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject res= (JSONObject) jsonObject.get("result");
            BigDecimal score= (BigDecimal) res.get("score");


            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
