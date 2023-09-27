//FINE FOR THESIS

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("THESIS"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back //onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='THESIS' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}


//FINE FOR BACK VOLUME

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("BACK VOLUME"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back //onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
 strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}
	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}

//FINE FOR PROCEEDING

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("PROCEEDING"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back //onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}



	//FINE FOR REPORTS

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("REPORT"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back //onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='REPORT' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}

//FINE FOR NON BOOK

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("NON BOOK"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		// subcount=subcount+faa;

	}
	sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}


//FINE FOR STANDARD

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("STANDARD"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back //onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='STANDARD' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		// subcount=subcount+faa;
	}
		sc.clear();

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();
out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}
