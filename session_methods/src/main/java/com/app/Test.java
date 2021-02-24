package com.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test {
    static SessionFactory fs=null;
    static {
    	Configuration con=new Configuration().configure();
    	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder();
    	builder.applySettings(con.getProperties());
    	ServiceRegistry sr=builder.build();
    	fs=con.buildSessionFactory(sr);
    }
    public void getTest()
    {
    	Session session=fs.openSession();
    	Student st=(Student)session.get(Student.class,1);
    	System.out.println(st);
    	
    	Student st1=(Student)session.get(Student.class,1);
    	System.out.println(st1);
    }
    
    public void loadTest()
    {
    	Session session=fs.openSession();
    	Student st=(Student)session.load(Student.class, 1);
    	System.out.println(st);
    	System.out.println(st.getId());
    	System.out.println(st.getName());
    	System.out.println(st.getAddress());
    	Student s=(Student)session.load(Student.class, 1);
    	System.out.println(s);
    	//session.flush();
    }
    
    public void persist()
    {
    	Session session=fs.openSession();
    	Student s=new Student();
    	s.setName("SNEHA");
    	s.setAddress("wani");
    	session.persist(s);
    	
    	/*Integer mxid=(Integer)session.save(s);
    	System.out.println(mxid);
    	*/
    	session.beginTransaction().commit();
    }
    
    public void saveOrUpdate()
    {
    	Session session=fs.openSession();
    	Student st=new Student();
    	st.setId(2);
    	st.setName("kajol");
    	st.setAddress("Akola");
    	session.saveOrUpdate(st);
    	session.beginTransaction().commit();
    }
    
    public void merge()
    {
    	Session session=fs.openSession();
    	Student s=(Student)session.get(Student.class, 1);//if id present 
    	s.setName("Indar");
    	session.close();
    	
    	Session session1=fs.openSession();
    	Student s1=(Student)session1.get(Student.class, 1);
    	s.setAddress("Wardha");
    	session1.merge(s);
    	session1.beginTransaction().commit();
    }
	public static void main(String[] args) {
		Test test=new Test();
		//test.getTest();
		//test.loadTest();
		//test.persist();
		//test.saveOrUpdate();
		test.merge();
	}

}
