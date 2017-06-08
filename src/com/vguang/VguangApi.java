package com.vguang;

import java.io.UnsupportedEncodingException;

/**
 * 微光API
 * 
 * @author yuxdc
 * 
 */
public class VguangApi {
	private static VguangApi _vugangApi = null;

	private VguangApi() {
	}

	public static VguangApi getInstance() {
		if (_vugangApi == null) {
			_vugangApi = new VguangApi();
		}

		return _vugangApi;
	}

	static {
		System.loadLibrary("VguangZ100");
	}

	public static final int DEVICE_VALID = 1; // 设备有效
	public static final int DEVICE_INVALID = 2; // 设备无效

	// 设置QR引擎状态，true时qr引擎开启；false时qr引擎关闭
	public native void setQRable(boolean bqr);

	// 设置DM引擎状态，true时dm引擎开启；false时dm引擎关闭
	public native void setDMable(boolean bdm);

	// 设置一维码引擎状态，true时一维码引擎开启；false时一维码引擎关闭
	public native void setBarcode(boolean bbarcode);

	// 设置自动休眠状态，true时自动休眠开启；false时自动休眠关闭
	public native void setAI(boolean bai);

	// 设置自动休眠灵敏度，1~64，缺省10
	public native void setAISensitivity(int aiLimit);

	// 设置自动休眠响应时间，单位秒
	public native void setAIResponseTime(int responseTime);

	// 设置解码间隔时间，单位毫秒
	public native void setDeodeIntervalTime(int intervalTime);

	// 设置扬声器状态，true时扬声器(缺省声音)开启；false时扬声器(缺省声音)关闭
	public native void setBeepable(boolean bbeep);

	// 扬声器声音，times取值为1,2,3
	public native void beep(int times);

	// 设置灯开关，true-打开，false-关闭
	public native void setLightSwitch(boolean blightSwitch);

	// 开灯
	public native void lightOn();

	// 关灯
	public native void lightOff();

	// 得到版本号
	public native long getVersionInfo();

	// 打开设备
	public native void openDevice();

	// 关闭设备
	public native void closeDevice();

	// 重启设备
	public native void resetDevice();

	// 设置摄像头打开方式，1-非阻塞（缺省）， 2-阻塞
	public native void setCameraMode(int mode);

	// 扫码回调接口
	private static IDecodeCallBack decodeCallBack = null;
	// 设备状态变化回调接口
	private static IDeviceStatusCallBack deviceStatusCallBack = null;

	// 设置扫码回调实现
	public static void setDecodeCallBack(IDecodeCallBack iDecodeCallBack) {
		decodeCallBack = iDecodeCallBack;
	}

	// 设置设备状态变化回调实现
	public static void setDeviceStatusCallBack(
			IDeviceStatusCallBack iDeviceStatusCallBack) {
		deviceStatusCallBack = iDeviceStatusCallBack;
	}

	/**
	 * 扫码回调函数
	 * 
	 * @param decodeStrBytes
	 *            解码字符串对应的byte数组（缺省是GBK编码）
	 */
	public static void decodeCallBack(byte[] decodeStrBytes) {
		if (decodeCallBack != null) {
			String str = null;
			try {
				if (isUTF8(decodeStrBytes, 0, decodeStrBytes.length)) {
					str = new String(decodeStrBytes, 0, decodeStrBytes.length,
							"UTF-8");
				} else {
					str = new String(decodeStrBytes, 0, decodeStrBytes.length,
							"GBK");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			decodeCallBack.decodeCallBack(str);
		}
		return;
	}

	/**
	 * 设备状态变化回调函数
	 * 
	 * @param status
	 *            设备状态,DEVICE_VALID(1)-设备有效,DEVICE_INVALID(2)-设备无效
	 */
	public static void deviceStatusCallBack(int status) {
		if (deviceStatusCallBack != null) {
			deviceStatusCallBack.deviceStatusCallBack(status);
		}
		return;
	}

	public static boolean isUTF8(byte[] data, int start, int end) {
		boolean canBeUTF8 = true;
		for (int i = start; i < end; i++) {
			int value = data[i] & 0xFF;

			// 两字节情况
			if (value >= 0xC0 && value <= 0xDF) {
				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}
			} else if (value >= 0xE0 && value <= 0xEF) { // 三字节情况
				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}
			} else if (value >= 0xF0 && value <= 0xF7) { // 四字节情况
				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}
			} else if (value >= 0xF8 && value <= 0xFB) { // 五字节情况
				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}
			} else if (value >= 0xFC && value <= 0xFD) { // 六字节情况
				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}

				i++;
				value = data[i] & 0xFF;
				if (value >= 0x80 && value <= 0xBF) {

				} else {
					return false;
				}
			}
		}
		return canBeUTF8;
	}

	// 得到OS版本
	public static int getOSVer() {
		int sdk_ver = android.os.Build.VERSION.SDK_INT;

		return sdk_ver;
	}
}
