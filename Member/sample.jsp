function check()
{
  if(confirm("Are You Sure To Continue Save?"))
   {
     document.member.flag.value="save";
	 return true;
    }

}

function chk(){
var flag_s;
var i;
var sp=document.member.Name.value;
if(sp=="")
{
				document.member.Name.focus();
				document.member.flag.value="none";
				document.member.Name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.member.Name.value.length;i++)
 	                      {
 	                       if(document.member.Name.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.member.Name.focus();
		                   	document.member.Name.value="";
			                return false;
		                      }
		                   else if (document.member.Code.value==""){
		                 	document.member.Code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }
 function chk_mc(){
var flag_cs;
var c;
var mc=document.member.Code.value;
if(mc=="")
{
document.member.Code.focus();
				document.member.flag.value="none";
				document.member.Code.value="";
				return false;
				}
				else
				{
	                 for(c=0;c<document.member.Code.value.length;c++)
 	                      {
 	                       if(document.member.Code.value.charAt(c)==" ")
 	                          {flag_cs=0; }
 	                                else{flag_cs=1;}
	                       }
		                  if (flag_cs==0)   
		                    {
		                   	document.member.Code.focus();
		                   	document.member.Code.value="";
			                return false;
		                      }
		                  	/*else if (document.member.Photo.value==""){
		                 	document.member.Photo.focus();
			                return false;
		                      }*/
							 else if (document.member.Deposit.value==""){
						     document.member.Deposit.focus();
						      return false;
		    			}
					        else if (document.member.Name.value==""){
						       document.member.Name.focus();
						       return false;
		    			       }
					          else{
		                    return true;
		                         } 
               }
 }

 function check_date()
{
return true;
}




/////

 if(chk_mc()){
			   if(chk()){
				if(check_date()){
				if(check()){


				document.member.flag.value="save";
		         	document.member.submit();
				}
				}
					}
					else
	{
	alert("Insufficent Data");
	}
					}
					else
	{
	alert("Insufficent Data");
	}
	else
	{
	alert("Insufficent Data");
	}