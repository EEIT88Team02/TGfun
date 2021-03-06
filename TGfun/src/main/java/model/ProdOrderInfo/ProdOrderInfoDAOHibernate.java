package model.ProdOrderInfo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Member.MemberDAOHibernate;
@Component
public class ProdOrderInfoDAOHibernate implements ProdOrderInfoDAO {

	// 測試程式
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		ProdOrderInfoDAOHibernate dao = (ProdOrderInfoDAOHibernate) context.getBean("prodOrderInfoDAOHibernate");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();		// 查詢
			// 查詢
//			 ProdOrderInfoDAOHibernate dao = new ProdOrderInfoDAOHibernate(session);
//			 ProdOrderInfoBean bean = dao.select(1);
//			 System.out.println("bean="+bean);
//			// 新增
//			ProdOrderInfoDAOHibernate insert = new ProdOrderInfoDAOHibernate(session);
//			ProdOrderInfoBean bean = new ProdOrderInfoBean();
//			bean.setProdOrderID(1);
//			bean.setProdCode(2);
//			bean.setProdCount(5);
//			bean.setProdSum(25654);
//			insert.insert(bean);
//			System.out.println("insert=" + insert);
			//
			// 修改
//			 ProdOrderInfoDAOHibernate update = new ProdOrderInfoDAOHibernate(session);
//			 update.update(5,1,3,20,789456);
			// 刪除
//			 ProdOrderInfoDAOHibernate dao = new ProdOrderInfoDAOHibernate(session);
//			 dao.delete(5);
			// 查詢全部
//			 ProdOrderInfoDAOHibernate selectall = new ProdOrderInfoDAOHibernate(session);
//			 List<ProdOrderInfoBean> bean = selectall.selectall();
//			 System.out.println(bean);
//			

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	// session-----------------------------------------------
	private SessionFactory sessionFactory = null;
	public ProdOrderInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// method---------------------------------------------

	public ProdOrderInfoBean select(int prodorderinfoID) {

		return (ProdOrderInfoBean) this.getSession().get(ProdOrderInfoBean.class,prodorderinfoID);

	}

	public ProdOrderInfoBean update(int prodorderinfoID, int prodOrderID, int prodCode, int prodCount, int prodSum) {
		ProdOrderInfoBean result = (ProdOrderInfoBean) this.getSession().get(ProdOrderInfoBean.class,prodorderinfoID);
		if (result != null) {
			result.setProdOrderID(prodOrderID);
			result.setProdCode(prodCode);
			result.setProdCount(prodCount);
			result.setProdSum(prodSum);
		}
		return result;

	}

	public ProdOrderInfoBean insert(ProdOrderInfoBean bean) {

		ProdOrderInfoBean temp = (ProdOrderInfoBean) this.getSession().get(ProdOrderInfoBean.class,bean.getProdorderinfoID());
		if (temp == null) {
			this.getSession().save(bean);
		}
		return null;
	}

	public boolean delete(int prodorderinfoID) {
		ProdOrderInfoBean bean = (ProdOrderInfoBean) this.getSession().get(ProdOrderInfoBean.class,prodorderinfoID);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}

		return false;
	}

	public List<ProdOrderInfoBean> selectall() {
		Query query = this.getSession().createQuery("from ProdOrderInfoBean");
		return (List<ProdOrderInfoBean>) query.list();
	}

}
