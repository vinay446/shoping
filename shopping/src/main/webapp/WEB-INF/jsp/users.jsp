<%@ page language="java" contentType="text/html; utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String username = (String) session.getAttribute("username");
        String emailID = (String) session.getAttribute("emailID");
        if (username == null) {
            response.sendRedirect(request.getContextPath()
                    + "/adminlogout?msg=Session Time out Please login again&req=dashboard");
        }
        pageContext.setAttribute("username", username);
        pageContext.setAttribute("emailID", emailID);
        pageContext.setAttribute("page", "Profile");
    %>
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76"
              href="<c:url value="/resources/images/glovision.png"/>" />
        <!-- Favicon-->
        <link rel="icon" href="<c:url value="/resources/images/glovision.png"/>"
              type="image/x-icon" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Users Profile</title>
        <meta
            content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
            name='viewport' />
        <meta name="viewport" content="width=device-width" />
        <!-- Bootstrap core CSS     -->
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>"
              rel="stylesheet" />
        <!--  Material Dashboard CSS    -->
        <link
            href="<c:url value="/resources/css/material-dashboard.css?v=1.2.0"/>"
            rel="stylesheet" />
        <!--     Fonts and icons     -->
        <link
            href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
            rel="stylesheet">
        <link
            href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons'
            rel='stylesheet' type='text/css'>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" />
        <script src="<c:url value="/resources/js/util.js"/>"></script>
    </head>

    <body>
        <div class="wrapper">
            <%@include file="sidebar.jsp"%>
            <div class="main-panel">
                <%@include file="header.jsp"%>
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <center><div id="load" class="loader"></div></center>
                                <c:if test="${method.equals('edit')}">
                                <div class="col-md-8">
                                    <div class="card">
                                        <div class="card-header" data-background-color="purple">
                                            <h4 class="title">Edit Profile ${user.firstname} </h4>                                           
                                        </div>
                                        <div class="card-content">
                                            <form method="POST" action="<c:url value="/editprofile/${user.id}"/>" enctype="multipart/form-data">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">User ID </label> <input
                                                                type="text" name="userID" value="${user.id}" class="form-control" ${disabledfield[0]==0?"disabled":""}>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Email address</label> <input
                                                                type="email" name="emailID" value="${user.emailID}" class="form-control" ${disabledfield[1]==0?"disabled":""} >
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Phone number</label> <input
                                                                type="text" name="phone" value="${user.phone}" class="form-control" ${disabledfield[2]==0?"disabled":""}>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Fist Name</label> <input
                                                                type="text" name="firstname" value="${user.firstname}" class="form-control" ${disabledfield[3]==0?"disabled":""}>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Last Name</label> <input
                                                                type="text" name="lastname" value="${user.lastname}" class="form-control" ${disabledfield[4]==0?"disabled":""}>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Adress</label> <input
                                                                type="text"  name="address" value="${user.address}" class="form-control" ${disabledfield[5]==0?"disabled":""}>
                                                        </div>
                                                    </div>
                                                </div>									
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">                                                           
                                                            <label class="control-label">Role ID</label>
                                                            <select class="form-control"  name="roleID" ${disabledfield[6]==0?"disabled":""}>
                                                                <option ${user.roleID.equals('user')?'selected="selected"':''} value="user">USER</option>
                                                                <option ${user.roleID.equals('admin')?'selected="selected"':''} value="admin">ADMIN</option>
                                                            </select>                                                            
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">                                                           
                                                            <label class="control-label">IS ACTIVE</label>
                                                            <select class="form-control" name="isactive" ${disabledfield[7]==0?"disabled":""}>                                                                  
                                                                <option ${user.isactive.equals('yes')?'selected="selected"':''} value="yes">YES</option>
                                                                <option ${user.isactive.equals('no')?'selected="selected"':''} value="no">NO</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <label class="control-label">Image </label>
                                                        <input type="file" name="image"  id="image" class="btn btn-primary btn-round" ${disabledfield[8]==0?"disabled":""} />                                                    
                                                    </div>
                                                </div>
                                                <button type="submit"  class="btn btn-success btn-round pull-left">Update Profile</button>
                                                <button type="button" class="btn btn-danger btn-round pull-right" onclick="location.href = '${pageContext.request.contextPath}/users'">Cancel</button>
                                                <div class="clearfix"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div>                                
                                <div class="col-md-4">
                                    <div class="card card-profile">
                                        <div class="card-avatar">
                                            <img class="img" src="http://localhost/images/${user.imageid}" />
                                        </div>
                                        <div class="content">
                                            <h6 class="category text-gray">${user.firstname}</h6>
                                            <h4 class="card-title">${user.lastname}</h4>
                                            <p class="card-content">Email: ${user.emailID}</p>										
                                        </div>
                                    </div>
                                </div>                                
                            </c:if>
                            <c:if test="${method.equals('create')}">
                                <div class="col-md-8" id="creatediv">
                                    <div class="card">
                                        <div class="card-header" data-background-color="purple">
                                            <h4 class="title">Create new User Profile</h4>                                           
                                        </div>
                                        <div class="card-content">
                                            <form method="POST" id="createform" action="<c:url value="/createprofile"/>" enctype="multipart/form-data">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">User ID </label> <input
                                                                type="text" name="userID" id="userID" value="${userid}" class="form-control" disabled>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Email address</label> <input
                                                                type="email" name="emailID" id="emailID" class="form-control" required >
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Phone number</label> <input
                                                                type="text" name="phone" id="phone" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Fist Name</label> <input
                                                                type="text" name="firstname" id="firstname" class="form-control" >
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Last Name</label> <input
                                                                type="text" name="lastname" id="lastname" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Adress</label> <input
                                                                type="text"  name="address" id="address" class="form-control" >
                                                        </div>
                                                    </div>
                                                </div>									
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">                                                           
                                                            <label class="control-label">Role ID</label>
                                                            <select class="form-control" id="roleID" name="roleID" >
                                                                <option  value="user">USER</option>
                                                                <option  value="admin">ADMIN</option>
                                                            </select>                                                            
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">                                                           
                                                            <label class="control-label">IS ACTIVE</label>
                                                            <select class="form-control" id="isactive" name="isactive" >                                                                   
                                                                <option value="no">NO</option>
                                                                <option value="yes">YES</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Password</label> <input
                                                                type="password" name="password" id="password" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Confirm Password</label> <input
                                                                type="password" name="cpassword" id="cpassword" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="control-label">Image</label>
                                                        <input type="file" name="image" id="image"  class="btn btn-primary btn-round" ${disabledfield[8]==0?"disabled":""} />                                                    
                                                    </div>
                                                </div>
                                                <button type="button" class="btn btn-success btn-round pull-left" onclick="createuser()">Create Profile</button>
                                                <button type="button" class="btn btn-danger btn-round pull-right" onclick="location.href = '${pageContext.request.contextPath}/users'">Cancel</button>
                                                <div class="clearfix"></div>
                                            </form>                                                
                                        </div>
                                    </div>
                                </div> 
                            </c:if>
                            <c:if test="${method.equals('view')}">                               
                                <c:if test="${username.equalsIgnoreCase('sysadmin')}">
                                    <div class="col-md-4">
                                        <div class="card card-profile">
                                            <div class="card-avatar">
                                                <img class="img" src="http://localhost/images/sysadmin.png" />
                                            </div>
                                            <div class="content">
                                                <h6 class="category text-gray">User ID: SYS ADMIN </h6>
                                                <h4 class="card-title">Name :Vinay</h4>
                                                <p class="card-content">Email: ${emailID}</p>
<!--                                                <a href="<c:url value="/edituser/0"/>"
                                                   class="btn btn-primary btn-round">Edit</a>                                                -->
                                                <a href="<c:url value="/create"/>" class="btn btn-primary btn-round">Create</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:forEach items="${users}" var="user" >
                                    <div class="col-md-4">
                                        <div class="card card-profile">
                                            <div class="card-avatar">
                                                <img class="img" src="http://localhost/images/${user.imageid}" />
                                            </div>
                                            <div class="content">
                                                <h6 class="category text-gray">User ID: ${user.id} </h6>
                                                <h4 class="card-title">Name :${user.firstname} ${user.lastname}</h4>
                                                <p class="card-content">Email: ${user.emailID}</p>
                                                <a href="<c:url value="/edituser/${user.id}"/>"  class="btn btn-primary btn-round">Edit</a>                                                
                                                <a href="<c:url value="/create"/>"  class="btn btn-primary btn-round">Create</a>
                                                <a href="<c:url value="/delete/${user.id}"/>"   class="btn btn-primary btn-round">Delete</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp"%>
            </div>
        </div>
        <!--   Core JS Files   -->
        <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/material.min.js"/>" type="text/javascript"></script>
        <!--  Charts Plugin -->
        <script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
        <!--  Dynamic Elements plugin -->
        <script src="<c:url value="/resources/js/arrive.min.js"/>"></script>
        <!--  PerfectScrollbar Library -->
        <script src="<c:url value="/resources/js/perfect-scrollbar.jquery.min.js"/>"></script>
        <!--  Notifications Plugin    -->
        <script src="<c:url value="/resources/js/bootstrap-notify.js"/>"></script>
        <!-- Material Dashboard javascript methods -->
        <script src="<c:url value="/resources/js/material-dashboard.js?v=1.2.0"/>"></script>
        <script>
                                                    $(document).ready(function () {
                                                        document.getElementById("load").style.display = 'none';
                                                        var message = "${message}";
                                                        var type = "${type}";
                                                        if (message !== null && message !== "" && message !== " " && message !== "null") {
                                                            if (type.toLowerCase() === "error") {
                                                                displaynotification("top", "right", message, "danger");
                                                            } else if (type.toLowerCase() === "info") {
                                                                displaynotification("bottom", "right", message, "info");
                                                            } else {
                                                                displaynotification("bottom", "right", message, "success");
                                                            }
                                                        }
                                                    });
                                                    function createuser() {
                                                        if (validatecreateuser()) {
                                                            document.getElementById("load").style.display = 'block';
                                                            document.getElementById("creatediv").style.display = 'none';
                                                            $("#createform").submit();
                                                        }
                                                    }

        </script>
    </body>
</html>