package com.cugb.xiaob.a2048_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnTouchListener,OnGestureListener {

    //创建一个用于识别收拾的GestureDetector对象
    private GestureDetector detector = new GestureDetector(this);
    //检测移动的最小距离，避免误操作
    private final static int MIN_MOVE = 200;
    //debug用
    private final static String TAG = "MyGesture";

    //向左划动
    public void left() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int y = j + 1; y < 4; y++) {
                    if (shuzu[i][y] != 0) {
                        if (shuzu[i][j] == 0) {
                            shuzu[i][j] = shuzu[i][y];
                            shuzu[i][y] = 0;
                            y = j + 1;
                        } else if (shuzu[i][j] == shuzu[i][y]) {
                            if (y == j + 1 || (shuzu[i][j + 1] == 0 && shuzu[i][y - 1] == 0)) {
                                shuzu[i][j] += shuzu[i][j];
                                shuzu[i][y] = 0;
                                if (shuzu[i][j] == 2048)
                                    Toast.makeText(MainActivity.this, "コングラチュレーション", Toast.LENGTH_SHORT).show();
                                j += 1;
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "left");
    }

    //向右划动
    public void right() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                for (int y = j - 1; y >= 0; y--) {
                    if (shuzu[i][y] != 0) {
                        if (shuzu[i][j] == 0) {
                            shuzu[i][j] = shuzu[i][y];
                            shuzu[i][y] = 0;
                            y = j - 1;
                        } else if (shuzu[i][j] == shuzu[i][y]) {
                            if (y == j - 1 || (shuzu[i][j - 1] == 0 && shuzu[i][y + 1] == 0)) {
                                shuzu[i][j] += shuzu[i][j];
                                shuzu[i][y] = 0;
                                if (shuzu[i][j] == 2048)
                                    Toast.makeText(MainActivity.this, "コングラチュレーション", Toast.LENGTH_SHORT).show();
                                j -= 1;
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "right");
    }

    //向上划动
    public void up() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (int x = i + 1; x < 4; x++) {
                    if (shuzu[x][j] != 0) {
                        if (shuzu[i][j] == 0) {
                            shuzu[i][j] = shuzu[x][j];
                            shuzu[x][j] = 0;
                            x = i + 1;
                        } else if (shuzu[i][j] == shuzu[x][j]) {
                            if (x == i + 1 || (shuzu[i + 1][j] == 0 && shuzu[x - 1][j] == 0)) {
                                shuzu[i][j] += shuzu[i][j];
                                shuzu[x][j] = 0;
                                if (shuzu[i][j] == 2048)
                                    Toast.makeText(MainActivity.this, "コングラチュレーション", Toast.LENGTH_SHORT).show();
                                i += 1;
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "up");
    }

    //向下划动
    public void down() {
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                for (int x = i - 1; x >= 0; x--) {
                    if (shuzu[x][j] != 0) {
                        if (shuzu[i][j] == 0) {
                            shuzu[i][j] = shuzu[x][j];
                            shuzu[x][j] = 0;
                            x = i - 1;
                        } else if (shuzu[i][j] == shuzu[x][j]) {
                            if (x == i - 1 || (shuzu[i - 1][j] == 0 && shuzu[x + 1][j] == 0)) ;
                            shuzu[i][j] += shuzu[i][j];
                            shuzu[x][j] = 0;
                            if (shuzu[i][j] == 2048)
                                Toast.makeText(MainActivity.this, "コングラチュレーション", Toast.LENGTH_SHORT).show();
                            i -= 1;
                        }
                    }
                }
            }
        }
        Log.d(TAG, "down");
    }


    //重写OnTouchListener的onTouch方法
//此方法在触摸屏被触摸，即发生触摸事件的时候被调用。
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    //在按下动作时被调用
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //在划动动作时被调用
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > MIN_MOVE) {
            bf();
            left();
            yidong();
            jiance();
            suiji();
            xianshi();
        } else if (e2.getX() - e1.getX() > MIN_MOVE) {
            bf();
            right();
            yidong();
            jiance();
            suiji();
            xianshi();
        } else if (e1.getY() - e2.getY() > MIN_MOVE) {
            bf();
            up();
            yidong();
            jiance();
            suiji();
            xianshi();
        } else if (e2.getY() - e1.getY() > MIN_MOVE) {
            bf();
            down();
            yidong();
            jiance();
            suiji();
            xianshi();
        }
        return false;
    }


    //在长按时被调用
    @Override
    public void onLongPress(MotionEvent e) {
    }

    //在滚动时调用
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    //在按住时被调用
    @Override
    public void onShowPress(MotionEvent e) {
    }

    //在抬起时被调用
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    //实时表示游戏状况的二维数组
    int[][] shuzu = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    int[][] beifen = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    //分数
    int score = -1;
    //检测是否能够继续游戏的状态值
    private int state = 0;

    // dubug用，完成后删除
    private void debug() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Log.d(TAG, "shuzu" + x + "," + y + "=" + shuzu[x][y] + "|||" + "beifen" + x + "," + y + "=" + beifen[x][y]);
            }
            Log.d(TAG, "----");
        }

        Log.d(TAG, "_____________" + "state" + state + "_______________");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xinyouxi();
        bf();
        TableLayout tb = (TableLayout) findViewById(R.id.yxhm);
//监听这个ImageView组件上的触摸屏时间
        tb.setOnTouchListener(this);
//下面两个要记得设哦，不然就没法处理轻触以外的事件了，例如抛掷动作。
        tb.setLongClickable(true);
        detector.setIsLongpressEnabled(true);
        Button bt = (Button) findViewById(R.id.replay);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xinyouxi();
            }
        });
        Button btrd = (Button) findViewById(R.id.redo);
        btrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chexiao();
            }
        });
    }

    //检查是否gameover
    private void jiance() {
        int s1 = 1, s2 = 1;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (shuzu[x][y] == 0 || shuzu[x][y + 1] == 0) {
                    s1 = 0;
                    Log.d(TAG, "有零元素");
                    break;
                }
                if (shuzu[x][y] == shuzu[x][y + 1]) {
                    s1 = 0;
                    Log.d(TAG, "横向有相等相邻元");
                }
                if (s1 == 0) break;
            }
            if (s1 == 0) break;
        }
        if (s1 == 1) {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 3; x++) {
                    if (shuzu[x][y] == shuzu[x + 1][y]) {
                        s2 = 0;
                        Log.d(TAG, "纵向有相等相邻元");
                    }
                    if (s2 == 0) break;
                }
                if (s2 == 0) break;
            }
        }
        if (s2 == 1 && s1 == 1) {
            state = 1;
            Log.d(TAG, "game over");
            Toast.makeText(MainActivity.this, "もうゲームオーバーから、始めからやり直すください。", Toast.LENGTH_LONG).show();
        }
    }

    //检测是否没有移动数字
    private void yidong() {
        int s = 1;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (shuzu[x][y] != beifen[x][y]) {
                    s = 0;
                    state = 0;
                }
            }
        }
        if (s == 1) {
            state = 2;
        }
    }

    //随机产生新的数字
    private void suiji() {
        if (state == 0) {
            Random random = new Random();
            int x = random.nextInt(4);
            int y = random.nextInt(4);
            if (shuzu[x][y] == 0) {
                int r = random.nextInt(100);
                if (r < 85) {
                    shuzu[x][y] = 2;
                } else {
                    shuzu[x][y] = 4;
                }
                score++;
                fenshu();
            } else suiji();
        } else if (state == 2) {
            Toast.makeText(MainActivity.this, "No number moving!", Toast.LENGTH_SHORT).show();
        } else if (state == 1) {
            Toast.makeText(MainActivity.this, "ゲームオーバー", Toast.LENGTH_LONG).show();
        }
    }

    //将数组和图片对应起来,每次操作数组后应该使用
    private void xianshi() {
        if (state == 0) {
            ImageView Img_11 = (ImageView) findViewById(R.id.pic_11);
            ImageView Img_12 = (ImageView) findViewById(R.id.pic_12);
            ImageView Img_13 = (ImageView) findViewById(R.id.pic_13);
            ImageView Img_14 = (ImageView) findViewById(R.id.pic_14);
            ImageView Img_21 = (ImageView) findViewById(R.id.pic_21);
            ImageView Img_22 = (ImageView) findViewById(R.id.pic_22);
            ImageView Img_23 = (ImageView) findViewById(R.id.pic_23);
            ImageView Img_24 = (ImageView) findViewById(R.id.pic_24);
            ImageView Img_31 = (ImageView) findViewById(R.id.pic_31);
            ImageView Img_32 = (ImageView) findViewById(R.id.pic_32);
            ImageView Img_33 = (ImageView) findViewById(R.id.pic_33);
            ImageView Img_34 = (ImageView) findViewById(R.id.pic_34);
            ImageView Img_41 = (ImageView) findViewById(R.id.pic_41);
            ImageView Img_42 = (ImageView) findViewById(R.id.pic_42);
            ImageView Img_43 = (ImageView) findViewById(R.id.pic_43);
            ImageView Img_44 = (ImageView) findViewById(R.id.pic_44);
            shuaxin(Img_11, shuzu[0][0]);
            shuaxin(Img_12, shuzu[0][1]);
            shuaxin(Img_13, shuzu[0][2]);
            shuaxin(Img_14, shuzu[0][3]);
            shuaxin(Img_21, shuzu[1][0]);
            shuaxin(Img_22, shuzu[1][1]);
            shuaxin(Img_23, shuzu[1][2]);
            shuaxin(Img_24, shuzu[1][3]);
            shuaxin(Img_31, shuzu[2][0]);
            shuaxin(Img_32, shuzu[2][1]);
            shuaxin(Img_33, shuzu[2][2]);
            shuaxin(Img_34, shuzu[2][3]);
            shuaxin(Img_41, shuzu[3][0]);
            shuaxin(Img_42, shuzu[3][1]);
            shuaxin(Img_43, shuzu[3][2]);
            shuaxin(Img_44, shuzu[3][3]);
            //debug();
        } else if (state == 1 || state == 2) {
            Log.d(TAG, "state:" + state);
            //debug();
        }
    }

    //刷新图片
    private void shuaxin(ImageView a, Integer b) {
        if (b == 0) a.setImageResource(R.drawable.pic_0);
        else if (b == 2) a.setImageResource(R.drawable.pic_2);
        else if (b == 4) a.setImageResource(R.drawable.pic_4);
        else if (b == 8) a.setImageResource(R.drawable.pic_8);
        else if (b == 16) a.setImageResource(R.drawable.pic_16);
        else if (b == 32) a.setImageResource(R.drawable.pic_32);
        else if (b == 64) a.setImageResource(R.drawable.pic_64);
        else if (b == 128) a.setImageResource(R.drawable.pic_128);
        else if (b == 256) a.setImageResource(R.drawable.pic_256);
        else if (b == 512) a.setImageResource(R.drawable.pic_512);
        else if (b == 1024) a.setImageResource(R.drawable.pic_1024);
        else if (b == 2048) a.setImageResource(R.drawable.pic_2048);
        else Log.e("error", "e!");
    }

    //重新开始游戏
    private void xinyouxi() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                shuzu[x][y] = 0;
                beifen[x][y] = 0;
            }
        }
        score = -1;
        state = 0;
        suiji();
        xianshi();
    }

    //刷新分数
    private void fenshu() {
        TextView sc = (TextView) findViewById(R.id.score);
        sc.setText(" " + score);
    }

    //撤销一步
    private void chexiao() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                shuzu[x][y] = beifen[x][y];
            }
        }
        xianshi();
        if (state == 0) {
            score--;
            state = 2;
            fenshu();
        }
    }

    //备份
    private void bf() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (beifen[x][y] != shuzu[x][y]) {
                    beifen[x][y] = shuzu[x][y];
                }
            }
        }
    }
}

