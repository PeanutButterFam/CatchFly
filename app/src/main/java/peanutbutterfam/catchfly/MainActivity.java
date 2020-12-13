package peanutbutterfam.catchfly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

class Block{
    public static int leftBlocks = 15;
}

public class MainActivity extends AppCompatActivity {

    ArrayList<Button> btn_al = new ArrayList<>(); //버튼 리스트
    ArrayList<Boolean> isSelected = new ArrayList<>(); //사용자가 선택한 버튼
    TextView leftBlocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_al.add((Button)findViewById(R.id.pos00));
        btn_al.add((Button)findViewById(R.id.pos01));
        btn_al.add((Button)findViewById(R.id.pos02));
        btn_al.add((Button)findViewById(R.id.pos03));
        btn_al.add((Button)findViewById(R.id.pos04));
        btn_al.add((Button)findViewById(R.id.pos05));

        btn_al.add((Button)findViewById(R.id.pos10));
        btn_al.add((Button)findViewById(R.id.pos11));
        btn_al.add((Button)findViewById(R.id.pos12));
        btn_al.add((Button)findViewById(R.id.pos13));
        btn_al.add((Button)findViewById(R.id.pos14));
        btn_al.add((Button)findViewById(R.id.pos15));

        btn_al.add((Button)findViewById(R.id.pos20));
        btn_al.add((Button)findViewById(R.id.pos21));
        btn_al.add((Button)findViewById(R.id.pos22));
        btn_al.add((Button)findViewById(R.id.pos23));
        btn_al.add((Button)findViewById(R.id.pos24));
        btn_al.add((Button)findViewById(R.id.pos25));

        btn_al.add((Button)findViewById(R.id.pos30));
        btn_al.add((Button)findViewById(R.id.pos31));
        btn_al.add((Button)findViewById(R.id.pos32));
        btn_al.add((Button)findViewById(R.id.pos33));
        btn_al.add((Button)findViewById(R.id.pos34));
        btn_al.add((Button)findViewById(R.id.pos35));

        btn_al.add((Button)findViewById(R.id.pos40));
        btn_al.add((Button)findViewById(R.id.pos41));
        btn_al.add((Button)findViewById(R.id.pos42));
        btn_al.add((Button)findViewById(R.id.pos43));
        btn_al.add((Button)findViewById(R.id.pos44));
        btn_al.add((Button)findViewById(R.id.pos45));

        btn_al.add((Button)findViewById(R.id.pos50));
        btn_al.add((Button)findViewById(R.id.pos51));
        btn_al.add((Button)findViewById(R.id.pos52));
        btn_al.add((Button)findViewById(R.id.pos53));
        btn_al.add((Button)findViewById(R.id.pos54));
        btn_al.add((Button)findViewById(R.id.pos55));

        UserBtnListener userListener = new UserBtnListener();

        for(int i = 0; i < btn_al.size(); i++){
            btn_al.get(i).setOnClickListener(userListener);
        }

        //파리 처음 위치 놓기
        putFirstLoc();

    }

    public void putFirstLoc(){ //처음 파리가 위치할 장소 지정
        int randomLoc = (int)(Math.random() * 36);
        Drawable defbtn = btn_al.get(0).getBackground();

        btn_al.get(randomLoc).setBackgroundResource(R.drawable.bee);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_al.get(randomLoc).setBackground(defbtn);
            }
        }, 1000);

    }

    class UserBtnListener implements View.OnClickListener{ //사용자가 버튼을 선태한 경우 리스너
        @Override
        public void onClick(View v) {
            Button tmp = (Button)v;
            //색상 변경
            tmp.setBackgroundColor(Color.YELLOW);

            if(Block.leftBlocks >= 0){
                //남은 블럭 수 -1
                Block.leftBlocks--;
            }else{
                //"You Lose" alertdialog
                showLoseDialog();
            }
        }

        public void showLoseDialog(){
           ;
        }
    }
}

