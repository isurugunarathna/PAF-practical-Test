package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Appoinment;
import model.Doctor;

public class DoctorRepository {
	Connection con = null;

	public DoctorRepository() {
		String url = "jdbc:mysql://localhost:3306/test";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Doctor> getDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		String sql = "select * from doc";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) 
			{
				Doctor d = new Doctor();
				
				d.setId(rs.getInt(1));
				d.setName(rs.getString(2));
				d.setSection(rs.getString(3));
				d.setContact_no(rs.getInt(4));
				d.setEmail(rs.getString(5));
				
				

				doctors.add(d);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return doctors;
	}

	public Doctor getDoctor(int id) {
		String sql = "select * from doc where id="+id;
		Doctor d = new Doctor();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
			{
				d.setId(rs.getInt(1));
				d.setName(rs.getString(2));
				d.setSection(rs.getString(3));
				d.setContact_no(rs.getInt(4));
				d.setEmail(rs.getString(5));
				
				
				
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return d;
	}

	public void create(Doctor d1) 
	{
		String sql = "insert into doc values(?,?,?,?,?)";
		try 
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, d1.getId());
			st.setString(2, d1.getName());
			st.setString(3, d1.getSection());
			st.setInt(4, d1.getContact_no());
			st.setString(5, d1.getEmail());
			
			st.executeUpdate();
		
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}

	}

	public void update(Doctor d1) {
		String sql = "update doc set name=?, section=?, contact_no=?,email=? where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, d1.getName());
			st.setString(2, d1.getSection());
			st.setInt(3, d1.getContact_no());
			st.setString(4, d1.getEmail());
			st.setInt(5, d1.getId());
			
			st.executeUpdate();
		
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}

	}

	public void delete(int id) 
	{
		String sql = "delete from doc where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
		
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	
	}
	
	public List<Appoinment> viewAppoinment(int id)
	{
		List<Appoinment> appoinment = new ArrayList<>();
		String sql = "select * from appoinment where id="+id;
		
		try 
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				Appoinment a = new Appoinment();
				
				a.setAp_id(rs.getInt(1));
				a.setDate(rs.getString(2));
				a.setTime(rs.getInt(3));
				a.setId(rs.getInt(4));
				
				appoinment.add(a);
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
			
		}
		
		return appoinment;
		
	}

	
}
