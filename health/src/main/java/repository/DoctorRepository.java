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
			System.out.println("DB connected");
		} catch (Exception e) {
			System.out.println(e);
		}
		//int d = getLastID();
	}
	
public String create(String name, String section,  String contact_no, String email) {
		
		String output = "";
		String sql = "INSERT INTO doc VALUES (?,?,?,?,?)";
		int id = getLastID();//getting next id for auto increment 
		try {
			PreparedStatement st = con.prepareStatement(sql);
			//System.out.println("Connected");

			st.setInt(1, id+1);
			st.setString(2, name);
			st.setInt(3, Integer.parseInt(section));
			st.setInt(4, Integer.parseInt(contact_no));
			st.setString(5, email);

			st.executeUpdate();
			System.out.println("one row inserted...");
			getLastID();
			String newItems = viewDoctor();    
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
			System.out.println(e);
		}
		return output;
	}
	
public int getLastID() {
    int id = 0;
    try {
        String sql="select MAX(id) from doc "; 
        Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
      
		if(rs.next()){
			id=rs.getInt(1);
			
		}
		System.out.println(id);
    }
    catch(Exception e){
    	
    }
    return id;
}


public String viewDoctor() {

	
	String output = "";

	try {
		
		output = "<table border='1'><tr><th>Doctor name</th>"
				+ "<th>Section</th><th>Contact_No</th>"      
				+ "<th>Email</th>"
				+ "<th>Update</th><th>Remove</th></tr>"; 
		
		String sql = "SELECT * FROM doc";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			//Doctor d = new Doctor();
			
			String id = Integer.toString((rs.getInt(1)));
			String name = (rs.getString(2));
			String section = (rs.getString(3));
			String contact_no = Integer.toString((rs.getInt(4)));
			String email = (rs.getString(5));
			
			
			output += "<tr><td><input id='hidItemIDUpdate'"
					+"name='hidItemIDUpdate' type='hidden'" 
					+"value='" + id + "'>"+ name + "</td>";     
			output += "<td>" + section + "</td>";    
			output += "<td>" + contact_no + "</td>";     
			output += "<td>" + email + "</td>"; 
			 
		    // buttons     
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
					+ id + "'>" + "</td></tr>";
			
			
			
		}
		output += "</table>"; 
	} catch (Exception e) {
		System.out.println(e);
	}
	return output;
}

public Doctor searchDoctor(int id) {

	String sql = "SELECT * FROM doc where id = " + id;
	Doctor d = new Doctor();

	try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			
			d.setId(rs.getInt(1));
			d.setName(rs.getString(2));
			d.setSection(rs.getString(3));
			d.setContact_no(rs.getInt(4));
			d.setEmail(rs.getString(5));
			
			
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	return d;
}

public String update(String id, String name, String section, String contact_no, String email) {
	
	String output = "";
	String sql = "UPDATE doc SET name=?, section=?, contact_no=?, email=?  WHERE id=?";

	try {
		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, name);
		st.setInt(2, Integer.parseInt(section));
		st.setInt(3, Integer.parseInt(contact_no));
		st.setString(4, email);
		st.setInt(5, Integer.parseInt(id));
	
		st.execute();
		
		System.out.println("one row updated...");
		
		String newItems = viewDoctor();    
		output = "{\"status\":\"success\", \"data\": \"" +        newItems + "\"}";   
	} catch (Exception e) {
		output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";   
		System.out.println(e);
	}
	return output;

}

public String delete(String id) {
	
	  
	
	String output = "";
	String sql = "DELETE FROM doc WHERE id=?";

	try {
		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, Integer.parseInt(id));

		st.executeUpdate();
		
		System.out.println("one row deleted...");
		
		String newItems = viewDoctor();
		output = "{\"status\":\"success\", \"data\": \"" 
		+        newItems + "\"}"; 
	} catch (Exception e) {
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";   
		System.out.println(e);
	}
	return output;
}


	
/*
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
	*/
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
