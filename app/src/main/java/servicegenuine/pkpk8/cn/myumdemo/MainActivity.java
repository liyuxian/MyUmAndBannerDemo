package servicegenuine.pkpk8.cn.myumdemo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBannerClickListener {
    private List<Integer> images = new ArrayList<Integer>();
    private List<String> titles = new ArrayList<String>();
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageData();
        banner = (Banner) findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE).
                setImages(images).
                setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //用fresco加载图片简单用法
                Uri uri = Uri.parse("android.resource://servicegenuine.pkpk8.cn.myumdemo/" + path);
                imageView.setImageURI(uri);
            }
        }).setBannerAnimation(Transformer.ScaleInOut).
                setIndicatorGravity(BannerConfig.LEFT).
                setBannerTitles(titles).
                setOnBannerClickListener(this);
        banner.start();
    }

    /**
     * 初始 图片数据
     */
    private void initImageData() {
        images.add(R.drawable.dingdanweikong);
        images.add(R.drawable.etc);
        images.add(R.drawable.etc2);
        images.add(R.drawable.jiyou);
        titles.add("dingdanweikong");
        titles.add("etc");
        titles.add("etc2");
        titles.add("jiyou");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ssss"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ssss"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MobclickAgent.onKillProcess(this);
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("--", "onStart");
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("--", "onStop");
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, titles.get(position - 1), Toast.LENGTH_SHORT).show();
    }
}
