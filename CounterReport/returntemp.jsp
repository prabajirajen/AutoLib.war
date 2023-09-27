	/*----------------------------BOOK BANK DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("BOOK BANK"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2  "+strsql+" and doc_type='BOOK BANK' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2  "+strsql+" and doc_type='BOOK BANK' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");

		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='BOOK BANK' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}




	/*----------------------------JOURNALS DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("JOURNAL"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='JOURNAL' order by  "+order;

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='JOURNAL' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");

		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();




	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='JOURNAL' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}



/*----------------------------THESIS DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("THESIS"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='THESIS' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Membercode</th><th>Member Name</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th></tr>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Member_Name=rs.getString("Member_Name");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");


		ser.add(Member_Code);
		ser.add(Member_Name);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);


	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='THESIS' group by return_date order by return_date";
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
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Return Date</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='THESIS' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}



/*----------------------------BACK VOLUME----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("BACK VOLUME"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='BACK VOLUME' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
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

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th></tr>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='BACK VOLUME' group by return_date order by return_date";
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
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");
	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);


		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Return_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='BACK VOLUME' group by return_date order by return_date";
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
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}




	/*----------------------------PROCEEDING----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("PROCEEDING"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='PROCEEDING' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);
		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='PROCEEDING' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);


		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Return_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();



	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='PROCEEDING' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


	/*----------------------------REPORTS----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("REPORT"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='REPORT' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);
		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='REPORT' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);


		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Return_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();



	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='REPORT' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}



	/*----------------------------NON BOOK----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("NON BOOK"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='NON BOOK' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);
		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='NON BOOK' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);


		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Return_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();



	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='NON BOOK' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


/*----------------------------STANDARD----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("STANDARD"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from Jhistory where 1<2 "+strsql+" and doc_type='STANDARD' order by  "+order;
		//strsql="select * from juserstat where issue_date="+Today_Date+" and //return_date="+Today_Date+" "+strsql+" order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{

		String Member_Code=rs.getString("Member_Code");
		String Access_No=rs.getString("Access_No");
		String Title=rs.getString("Title");
		String Issue_Date=getdate(rs.getString("Issue_Date"));
		String Due_Date=getdate(rs.getString("Due_Date"));
		String dept_name=rs.getString("dept_name");
		String group_name=rs.getString("group_name");
		String course_name=rs.getString("course_name");
		String Member_Name=rs.getString("Member_Name");

		ser.add(Member_Code);
		ser.add(Access_No);
		ser.add(Title);
		ser.add(Issue_Date);
		ser.add(Due_Date);
		ser.add(dept_name);
		ser.add(group_name);
		ser.add(course_name);
		ser.add(Member_Name);
		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		 sno=sno+1;
		 }
	sc.clear();


	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='STANDARD' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Return_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);


		/* out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Return_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x; */
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		//count_book=rs.getString("count_book");
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		//sno=sno+1;
		//int x=Integer.parseInt(count_book);
		//total=total+x;
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		out.print(x);
		total=total+x;
		 }
	sc.clear();



	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, return_date from Jhistory where 1<2 "+strsql+" and doc_type='STANDARD' group by return_date order by return_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>RETURN REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);


		/* count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;*/
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0; i<sc.size();i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}
