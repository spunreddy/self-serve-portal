<%@ include file="/init.jsp"%>


<portlet:actionURL name="addUser" var="addUserURL" />
<aui:form name="addUser" action="<%=addUserURL.toString()%>" method="post" >
        First Name: <aui:input type="text" name="first_name" />
        Last Name: <aui:input type="text" name="last_name" />
        Email: <aui:input type="email" name="email" />
         Organization: <aui:input type="text" name="org" />
         Is Admin: 
         <aui:input type="radio" name="is_admin" value="yes" /> Yes
         <aui:input type="radio" name="is_admin" value="no" /> No
         Allow Password Login: 
         <aui:input type="radio" name="allow_password_login" value="yes" /> Yes
         <aui:input type="radio" name="allow_password_login" value="no" /> No
         Create Home Directory: 
         <aui:input type="radio" name="create_home_dir" value="yes" /> Yes
         <aui:input type="radio" name="create_home_dir" value="no" /> No
    <aui:input type="submit" name="add_user" value="Add User"/>
</aui:form>
