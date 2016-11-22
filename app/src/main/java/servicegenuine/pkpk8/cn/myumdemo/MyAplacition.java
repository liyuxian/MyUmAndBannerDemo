package servicegenuine.pkpk8.cn.myumdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by wr on 2016/11/11.
 */
public class MyAplacition extends Application {
    private MobclickAgent.UMAnalyticsConfig mUMAnalyticsConfig;

    @Override
    public void onCreate() {
        super.onCreate();
      /*  mUMAnalyticsConfig = new MobclickAgent.UMAnalyticsConfig(this,"58255935717c194bf00026b9","apple");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        MobclickAgent. startWithConfigure(mUMAnalyticsConfig );*/
        Fresco.initialize(this);//初始化
        MobclickAgent.enableEncrypt(true); //日志加密
        MobclickAgent.openActivityDurationTrack(false); // 禁止默认的页面统计方式
        MobclickAgent.setDebugMode(false);//使用集成测试模式  上线 需关闭或注释本行代码
    }
}
