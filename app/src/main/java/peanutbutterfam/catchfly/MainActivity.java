package peanutbutterfam.catchfly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

class Block {
    public final static int LEFT_BLOCKS = 15;  // 막을 수 있는 블럭 개수
    public final static int POS_Y = 6; // 블럭의 세로 개수
    public final static int POS_X = 6; // 블럭의 가로 개수
    public final static int BLOCK_NUM = POS_Y * POS_X; // 총 블럭 개수
}

public class MainActivity extends AppCompatActivity {
    public static int leftBlocks; // 막을 수 있는 블럭 개수

    ArrayList<Button> btn_al = new ArrayList<>(); //버튼 리스트
    ArrayList<Boolean> isSelected = new ArrayList<>(); //사용자가 선택한 버튼

    TextView tvBlocks;
    Drawable defaultBtn;
    MediaPlayer mediaPlayer;

    Boolean isMyTurn = false;
    Boolean isWin = false;
    int currentPos; // 현재 파리의 위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        leftBlocks = Block.LEFT_BLOCKS;

        // for를 이용한 버튼id 연결 "btn_al.add(findViewById(R.id.pos00));" 대신 사용
        for(int i=0; i<Block.POS_Y; i++) {
            for(int j=0; j<Block.POS_X; j++) {
                int buttonId = getResources().getIdentifier("pos"+i+""+j, "id", getPackageName());
                btn_al.add(findViewById(buttonId));
            }
        }

        defaultBtn = btn_al.get(0).getBackground();

        tvBlocks = findViewById(R.id.tvBlocks);

        UserBtnListener userListener = new UserBtnListener();

        for(int i = 0; i < btn_al.size(); i++) {
            btn_al.get(i).setOnClickListener(userListener);
        }

        for(int i = 0; i < Block.BLOCK_NUM; i++) {
            isSelected.add(false);
        }

        //파리 처음 위치 놓기
        putFirstLoc();
    }

    public void putFirstLoc() { //처음 파리가 위치할 장소 지정
        currentPos = (int)(Math.random() * Block.BLOCK_NUM); // 0 ~ 35
        btn_al.get(currentPos).setBackgroundResource(R.drawable.bee);
        isMyTurn = true;
    }

    public void moveBee() { // 사용자부터 번갈아 플레이
        System.out.println("현재 위치 : " + currentPos);

        ArrayList<Integer> availablePos = new ArrayList<>();

        // 가능한 위치 인덱스 넣기
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for(int i=0; i<dx.length; i++) {
            int tmp_y = (currentPos / Block.POS_X) + dy[i];
            int tmp_x = (currentPos % Block.POS_X) + dx[i];
            if (-1 < tmp_y && tmp_y < Block.POS_Y && -1 < tmp_x && tmp_x < Block.POS_X) {
                int origin_pos = tmp_y * Block.POS_X + tmp_x;
                if(!isSelected.get(origin_pos)) {
                    availablePos.add(origin_pos);
                }
            }
        }

        // 파리가 움직일 칸이 있을경우
        if(availablePos.size() > 0) {
            Collections.shuffle(availablePos);
            int movedPos = availablePos.get(0);
            System.out.println("이동할 위치 : " + movedPos);
            btn_al.get(currentPos).setBackground(defaultBtn);
            btn_al.get(movedPos).setBackgroundResource(R.drawable.bee);
            currentPos = movedPos;
            isMyTurn = true;
        }
        else { // 파리가 더 이상 움직일 칸이 없을경우
            isWin = true;
            showResultDialog();
        }
    }

    public void showResultDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(isWin == true){
            builder.setTitle("YOU WIN");
        }else{
            builder.setTitle("YOU LOSE");
        }

        builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mediaPlayer.stop();
                finish();
            }
        });
        builder.setNegativeButton("다시 플레이", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                mediaPlayer.stop();
                finish();
            }
        });
        builder.show();
    }

    class UserBtnListener implements View.OnClickListener { // 사용자가 버튼을 선태한 경우 리스너
        @Override
        public void onClick(View v) {
            Button tmp = (Button)v;

            int clickedBtnNum = -1;

            for(int i = 0; i < btn_al.size(); i++) {
                if(tmp == btn_al.get(i)) {
                    clickedBtnNum = i;
                }
            }

            if(isMyTurn == true) {
                if(isSelected.get(clickedBtnNum) == false && clickedBtnNum != currentPos) {   // 파리가 위치한 곳을 클릭할 수 없음
                    //색상 변경
                    tmp.setBackgroundColor(Color.parseColor("#FFCC00"));

                    //클릭한 버튼 더 이상 클릭 못하게
                    System.out.println("선택된 버튼 : " + clickedBtnNum);
                    isSelected.set(clickedBtnNum, true);

                    if(MainActivity.leftBlocks > 0) {
                        //남은 블럭 수 -1
                        MainActivity.leftBlocks--;
                        tvBlocks.setText("남은 블락 " + MainActivity.leftBlocks);
                    }
                    else {
                        //"You Lose" alertdialog
                        isWin = false;
                        showResultDialog();
                    }
                    isMyTurn = false;

                    //deleteBee = true;
                    moveBee();
                }
            }
        }
    }
}

