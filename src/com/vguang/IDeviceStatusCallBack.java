package com.vguang;

/**
 * 设备状态变化回调接口
 * 
 * @author yuxdc
 */
public interface IDeviceStatusCallBack {
	/**
	 * 设备状态变化回调函数
	 * 
	 * @param status
	 *            设备状态
	 */
	public void deviceStatusCallBack(int status);
}
