package model.DeviceInfo;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.RoomDeviceInfo.RoomDeviceInfoBean;

@Entity
@Table(name = "DEVICEINFO")
@Component
public class DeviceInfoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deviceID;
	private String device;
	private byte[] deviceImage;

	@OneToMany(mappedBy = "DeviceInfos")
	private Set<RoomDeviceInfoBean> roomDeviceInfos;

	public Set<RoomDeviceInfoBean> getRoomDeviceInfos() {
		return roomDeviceInfos;
	}

	public void setRoomDeviceInfos(Set<RoomDeviceInfoBean> roomDeviceInfos) {
		this.roomDeviceInfos = roomDeviceInfos;
	}

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			/* 查詢單筆ok */
//			 DeviceInfoBean select = (DeviceInfoBean) session.get(DeviceInfoBean.class,2);
//			 System.out.println(select);

			// DeviceInfoBean select=(DeviceInfoBean)session.get(DeviceInfoBean.class, 1);
			// System.out.println(select.getRoomDeviceInfos());

			/* 新增資料ok */
			// DeviceInfoBean insert = new DeviceInfoBean();
			// insert.setDevice("電視");
			// session.save(insert);

			/* 刪除ok */
			// DeviceInfoBean bean=(DeviceInfoBean)session.get(DeviceInfoBean.class, 2);
			// session.delete(bean);

			/* 修改ok */
			// DeviceInfoBean bean = (DeviceInfoBean) session.get(DeviceInfoBean.class,2);
			// bean.setDevice("xx");

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 DeviceInfoBean select = (DeviceInfoBean) session.get(DeviceInfoBean.class,2);
			 System.out.println(select);
			 	 
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

	@Override
	public String toString() {
		return "DeviceInfoBean [deviceID=" + deviceID + ", device=" + device + ", deviceImage=" + Arrays.toString(deviceImage) + "]";
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public byte[] getDeviceImage() {
		return deviceImage;
	}

	public void setDeviceImage(byte[] deviceImage) {
		this.deviceImage = deviceImage;
	}

}
