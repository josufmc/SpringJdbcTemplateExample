package com.novellius;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.novellius.dao.AdminDao;
import com.novellius.pojo.Admin;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		try {
			
//			Admin admin = new Admin();
//			Timestamp ts = new Timestamp(new Date().getTime());
//			admin.setCargo("gerente");
//			admin.setNombre("Martin");
//			admin.setFechaCreacion(ts);
//			adminDao.save(admin);	
			
//			List<Admin> admins = adminDao.findAll();
//			for (Admin admin2 : admins) {
//				System.out.println(admin2);
//			}
			
//			System.out.println(adminDao.findById(1));
//			System.out.println(adminDao.findById(3));
//			System.out.println(adminDao.findByName("Martin").toString());
			
			
//			Admin admin = adminDao.findById(1);
//			if (admin == null) {
//				System.out.println("Admin no encontrado, saliendo del programa...");
//				System.exit(0);
//			}
//			System.out.println("Admin (1): " + admin);
//			admin.setCargo("currito");
//			admin.setNombre("Pedro");
//			
//			boolean result;
//			result = adminDao.update(admin);
//			if (result){
//				System.out.println("Actualizado correctamente " + admin);
//			}
//			result = adminDao.delete(admin.getIdAd());
//			if (result){
//				System.out.println("Admin: " + admin + " eliminado correctamente");
//			}
			
			// Prueba batch
			List<Admin> admins = adminDao.findAll();
			int ids[] = adminDao.saveAll(admins);
			
			
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();
	}

	public static void imprimirAdministrador() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		Admin administrador = (Admin) applicationContext.getBean("admin");
		// administrador.imprimirDireccion();
		System.out.println(administrador);
		((ClassPathXmlApplicationContext) applicationContext).close();
	}
}
