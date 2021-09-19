package com.student.app.model; 

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.couchbase.model.ConcreteDocument;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
@Table(name="student")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllStudents",
            query   =   "SELECT * FROM student",
                        resultClass = Student.class
    )
})
@NamedStoredProcedureQuery(
        name="addStudent",
        procedureName="addStudent",
        resultClasses = { Student.class },
        parameters={
            @StoredProcedureParameter(name="name", type=String.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="age", type=String.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="phone", type=String.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="country", type=Integer.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="gender", type=Integer.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="standard", type=Integer.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="address", type=Integer.class, mode=ParameterMode.IN),
            @StoredProcedureParameter(name="acceptForm", type=Integer.class, mode=ParameterMode.IN)
        }
)


public class Student extends ConcreteDocument {
	
	@Min(value = 1, message = "{id.required}")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty(message = "{name.required}")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "{age.required}")
	@Column(name="age")
	private String age;
	
	@NotEmpty(message = "{phone.required}")
	@Column(name="phone")
	private String phone;
	
	@NotEmpty(message = "{country.required}")
	@Column(name="country")
	private String country;
	
	@NotEmpty(message = "{gender.required}")
	@Column(name="gender")
	private String gender;
	
	@NotEmpty(message = "{standard.required}")
	@Column(name="standard")
	private String standard;
	
	@NotEmpty(message = "{address.required}")
	@Column(name="address")
	private String address;
	
	@NotEmpty(message = "{acceptForm.required}")
	@Column(name="acceptForm")
	private String acceptForm;
	
	
	
	
	
	public Student(
			@Min(value = 1, message = "{id.required}") long id,
			@NotNull @NotEmpty(message = "{name.required}") String name,
			@NotEmpty(message = "{age.required}") String age, 
			@NotEmpty(message = "{phone.required}") String phone,
			@NotEmpty(message = "{country.required}") String country,
			@NotEmpty(message = "{gender.required}") String gender,
			@NotEmpty(message = "{standard.required}") String standard,
			@NotEmpty(message = "{address.required}") String address,
			@NotEmpty(message = "{acceptForm.required}") String acceptForm) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.country = country;
		this.gender = gender;
		this.standard = standard;
		this.address = address;
		this.acceptForm = acceptForm;
	}
	
	public Student(){
		setJsonType("app_student");
		setPrefix("student_");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAcceptForm() {
		return acceptForm;
	}
	public void setAcceptForm(String acceptForm) {
		this.acceptForm = acceptForm;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", age=" + age + ", phone=" + phone + ", country=" + country
				+ ", gender=" + gender + ", address=" + address + ", acceptForm=" + acceptForm + ", standard="
				+ standard + "]";
	}
	
	/*Comparator for sorting the list by roll no*/
    public static Comparator<Student> stuidComparator = new Comparator<Student>() {

	public int compare(Student s1, Student s2) {

	   long id1 =s1.getId();
	   long id2 = s2.getId();

	   /*For ascending order*/
	   return (int) (id1-id2);

	   /*For descending order*/
	   //rollno2-rollno1;
   }};
	
	
	
}
