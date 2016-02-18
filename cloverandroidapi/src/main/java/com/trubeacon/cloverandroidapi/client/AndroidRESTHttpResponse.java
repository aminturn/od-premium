package com.trubeacon.cloverandroidapi.client;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AndroidRESTHttpResponse extends RESTHttpResponse implements Parcelable {

	public AndroidRESTHttpResponse(int statusCode, String responseEntity, Map<String, List<String>> headers) {
		super(statusCode, responseEntity, headers);
	}

	private static Map<String, List<String>> parseHeaders(Header[] headerArray) {
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		if (headers != null) {
			for (Header header : headerArray) {
				String name = header.getName();
				String value = header.getValue();
				List<String> values = headers.get(name);
				if (values == null) {
					values = new ArrayList<String>();
					headers.put(name, values);
				}
				values.add(value);
			}
		}
		return headers;
	}

	public static final Creator<AndroidRESTHttpResponse> CREATOR = new Creator<AndroidRESTHttpResponse>() {

		@Override
		public AndroidRESTHttpResponse createFromParcel(Parcel source) {

			int statusCode = source.readInt();
			String responseEntity = source.readString();

			Map<String, List<String>> headers = new HashMap<String, List<String>>();
			for (int i = 0; i+1 < source.dataAvail(); i+=2) {
				String name = source.readString();
				String value = source.readString();
				List<String> values = headers.get(name);
				if (values == null) {
					values = new ArrayList<String>();
					headers.put(name, values);
				}
				values.add(value);
			}

			return new AndroidRESTHttpResponse(statusCode, responseEntity, headers);
		}

		@Override
		public AndroidRESTHttpResponse[] newArray(int size) {
			return new AndroidRESTHttpResponse[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(super.getStatusCode());
		dest.writeString(super.getResponseBody());
		if (super.getHeaders() != null) {
			for (String name : super.getHeaders().keySet()) {
				for (String value : super.getHeaders().get(name)) {
					dest.writeString(name);
					dest.writeString(value);
				}
			}
		}
	}

}
