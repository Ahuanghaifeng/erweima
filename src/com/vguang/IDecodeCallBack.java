package com.vguang;

/**
 * 扫码回调接口
 * 
 * @author yuxdc
 */
public interface IDecodeCallBack {
	/**
	 * 扫码回调函数
	 * 
	 * @param decodeStr
	 *            扫码结果
	 */
	public void decodeCallBack(String decodeStr);
}
