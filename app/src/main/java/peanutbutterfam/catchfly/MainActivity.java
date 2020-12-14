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
import java.util.Collections;

class Block{
    public static int leftBlocks = 15;
}

public class MainActivity extends AppCompatActivity {

    ArrayList<Button> btn_al = new ArrayList<>(); //버튼 리스트
    ArrayList<Boolean> isSelected = new ArrayList<>(); //사용자가 선택한 버튼

    TextView tvBlocks;
    Drawable defaultBtn;

    Boolean isMyTurn = false;
    int currentPos;

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

        defaultBtn = btn_al.get(0).getBackground();

        tvBlocks = (TextView)findViewById(R.id.tvBlocks);

        UserBtnListener userListener = new UserBtnListener();

        for(int i = 0; i < btn_al.size(); i++){
            btn_al.get(i).setOnClickListener(userListener);
        }

        for(int i = 0; i < 36; i++){
            isSelected.add(false);
        }

        //파리 처음 위치 놓기
        putFirstLoc();
    }

    public void putFirstLoc(){ //처음 파리가 위치할 장소 지정
        int randomLoc = (int)(Math.random() * 36);
        btn_al.get(randomLoc).setBackgroundResource(R.drawable.bee);

        currentPos = randomLoc;

        isMyTurn = true;
    }

    public void moveBee(){ //사용자부터 번갈아 플레이
        System.out.println("현재 위치 : "+currentPos);

        btn_al.get(currentPos).setBackground(defaultBtn);

        ArrayList<Integer> availablePos = new ArrayList<>();

        int left = currentPos - 1;
        int right = currentPos + 1;
        int up = currentPos - 6;
        int down = currentPos + 6;

        //가능한 위치 인덱스 넣기
        switch (currentPos){
            case 0:
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
                break;
            case 5:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
                break;
            case 30:
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                break;
            case 35:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
                break;
            case 6:
            case 12:
            case 18:
            case 24:
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
                break;
            case 31:
            case 32:
            case 33:
            case 34:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                break;
            case 11:
            case 17:
            case 23:
            case 29:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
                break;
            default:
                if(isSelected.get(left) == false){
                    availablePos.add(left);
                }
                if(isSelected.get(right) == false){
                    availablePos.add(right);
                }
                if(isSelected.get(up) == false){
                    availablePos.add(up);
                }
                if(isSelected.get(down) == false){
                    availablePos.add(down);
                }
        }

        Collections.shuffle(availablePos);

        int movedPos = availablePos.get(0);

        System.out.println("이동할 위치 : "+movedPos);

        btn_al.get(movedPos).setBackgroundResource(R.drawable.bee);

        currentPos = movedPos;
        isMyTurn = true;
    }

    class UserBtnListener implements View.OnClickListener{ //사용자가 버튼을 선태한 경우 리스너
        @Override
        public void onClick(View v) {
            Button tmp = (Button)v;

            if(isMyTurn == true){
                if(isSelected.get(Integer.parseInt(tmp.getText().toString()) - 1) == false){
                    //색상 변경
                    tmp.setBackgroundColor(Color.YELLOW);
                    //클릭한 버튼 더 이상 클릭 못하게
                    int selectedBtn = Integer.parseInt(tmp.getText().toString()) - 1;

                    System.out.println("선택된 버튼 : "+selectedBtn);
                    isSelected.set(selectedBtn, true);

                    if(Block.leftBlocks > 0){
                        //남은 블럭 수 -1
                        Block.leftBlocks--;
                        tvBlocks.setText("남은 블락 "+Block.leftBlocks);
                    }else{
                        //"You Lose" alertdialog
                        showLoseDialog();
                    }
                    isMyTurn = false;

                    //deleteBee = true;
                    moveBee();
                }
            }
        }

        public void showLoseDialog(){
            Toast.makeText(MainActivity.this, "YOU LOSE", Toast.LENGTH_SHORT).show();
        }
    }
}

