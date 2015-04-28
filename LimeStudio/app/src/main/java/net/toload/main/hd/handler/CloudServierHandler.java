package net.toload.main.hd.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import net.toload.main.hd.ui.SetupImFragment;

public class CloudServierHandler extends Handler {

	public static final String tag = "CloudServierHandler";
	
	private SetupImFragment fragment;

	public CloudServierHandler(SetupImFragment fragment) {
		this.fragment = fragment;
	}
	
	@Override
	public void handleMessage(Message msg) {
		
		String action = msg.getData().getString("action");  
		if(action.equals("show")){
			String type = msg.getData().getString("type");  
			if(type.equals("backup")){
				fragment.showProgressDialog(true);
			}else if(type.equals("restore")){
				fragment.showProgressDialog(false);
			}
		}else if(action.equals("update")){
			int progress = msg.getData().getInt("progress");
			fragment.updateProgressDialog(progress);
		}else if(action.equals("close")){
			fragment.closeProgressDialog();
		}
		
	}
	
	public void showProgressDialog(boolean isBackup){
		Bundle b = new Bundle();
		   b.putString("action", "show");
		   if(isBackup){
			   b.putString("type", "backup");  
		   }else{
			   b.putString("type", "restore");  
		   }
		   Message m = this.obtainMessage();
		   		   m.setData(b);
		   this.sendMessage(m);
	}

	public void updateProgressDialog(int value){
		Bundle b = new Bundle();
		   b.putString("action", "update");
		   b.putInt("progress", value);  
		   Message m = this.obtainMessage();
		   		   m.setData(b);
		   this.sendMessage(m);
	}

	public void closeProgressDialog(){
		Bundle b = new Bundle();
		   b.putString("action", "close");
		   Message m = this.obtainMessage();
		   		   m.setData(b);
		   this.sendMessage(m);
	}

}