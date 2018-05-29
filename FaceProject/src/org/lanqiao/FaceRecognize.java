package org.lanqiao;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

//��������ʶ��Ĺ���(���ڰٶ�AI)
public class FaceRecognize {
	//�ٶ�AI��API_ID��API_KEY��SECRET_KEYֵ
	public static final String API_ID = "10560341";
	public static final String API_KEY = "RcTBE8XO3O0HyBRdsG61sjdT";
	public static final String SECRET_KEY = "lCgdGUYf1zsj9Ajk6uz8HMU5AMHuxPM2";
	
	//ͨ�����ðٶ�AI�ṩ��API��������ʶ��
	public static Object faceRecognize(String imgPath) { //imgPath:������������Ƭ
		//��ʼ��һ��AipFace,������ٶ�AIӦ�ý�������
		AipFace client = new AipFace(API_ID, API_KEY, SECRET_KEY);
		//��ѡ�������������Ӳ���
		client.setConnectionTimeoutInMillis(20000);
		client.setSocketTimeoutInMillis(60000);
		//����ʶ��Ĳ���
		HashMap<String,String> options = new HashMap<String,String>();
		//������������������
		options.put("max_face_num", "1");
		//��������ֵ������
		options.put("face_fields", "age,beauty");
		//��ʼ���
		JSONObject response = client.detect(imgPath, options);
		System.out.println(response);
		return response;
	}
}
