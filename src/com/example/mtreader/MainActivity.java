package com.example.mtreader;


import java.util.List;

import com.app.activity.SelectWayActivity;
import com.app.utils.ToastUtil;
import com.example.erweima.R;
import com.example.mtreader.RootCmd;
import com.example.request.api.json.JsonRequestManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button start;
	private Intent mIntent;
	private TextView finish;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mWindowManagerParams;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		forbiddenHomeKey();
		System.loadLibrary("mt3x32");
		init();
		int dev = mt8deviceopen(0,115200);
		if(dev>0)
		{
			ToastUtil.showToast(this, "打开设备成功");
		}
		else 
		{						
			ToastUtil.showToast(this,"打开设备失败");
		}
		start = (Button) findViewById(R.id.start);
		finish = (TextView) findViewById(R.id.finish);
		start.setOnClickListener(onClickListener);
		finish.setOnClickListener(onClickListener);
		mIntent = new Intent();
		
	}
	
	private void forbiddenHomeKey(){  
	    mWindowManager = this.getWindowManager();  
	    mWindowManagerParams = new LayoutParams();
	    mWindowManagerParams.width = LayoutParams.WRAP_CONTENT;  
	    mWindowManagerParams.height = LayoutParams.WRAP_CONTENT;  
	   // internal system error windows, appear on top of everything they can  
	    mWindowManagerParams.type = LayoutParams.TYPE_SYSTEM_ERROR;  
	     // indicate this view don’t respond the touch event  
	    mWindowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCHABLE;  
	    // add an empty view on the top of the window  
	    View mEmptyView = new View(this);  
	    mWindowManager.addView(mEmptyView, mWindowManagerParams);  
	}

    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
//    	if(isAppForeground(this)){
    		ActivityManager activityManager = (ActivityManager) getApplicationContext()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.moveTaskToFront(getTaskId(), 0);
//    	}
//    	ActivityManager mAm = (ActivityManager)getSystemService(this.ACTIVITY_SERVICE);  
//        //获得当前运行的task  
//        List<ActivityManager.RunningTaskInfo> taskList = mAm.getRunningTasks(100);  
//        for (ActivityManager.RunningTaskInfo rti : taskList) {  
//            //找到当前应用的task，并启动task的栈顶activity，达到程序切换到前台  
//            if(rti.topActivity.getPackageName().equals(getPackageName())) {  
//                try {  
//                    Intent  resultIntent = new Intent(this, Class.forName(rti.topActivity.getClassName()));  
//                    resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);  
//                    this.startActivity(resultIntent);
//                }catch (ClassNotFoundException e) {  
//                    e.printStackTrace();  
//                }  
//                return;  
//            }  
//        }  
    }
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.start:
				mIntent.setClass(MainActivity.this,SelectWayActivity.class);
				startActivity(mIntent);
				break;
			case R.id.finish:
				finish();
				break;
			}
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}
		//设备操作函数
		public static native int mt8deviceopen(int port,int baud);
		public static native int mt8serialopen(String port, int baud);
		public static native int mt8deviceopenfd(int fd);
		
		public static native int mt8deviceclose();
		public static native int mt8deviceversion( int module,byte verlen[],byte verdata[]);
		public static native int mt8devicebeep(int delaytime, int times);
		
		//CPU 卡操作函数
		public static native int mt8samsltreset(int delaytime, int cardno, byte natrlen[],byte atr[]);
		public static native int mt8samsltpowerdown(int cardno);
		public static native int mt8cardAPDU(int cardno, int sendApduLen,byte sendApdu[], int nrecvLen[], byte recvApdu[]);
		
		//非接CPU卡操作函数
		public static native int mt8opencard(int delaytime,byte cardtype[],byte snrlen[],byte snr[],byte atrlen[],byte atr[]);
		public static native int mt8rfhalt(int delaytime);
		public static native int mt8GetCardType(int isContact[],byte Contact_Atr[],byte atrLen[],
				int isContactLess[],byte ContactLess_Snr[],byte snrLen[],
			int isIDcard[],int isMag[],int jtrack1_len[],int jtrack2_len[],int jtrack3_len[],
			byte jtrack1_data[],byte jtrack2_data[],byte jtrack3_data[]);
		
		//PBOC 金融IC卡
		//获取金融IC卡卡号、姓名
		public static native int ReadNAN (int nCardType,byte Cardno[],byte CardName[],byte lpErrMsg[]);
		
		//接触式CPU社保卡
		public static native int ReadSBInfo (byte lpSocialCardBasicinfo[],byte lpErrMsg[]);
		
		//M1卡操作函数
		public static native int mt8rfcard(int delaytime,byte cardtype[],byte cardID[]);
		public static native int mt8rfauthentication(int mode,int nsecno,byte key[]);
		public static native int mt8rfread(int nblock,byte readdata[]);
		public static native int mt8rfwrite(int nblock,byte writedata[]);
		public static native int mt8rfincrement(int nblock,int incvalue);
		public static native int mt8rfdecrement(int nblock,int decvalue);
		public static native int mt8rfinitval(int nblock,int writevalue);
		public static native int mt8rfreadval(int nblock,int readvalue[]);
		
		//磁条卡操作
		public static native int mt8magneticmode();
		public static native int mt8magneticread(int jtimeout,int jtrack1_len[],int jtrack2_len[],int jtrack3_len[],
				byte jtrack1_data[],byte jtrack2_data[],byte jtrack3_data[]);
		
		//接触式存储函数
		public static native int mt8contactsettype(int cardno,int cardtype);
		public static native int mt8contactidentifytype(int cardno,byte cardtype[]);
		public static native int mt8contactpasswordinit(int cardno,int pinlen,byte pinstr[]);
		public static native int mt8contactpasswordcheck(int cardno,int pinlen,byte pinstr[]);
		public static native int mt8contactread(int cardno,int address,int rlen,byte readdata[]);
		public static native int mt8contactwrite(int jcardno,int address,int wlen,byte writedata[]);
		
		//AT88SC1604
		public static native int mt8srd1604(int zone,int offset,int len,byte read_data_buffer[]);
		public static native int mt8swr1604(int zone,int offset,int len,byte write_data_buffer[]);
		public static native int mt8csc1604(int zone,int len,byte passwd[]);
		public static native int mt8cesc1604(int zone,int len,byte passwd);
		public static native int mt8wsc1604(int zone,int len,byte passwd[]);
		public static native int mt8ser1604(int zone,int offset,int len);
		public static native int mt8fakefus1604(int mode);
		public static native int mt8psnl1604();
		
		//二代证
		public static native int mt8CLCardOpen(int delaytime,byte cardtype[],byte snrlen[],byte snr[],byte rlen[],byte recdata[]);
		public static native int mt8IDCardRead(
				byte name[],
				byte sex[],
				byte nation[],
				byte birth[],
				byte address[],
				byte idnum[],
				byte asigndepartment[],
				byte datestart[],
				byte dateend[],
				int rphotodatalen[],
				byte photodatainfo[]);
		
		public static native int mt8IDCardGetCardInfo(int index,byte infodata[],int infodatalen[]);
		public static native int mt8IDCardGetModeID(byte IDLen[],byte sIDData[]);
		public static native int mt8IDCardReadIDNUM(byte rlen[],byte receivedata[]);
		
		
		//密码键盘
		public static native int mt8desencrypt(byte key[], byte ptrSource[], int msgLen,byte ptrDest[]);
		public static native int mt8desdecrypt(byte key[], byte ptrSource[], int msgLen,byte ptrDest[]);
		public static native int mt8des3encrypt(byte key[], byte ptrSource[], int msgLen,byte ptrDest[]);
		public static native int mt8des3decrypt(byte key[], byte ptrSource[], int msgLen,byte ptrDest[]);
		public static native int mt8pwddecrypt(byte ptrSource[], int nDataLen,byte ptrDest[]);
		public static native int mwkbdownloadmainkey(int destype, int mainkeyno,byte mainkey[]);
		public static native int mwkbdownloaduserkey(int destype, int mainkeyno,int userkeyno,byte userkey[]);
		public static native int mt8mwkbactivekey(int mainkeyno, int userkeyno);
		public static native int mt8mwkbsetpasslen(int passlen);
		public static native int mt8mwkbsettimeout(int timeout);
		public static native int mt8mwkbgetpin(int type, byte planpin[]);
		public static native int mt8mwkbgetenpin(int type, byte cardno[],byte planpin[]);
		
		//工具函数
		public static native int mt8hexasc(byte hex[],byte asc[],int len);
		public static native int mt8aschex(byte asc[],byte hex[],int len);
		public static native int mt8hexbase64(byte hex[],byte base64[],int hexlen);
		public static native int mt8base64hex(byte base64[],byte hex[],int base64len);
		public static native int mt8rfencrypt(byte key[],byte ptrSource[],int msgLen, byte ptrDest[]);
		public static native int mt8rfdecrypt(byte key[],byte ptrSource[],int msgLen, byte ptrDest[]);
		
		
		
		 private void init()//操作设备文件时需要权限
		 {
			   boolean isroot = (RootCmd.haveRoot()); 	
		    	RootCmd.execRootCmdSlient(
		    			
		    			"chmod 777 /dev/ttyS0;"+
		    			"chmod 777 /dev/ttyS1;"+
		    			"chmod 777 /dev/ttyS2;"+
		    			"chmod 777 /dev/ttyS3;"+
		    			"chmod 777 /dev/bus/;"+
		    			"chmod 777 /dev/bus/usb/;"+
		    			"chmod 777 /dev/bus/usb/0*;"+
		    			"chmod 777 /dev/bus/usb/001/*;"+
		    			"chmod 777 /dev/bus/usb/002/*;"+
		    			"chmod 777 /dev/bus/usb/003/*;"+
		    			"chmod 777 /dev/bus/usb/004/*;"+
		    			"chmod 777 /dev/bus/usb/005/*;");
		    	
		  }
	

}