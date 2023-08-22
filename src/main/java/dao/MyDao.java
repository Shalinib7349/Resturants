package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class MyDao {

	EntityManagerFactory e=Persistence.createEntityManagerFactory("dev");
	EntityManager m=e.createEntityManager();
	EntityTransaction t=m.getTransaction();
	
	
	//we using pass by reference here to access Customer class object
	public void save(Customer customer)
	{
		t.begin();
		m.persist(customer);//here we add object of Customer class thatsy we using pass by reference
		t.commit();
	}
	
	public Customer fetchByEmail(String email)
	{
		Query query=(Query) m.createQuery("select x from Customer x where email=?1").setParameter(1,email);
		List<Customer>list=query.getResultList();   //it will execute and check
		if(list.isEmpty())
		return null;
		else
			return list.get(0);
}
	public Customer fetchByMobile(long mobile){
		Query query=(Query) m.createQuery("select x from Customer x where mobile=?1").setParameter(1,mobile);
		List<Customer>list=query.getResultList();   
		if(list.isEmpty())
		return null;
		else
			return list.get(0);
	}
	}
