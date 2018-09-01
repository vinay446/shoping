<nav class="navbar navbar-transparent navbar-absolute">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"> <c:out value="${page}"/> </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#pablo" class="dropdown-toggle"
                       data-toggle="dropdown"> <i class="material-icons">dashboard</i>
                        <p class="hidden-lg hidden-md">Dashboard</p>
                    </a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"> <i class="material-icons">notifications</i>
                        <span class="notification">5</span>
                        <p class="hidden-lg hidden-md">Notifications</p>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Mike John responded to your email</a></li>
                        <li><a href="#">You have 5 new tasks</a></li>
                        <li><a href="#">You're now friend with Andrew</a></li>
                        <li><a href="#">Another Notification</a></li>
                        <li><a href="#">Another One</a></li>
                    </ul></li>
                <li class="dropdown"><a
                        href="#"
                        class="dropdown-toggle" data-toggle="dropdown"> <i
                            class="material-icons">person</i>
                    </a>
                    <p class="hidden-lg hidden-md">Profile</p>
                    <ul class="dropdown-menu">
                        <c:if test="${sessionutil.getPermissions().getCreate()==1}">
                              <button class="btn btn-round btn-info" style="height: 100%; width: 100%;" onclick="location.href = '<c:url value="/create"/>'">
                                <i class="material-icons"> person </i>Create New User
                            </button><br/>
                        </c:if>
                        <button class="btn btn-round btn-danger" style="height: 100%; width: 100%;" onclick="location.href = '<c:url value="/adminlogout?msg=successfully loggedout&req="/>'">
                                <i class="material-icons"> power_settings_new </i>Logout
                        </button>
                    </ul></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group  is-empty">
                    <input type="text" class="form-control" placeholder="Search">
                    <span class="material-input"></span>
                </div>
                <button type="submit" class="btn btn-white btn-round btn-just-icon">
                    <i class="material-icons">search</i>
                    <div class="ripple-container"></div>
                </button>
            </form>
        </div>
    </div>
</nav>