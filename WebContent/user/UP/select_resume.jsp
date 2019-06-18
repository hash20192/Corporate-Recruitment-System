<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.bean.Resume" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="java.util.List" %>

<div id="resume_selection">
<h1>Select Resume</h1><br/>
<%List<Resume> l= (List)request.getAttribute("resume_list");Iterator<Resume> it = l.listIterator();int i=0,d=l.size();
				Resume r = new Resume();
				String resume_title;
				 String  resume_name;
				 long resume_id; %>
				
				 <form>
				 <table>
				<% while(it.hasNext() && i<d){
						r = l.get(i);
						resume_title = r.getResume_title(); 
					  	
					  resume_id = r.getResume_id();%>
					  <tr><td>
				<%=resume_title.toString()%></td><td>  <input type="radio" name="group1" value="<%=resume_id%>" />
					
				
					</td>
					</tr>
				<%i++; }%>
				<tr>
				<td>
			<input id="resume_apply" type="button" name="job_apply" value="Apply" onclick="myFunction()" />
			</td>
			</tr>
			</table>
			</form>
</div>
