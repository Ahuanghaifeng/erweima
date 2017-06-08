package com.example.erweima;

import android.app.Activity;
import android.widget.TextView;

import com.vguang.IDeviceStatusCallBack;
import com.vguang.VguangApi;

public class DeviceStatusCallBack implements IDeviceStatusCallBack {
	private TextView _deviceStatus;
	private Activity _context;

	public DeviceStatusCallBack(Activity context, TextView deviceStatus) {
		_context = context;
		_deviceStatus = deviceStatus;
	}

	@Override
	public void deviceStatusCallBack(final int status) {
		if (_context != null && _deviceStatus != null) {
			_context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// System.out.println("_deviceStatus:" + _deviceStatus);
					if (VguangApi.DEVICE_INVALID == status) {
						_deviceStatus.setText("设备无效");
						_deviceStatus.setEnabled(false);
					} else if (VguangApi.DEVICE_VALID == status) {
						_deviceStatus.setText("设备有效");
						_deviceStatus.setEnabled(true);
					}
				}
			});
		}
	}

}
