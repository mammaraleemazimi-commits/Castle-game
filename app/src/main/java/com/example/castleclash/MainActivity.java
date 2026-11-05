package com.example.castleclash;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.graphics.*;
import java.util.*;
public class MainActivity extends Activity {
 public void onCreate(Bundle b){super.onCreate(b);setContentView(new Game());}
 class Unit {float x; int hp=3; boolean enemy; Unit(float xx,boolean e){x=xx;enemy=e;}}
 class Game extends View {
  Paint p=new Paint(3); ArrayList<Unit> units=new ArrayList<>(); 
  float w=1000,h=500; int playerHP=100,enemyHP=100,coins=100,frame=0,score=0; boolean over=false;
  Game(){super(MainActivity.this);}
  void rect(Canvas c,int color,float l,float t,float r,float b){p.setColor(color);c.drawRect(l,t,r,b,p);}
  void txt(Canvas c,String s,int color,float x,float y,int size){p.setColor(color);p.setTextSize(size);p.setTypeface(Typeface.create("sans-serif",Typeface.BOLD));c.drawText(s,x,y,p);}
  void castle(Canvas c,float x,int color){rect(c,color,x,250,x+95,405);rect(c,color,x-12,232,x+107,255);for(int i=0;i<4;i++)rect(c,color,x-10+i*32,211,x+10+i*32,242);rect(c,0xff302c37,x+34,341,x+65,405);}
  protected void onDraw(Canvas c){
   super.onDraw(c);c.save();c.scale(getWidth()/w,getHeight()/h);
   rect(c,0xff90d9f7,0,0,1000,500);rect(c,0xffb5e5a0,0,350,1000,500);rect(c,0xff76b95a,0,404,1000,500);
   p.setColor(0xffffe08a);c.drawCircle(480,85,37,p);
   castle(c,25,0xff4268c9);castle(c,875,0xffc64a47);
   rect(c,0xff242b37,30,35,280,55);rect(c,0xff4cd26e,30,35,30+2.5f*Math.max(0,playerHP),55);
   rect(c,0xff242b37,720,35,970,55);rect(c,0xffff5555,720,35,720+2.5f*Math.max(0,enemyHP),55);
   txt(c,"YOUR CASTLE",0xff172c4b,30,27,20);txt(c,"ENEMY CASTLE",0xff6b1e22,720,27,20);
   txt(c,"COINS: "+coins+"     SCORE: "+score,0xff243048,350,38,23);
   for(Unit u:units){p.setColor(u.enemy?0xffe74747:0xff326be5);c.drawCircle(u.x,379,16,p);rect(c,0xff34313a,u.x-9,391,u.x-3,410);rect(c,0xff34313a,u.x+3,391,u.x+9,410);rect(c,0xffffd1a1,u.x-8,354,u.x+8,368);}
   rect(c,0xff263b59,300,430,700,493);txt(c,"DEPLOY KNIGHT  -  20 COINS",0xffffffff,326,469,25);
   if(over){rect(c,0xcc102034,0,0,1000,500);txt(c,enemyHP<=0?"VICTORY!":"DEFEAT!",0xffffffff,365,220,54);txt(c,"TAP TO PLAY AGAIN",0xffffdf77,365,280,28);}
   c.restore();
   if(!over){frame++;if(frame%85==0)units.add(new Unit(845,true));if(frame%18==0)coins=Math.min(150,coins+1);
    for(Unit u:units){Unit target=null;float best=999;
     for(Unit v:units)if(v.enemy!=u.enemy&&v.hp>0&&Math.abs(v.x-u.x)<best){best=Math.abs(v.x-u.x);target=v;}
     if(target!=null&&best<33){if(frame%25==0){target.hp--;if(target.hp<=0&&!u.enemy){coins+=8;score+=10;}}}
     else {u.x+=u.enemy?-1.1f:1.4f;if(u.enemy&&u.x<130){playerHP-=2;u.hp=0;}if(!u.enemy&&u.x>860){enemyHP-=2;u.hp=0;}}
    }
    for(int i=units.size()-1;i>=0;i--)if(units.get(i).hp<=0)units.remove(i);
    if(playerHP<=0||enemyHP<=0)over=true;
   }postInvalidateDelayed(33);
  }
  public boolean onTouchEvent(MotionEvent e){if(e.getAction()!=MotionEvent.ACTION_DOWN)return true;
   if(over){over=false;playerHP=100;enemyHP=100;coins=100;score=0;frame=0;units.clear();}
   else if(e.getY()*h/getHeight()>415&&coins>=20){coins-=20;units.add(new Unit(150,false));}
   invalidate();return true;}
 }
}