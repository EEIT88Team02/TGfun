package model.BBQOrder;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.BBQInfo.BBQInfoBean;
import model.FoodOrder.FoodOrderBean;
import model.MemberOrder.MemberOrderBean;

@Entity
@Table(name = "BBQORDER")
@Component
public class BBQOrderBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bbqOrderID; // 烤肉區訂單編號
	private int orderID;    // 訂單編號
	private int bbqID;      // 烤肉區編號
	private Date bbqDate; // 日期
	private boolean haveDelete;

	@OneToMany(mappedBy = "bbqOrders")
	private Set<BBQInfoBean> bbqInfos;

	public Set<BBQInfoBean> getBbqInfos() {
		return bbqInfos;
	}

	public void setBbqInfos(Set<BBQInfoBean> bbqInfos) {
		this.bbqInfos = bbqInfos;
	}

	@OneToOne
	@JoinColumn(name = "orderID" ,
				referencedColumnName = "orderID" ,
				insertable = false ,
				updatable = false)

	private MemberOrderBean memberOrders;

	public MemberOrderBean getMemberOrders() {
		return memberOrders;
	}

	public void setMemberOrders(MemberOrderBean memberOrders) {
		this.memberOrders = memberOrders;
	}

	@OneToOne
	@JoinColumn(name = "bbqOrderID" ,
	referencedColumnName = "bbqOrderID" ,
	insertable = false ,
	updatable = false)
	private FoodOrderBean foodorderbean;

	public FoodOrderBean getFoodorderbean() {
		return foodorderbean;
	}

	public void setFoodorderbean(FoodOrderBean foodorderbean) {
		this.foodorderbean = foodorderbean;
	}
	public static void main(String[] args) {

//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			/* 查詢單筆ok */
			// BBQOrderBean select=(BBQOrderBean)session.get(BBQOrderBean.class, 1);
			// System.out.println(select);

//			 BBQOrderBean select=(BBQOrderBean)session.get(BBQOrderBean.class, 1);
//			 System.out.println(select.getBbqInfos());
			
//			 BBQOrderBean select=(BBQOrderBean)session.get(BBQOrderBean.class, 1);
//			 System.out.println(select.getMemberOrders());

			/* 查詢全部ok */
			// SQLQuery query=session.createSQLQuery("select* from BBQOrder");
			// query.addEntity(BBQOrderBean.class);
			// List<BBQOrderBean> beans=query.list();
			// System.out.println(beans);

			/* 新增ok */
			// BBQOrderBean insert=new BBQOrderBean();
			// insert.setBbqID(1);
			// insert.setOrderID(1);
			// insert.setBbqDate(new Date());
			//
			// session.save(insert);

			/* 修改 ok */
			// BBQOrderBean bean=(BBQOrderBean)session.get(BBQOrderBean.class, 1);
			// bean.setBbqDate(new Date());

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.getSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 BBQOrderBean select=(BBQOrderBean)session.get(BBQOrderBean.class, 1);
			 System.out.println(select);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

	@Override
	public String toString() {
		return "BBQOrderBean [bbqOrderID=" + bbqOrderID + ", orderID=" + orderID + ", bbqID=" + bbqID + ", bbqDate=" + bbqDate + ", haveDelete=" + haveDelete + "]";
	}

	public int getBbqOrderID() {
		return bbqOrderID;
	}

	public void setBbqOrderID(int bbqOrderID) {
		this.bbqOrderID = bbqOrderID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getBbqID() {
		return bbqID;
	}

	public void setBbqID(int bbqID) {
		this.bbqID = bbqID;
	}

	public Date getBbqDate() {
		return bbqDate;
	}

	public void setBbqDate(Date bbqDate) {
		this.bbqDate = bbqDate;
	}

	public boolean isHaveDelete() {
		return haveDelete;
	}

	public void setHaveDelete(boolean haveDelete) {
		this.haveDelete = haveDelete;
	}

}
