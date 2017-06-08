package com.example.erweima;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.vguang.IDecodeCallBack;

public class DecodeCallBack implements IDecodeCallBack {
	// private TextView _decodeStr;
//	private Activity _context;

	// public DecodeCallBack(Activity context, TextView decodeStr){
	// _context = context;
	// _decodeStr = decodeStr;
	// }
//	public DecodeCallBack(Activity context) {
//		_context = context;
//	}

	@Override
	public void decodeCallBack(final String _decodeStr) {
//		if (_context != null && _decodeStr != null) {
//			_context.runOnUiThread(new Runnable() {
//
//				@Override
//				public void run() {
//					// //System.out.println("decodeStr:" + decodeStr);
//					// _decodeStr.setText(decodeStr);
////					Toast.makeText(_context, "aaaaaaaaaaaa", Toast.LENGTH_SHORT)
////							.show();
//					if (listener != null) {
//						listener.erWeiMaListener(_decodeStr);
//					}
//					
//				}
//			});
//		}
		if(_decodeStr!=null){
			if (listener != null) {
				listener.erWeiMaListener(_decodeStr);
			}
		}

	}

	public interface onErWeiMaListener {
		void erWeiMaListener(String str);
	}

	onErWeiMaListener listener;

	public void setOnErWeiMaListener(onErWeiMaListener listener) {
		this.listener = listener;
	}

}
